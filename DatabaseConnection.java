package marketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class DatabaseConnection {
    private static final String URL = "jdbc:jtds:sqlserver://localhost:1433/minimarketDB";
    private static final String USERNAME = "sa"; 
    private static final String PASSWORD = "password123"; 
    
    private static Connection connection = null;
    
    // Method untuk mendapatkan koneksi
    // Method untuk mendapatkan koneksi
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load SQL Server JDBC Driver
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Koneksi database berhasil!");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC tidak ditemukan: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    // Method untuk menutup koneksi
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method untuk test koneksi
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    // Method untuk mendapatkan data kasir berdasarkan ID
    public static String getNamaKasir(String idKasir) {
        String nama = "";
        try {
            Connection conn = getConnection();
            String query = "SELECT Nama_Kasir FROM Kasir WHERE ID_Kasir = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, idKasir);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                nama = rs.getString("Nama_Kasir");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nama;
    }
    
    // Method untuk mendapatkan data produk berdasarkan ID
    public static Produk getProduk(String idProduk) {
        Produk produk = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Produk WHERE ID_Produk = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, idProduk);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                produk = new Produk();
                produk.setIdProduk(rs.getString("ID_Produk"));
                produk.setNamaProduk(rs.getString("Nama_Produk"));
                produk.setHarga(rs.getFloat("Harga"));
                produk.setStok(rs.getInt("Stok"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produk;
    }
    
    // Method untuk mendapatkan nama pembeli berdasarkan ID
    public static String getNamaPembeli(String idPembeli) {
        String nama = "";
        try {
            Connection conn = getConnection();
            String query = "SELECT Nama_Pembeli FROM Pembeli WHERE ID_Pembeli = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, idPembeli);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                nama = rs.getString("Nama_Pembeli");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nama;
    }
    
    public static String generateTransactionId() {
        // Format yang lebih pendek: TXN + YYMMDD + HHmm
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        String timestamp = sdf.format(new Date());
        return "TXN" + timestamp; // Total: 13 karakter (TXN + 10 digit)
    }

    
    // Method untuk menyimpan transaksi
    public static boolean simpanTransaksi(Transaksi transaksi) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = getConnection();
        if (conn == null) {
            System.out.println("Koneksi database gagal!");
            return false;
        }

        conn.setAutoCommit(false);

        // Generate ID transaksi yang unik
        String idTransaksi = generateTransactionId();
        System.out.println("Generated Transaction ID: " + idTransaksi);

        // 1. Insert ke tabel Transaksi (Header)
        String sqlTransaksi = "INSERT INTO Transaksi (ID_Transaksi, ID_Pembeli, ID_Kasir, Tanggal_Transaksi, Total_Harga, Metode_Pembayaran, Kembalian) VALUES (?, ?, ?, GETDATE(), ?, ?, ?)";
        pstmt = conn.prepareStatement(sqlTransaksi);
        pstmt.setString(1, idTransaksi);
        pstmt.setString(2, transaksi.getIdPembeli());
        pstmt.setString(3, transaksi.getIdKasir());
        pstmt.setDouble(4, transaksi.getTotalHarga());
        pstmt.setString(5, transaksi.getMetodePembayaran() != null ? transaksi.getMetodePembayaran() : "CASH");
        pstmt.setDouble(6, transaksi.getKembalian());

        int rowsAffected = pstmt.executeUpdate();
        System.out.println("Transaksi inserted, rows affected: " + rowsAffected);

        // 2. Insert ke tabel Detail_Transaksi
        pstmt.close();
        String sqlDetail = "INSERT INTO Detail_Transaksi (ID_Transaksi, ID_Produk, Jumlah, Harga_Satuan) VALUES (?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sqlDetail);
        pstmt.setString(1, idTransaksi);
        pstmt.setString(2, transaksi.getIdProduk());
        pstmt.setInt(3, transaksi.getTotalItem());
        
        // Hitung harga satuan dari total harga dibagi jumlah item
        double hargaSatuan = transaksi.getTotalHarga() / transaksi.getTotalItem();
        pstmt.setDouble(4, hargaSatuan);

        rowsAffected = pstmt.executeUpdate();
        System.out.println("Detail transaksi inserted, rows affected: " + rowsAffected);

        // 3. Update stok produk
        pstmt.close();
        String sqlUpdateStok = "UPDATE Produk SET Stok = Stok - ? WHERE ID_Produk = ?";
        pstmt = conn.prepareStatement(sqlUpdateStok);
        pstmt.setInt(1, transaksi.getTotalItem());
        pstmt.setString(2, transaksi.getIdProduk());

        rowsAffected = pstmt.executeUpdate();
        System.out.println("Stok updated, rows affected: " + rowsAffected);

        // Commit transaksi
        conn.commit();
        System.out.println("Transaksi berhasil disimpan dengan ID: " + idTransaksi);
        return true;

    } catch (SQLException e) {
        System.out.println("Error menyimpan transaksi: " + e.getMessage());
        e.printStackTrace();

        try {
            if (conn != null) {
                conn.rollback();
                System.out.println("Transaction rolled back");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    // Method untuk cek apakah tabel exists
public static boolean checkTableExists(String tableName) {
    try {
        Connection conn = getConnection();
        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, tableName);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            return count > 0;
        }
        rs.close();
        pst.close();
        return false;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public static boolean simpanTransaksiMultiple(List<ItemTransaksi> listBarang, String idPembeli, String idKasir, float totalHarga, float kembalian) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = getConnection();
        if (conn == null) {
            System.out.println("Koneksi database gagal!");
            return false;
        }

        conn.setAutoCommit(false);

        // Generate ID transaksi yang unik
        String idTransaksi = generateTransactionId();
        System.out.println("Generated Transaction ID: " + idTransaksi);

        // 1. Insert ke tabel Transaksi (Header)
        String sqlTransaksi = "INSERT INTO Transaksi (ID_Transaksi, ID_Pembeli, ID_Kasir, Tanggal_Transaksi, Total_Harga, Metode_Pembayaran, Kembalian) VALUES (?, ?, ?, GETDATE(), ?, ?, ?)";
        pstmt = conn.prepareStatement(sqlTransaksi);
        pstmt.setString(1, idTransaksi);
        pstmt.setString(2, idPembeli);
        pstmt.setString(3, idKasir);
        pstmt.setDouble(4, totalHarga);
        pstmt.setString(5, "CASH");
        pstmt.setDouble(6, kembalian);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println("Transaksi header inserted, rows affected: " + rowsAffected);

        // 2. Insert detail untuk setiap item
        pstmt.close();
        String sqlDetail = "INSERT INTO Detail_Transaksi (ID_Transaksi, ID_Produk, Jumlah, Harga_Satuan) VALUES (?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sqlDetail);
        
        for (ItemTransaksi item : listBarang) {
            pstmt.setString(1, idTransaksi);
            pstmt.setString(2, item.getIdBarang());
            pstmt.setInt(3, item.getJumlah());
            pstmt.setDouble(4, item.getHargaSatuan());
            pstmt.addBatch();  // Tambahkan ke batch
        }
        
        int[] batchResults = pstmt.executeBatch();
        System.out.println("Detail transaksi inserted, items: " + batchResults.length);

        // 3. Update stok untuk setiap produk
        pstmt.close();
        String sqlUpdateStok = "UPDATE Produk SET Stok = Stok - ? WHERE ID_Produk = ?";
        pstmt = conn.prepareStatement(sqlUpdateStok);
        
        for (ItemTransaksi item : listBarang) {
            pstmt.setInt(1, item.getJumlah());
            pstmt.setString(2, item.getIdBarang());
            pstmt.addBatch();
        }
        
        int[] stockResults = pstmt.executeBatch();
        System.out.println("Stok updated for items: " + stockResults.length);

        // Commit transaksi
        conn.commit();
        System.out.println("Transaksi berhasil disimpan dengan ID: " + idTransaksi);
        return true;

    } catch (SQLException e) {
        System.out.println("Error menyimpan transaksi: " + e.getMessage());
        e.printStackTrace();

        try {
            if (conn != null) {
                conn.rollback();
                System.out.println("Transaction rolled back");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    // Method untuk update stok produk
    public static boolean updateStokProduk(String idProduk, int jumlahTerjual) {
        try {
            Connection conn = getConnection();
            String query = "UPDATE Produk SET Stok = Stok - ? WHERE ID_Produk = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, jumlahTerjual);
            pst.setString(2, idProduk);
            
            int result = pst.executeUpdate();
            pst.close();
            
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



// Class model untuk Produk
class Produk {
    private String idProduk;
    private String namaProduk;
    private float harga;
    private int stok;
    
    // Getters and Setters
    public String getIdProduk() { return idProduk; }
    public void setIdProduk(String idProduk) { this.idProduk = idProduk; }
    
    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }
    
    public float getHarga() { return harga; }
    public void setHarga(float harga) { this.harga = harga; }
    
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}

// Class model untuk Transaksi
class Transaksi {
    private String idTransaksi;
    private String idPembeli;
    private String idProduk;
    private String idKasir;
    private String kodeTransaksi;
    private java.util.Date tanggalTransaksi;
    private int totalItem;
    private float totalHarga;
    private String metodePembayaran;
    private float kembalian;
    
    // Getters and Setters
    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }
    
    public String getIdPembeli() { return idPembeli; }
    public void setIdPembeli(String idPembeli) { this.idPembeli = idPembeli; }
    
    public String getIdProduk() { return idProduk; }
    public void setIdProduk(String idProduk) { this.idProduk = idProduk; }
    
    public String getIdKasir() { return idKasir; }
    public void setIdKasir(String idKasir) { this.idKasir = idKasir; }
    
    public String getKodeTransaksi() { return kodeTransaksi; }
    public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }
    
    public java.util.Date getTanggalTransaksi() { return tanggalTransaksi; }
    public void setTanggalTransaksi(java.util.Date tanggalTransaksi) { this.tanggalTransaksi = tanggalTransaksi; }
    
    public int getTotalItem() { return totalItem; }
    public void setTotalItem(int totalItem) { this.totalItem = totalItem; }
    
    public float getTotalHarga() { return totalHarga; }
    public void setTotalHarga(float totalHarga) { this.totalHarga = totalHarga; }
    
    public String getMetodePembayaran() { return metodePembayaran; }
    public void setMetodePembayaran(String metodePembayaran) { this.metodePembayaran = metodePembayaran; }
    
    public float getKembalian() { return kembalian; }
    public void setKembalian(float kembalian) { this.kembalian = kembalian; }
}


