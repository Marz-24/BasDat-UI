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
import java.util.ArrayList; // Import for ArrayList

public class DatabaseConnection {
    private static final String URL = "jdbc:jtds:sqlserver://localhost:1433/minimarketDB";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "password123";

    private static Connection connection = null;

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
                produk.setIdKategori(rs.getString("ID_Kategori")); // Tambahkan ini
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS"); // Tambah milidetik agar lebih unik
        String timestamp = sdf.format(new Date());
        return "TX" + timestamp; // Total: 16 karakter (TX + 14 digit)
    }

    public static String generateDetailTransactionId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS"); // Tambah milidetik agar lebih unik
        String timestamp = sdf.format(new Date());
        return "DT" + timestamp; // Total: 16 karakter (DT + 14 digit)
    }

    // Method untuk menyimpan transaksi dan detailnya (multiple items)
    public static boolean simpanTransaksiMultiple(List<ItemTransaksi> listBarang, String idPembeli, String idKasir, float totalHarga, float kembalian, String metodePembayaran) {
        Connection conn = null;
        PreparedStatement pstmtTransaksi = null;
        PreparedStatement pstmtDetail = null;
        PreparedStatement pstmtProduk = null;
        PreparedStatement pstmtPembeli = null; // Tambahkan ini

        try {
            conn = getConnection();
            if (conn == null) {
                System.out.println("Koneksi database gagal!");
                return false;
            }

            conn.setAutoCommit(false); // Start transaction

            // Cek dan insert pembeli jika belum ada
            String checkPembeliSql = "SELECT ID_Pembeli FROM Pembeli WHERE ID_Pembeli = ?";
            pstmtPembeli = conn.prepareStatement(checkPembeliSql);
            pstmtPembeli.setString(1, idPembeli);
            ResultSet rsPembeli = pstmtPembeli.executeQuery();

            if (!rsPembeli.next()) { // Pembeli belum ada, insert baru
                String insertPembeliSql = "INSERT INTO Pembeli (ID_Pembeli, Nama_Pembeli) VALUES (?, ?)";
                pstmtPembeli.close(); // Tutup yang sebelumnya
                pstmtPembeli = conn.prepareStatement(insertPembeliSql);
                pstmtPembeli.setString(1, idPembeli);
                // Asumsi nama pembeli belum ada di JualBarang, bisa ditambahkan jika perlu
                // Untuk sementara, jika tidak ada nama, gunakan "Guest" atau kosong
                String namaPembeli = getNamaPembeli(idPembeli); // Ambil nama dari field atau asumsikan
                if (namaPembeli == null || namaPembeli.isEmpty()) {
                     // Jika tidak ada di DB, kita perlu cara mendapatkan nama pembeli dari JualBarang
                     // Untuk contoh ini, kita asumsikan ID Pembeli sama dengan Nama Pembeli jika tidak ada
                     namaPembeli = "Guest";
                }
                pstmtPembeli.setString(2, namaPembeli); // Anda mungkin perlu menambahkan field nama pembeli di GUI JualBarang
                pstmtPembeli.executeUpdate();
                System.out.println("Pembeli baru di-insert: " + idPembeli);
            }
            rsPembeli.close();
            pstmtPembeli.close();


            // Generate ID transaksi yang unik
            String idTransaksi = generateTransactionId();
            System.out.println("Generated Transaction ID: " + idTransaksi);

            // 1. Insert ke tabel Transaksi (Header)
            String sqlTransaksi = "INSERT INTO Transaksi (ID_Transaksi, ID_Pembeli, ID_Kasir, Kode_Transaksi, Tanggal_Transaksi, Total_Harga, Metode_Pembayaran, Kembalian) VALUES (?, ?, ?, ?, GETDATE(), ?, ?, ?)";
            pstmtTransaksi = conn.prepareStatement(sqlTransaksi);
            pstmtTransaksi.setString(1, idTransaksi);
            pstmtTransaksi.setString(2, idPembeli);
            pstmtTransaksi.setString(3, idKasir);
            pstmtTransaksi.setString(4, "TRX" + System.currentTimeMillis()); // Kode Transaksi
            pstmtTransaksi.setDouble(5, totalHarga);
            pstmtTransaksi.setString(6, metodePembayaran);
            pstmtTransaksi.setDouble(7, kembalian);

            int rowsAffected = pstmtTransaksi.executeUpdate();
            System.out.println("Transaksi header inserted, rows affected: " + rowsAffected);
            pstmtTransaksi.close();

            // 2. Insert detail untuk setiap item ke Detail_Transaksi
            String sqlDetail = "INSERT INTO Detail_Transaksi (ID_Detail_Transaksi, ID_Transaksi, ID_Produk, Jumlah, Harga_Satuan) VALUES (?, ?, ?, ?, ?)";
            pstmtDetail = conn.prepareStatement(sqlDetail);

            // 3. Update stok untuk setiap produk
            String sqlUpdateStok = "UPDATE Produk SET Stok = Stok - ? WHERE ID_Produk = ?";
            pstmtProduk = conn.prepareStatement(sqlUpdateStok);

            for (ItemTransaksi item : listBarang) {
                // Insert Detail_Transaksi
                pstmtDetail.setString(1, generateDetailTransactionId());
                pstmtDetail.setString(2, idTransaksi);
                pstmtDetail.setString(3, item.getIdBarang());
                pstmtDetail.setInt(4, item.getJumlah());
                pstmtDetail.setDouble(5, item.getHargaSatuan());
                pstmtDetail.addBatch();

                // Update Stok
                pstmtProduk.setInt(1, item.getJumlah());
                pstmtProduk.setString(2, item.getIdBarang());
                pstmtProduk.addBatch();
            }

            int[] batchResultsDetail = pstmtDetail.executeBatch();
            System.out.println("Detail transaksi inserted, items: " + batchResultsDetail.length);
            pstmtDetail.close();

            int[] stockResults = pstmtProduk.executeBatch();
            System.out.println("Stok updated for items: " + stockResults.length);
            pstmtProduk.close();


            conn.commit(); // Commit transaction
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
                if (pstmtTransaksi != null) pstmtTransaksi.close();
                if (pstmtDetail != null) pstmtDetail.close();
                if (pstmtProduk != null) pstmtProduk.close();
                if (pstmtPembeli != null) pstmtPembeli.close();
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
            // Gunakan Statement dan ResultSet untuk query umum
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'");

            if (rs.next()) {
                int count = rs.getInt(1);
                rs.close();
                stmt.close();
                return count > 0;
            }
            rs.close();
            stmt.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metode untuk menghapus produk dari database
    public static boolean deleteProduk(String idProduk) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "DELETE FROM Produk WHERE ID_Produk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idProduk);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error menghapus produk: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Class model untuk Produk
    public static class Produk {
        private String idProduk;
        private String namaProduk;
        private float harga;
        private int stok;
        private String idKategori; // Tambahkan ini

        // Getters and Setters
        public String getIdProduk() { return idProduk; }
        public void setIdProduk(String idProduk) { this.idProduk = idProduk; }

        public String getNamaProduk() { return namaProduk; }
        public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

        public float getHarga() { return harga; }
        public void setHarga(float harga) { this.harga = harga; }

        public int getStok() { return stok; }
        public void setStok(int stok) { this.stok = stok; }
        
        public String getIdKategori() { return idKategori; }
        public void setIdKategori(String idKategori) { this.idKategori = idKategori; }
    }

    // Class model untuk Transaksi (tetap seperti semula, tapi pastikan tidak ada ID_Produk)
    public static class Transaksi {
        private String idTransaksi;
        private String idPembeli;
        private String idKasir;
        private String kodeTransaksi;
        private Date tanggalTransaksi;
        private float totalHarga;
        private String metodePembayaran;
        private float kembalian;
        private int totalItem; // Tambahkan ini kembali untuk kenyamanan di Transaksi Model

        // Getters and Setters
        public String getIdTransaksi() { return idTransaksi; }
        public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

        public String getIdPembeli() { return idPembeli; }
        public void setIdPembeli(String idPembeli) { this.idPembeli = idPembeli; }

        public String getIdKasir() { return idKasir; }
        public void setIdKasir(String idKasir) { this.idKasir = idKasir; }

        public String getKodeTransaksi() { return kodeTransaksi; }
        public void setKodeTransaksi(String kodeTransaksi) { this.kodeTransaksi = kodeTransaksi; }

        public Date getTanggalTransaksi() { return tanggalTransaksi; }
        public void setTanggalTransaksi(Date tanggalTransaksi) { this.tanggalTransaksi = tanggalTransaksi; }

        public float getTotalHarga() { return totalHarga; }
        public void setTotalHarga(float totalHarga) { this.totalHarga = totalHarga; }

        public String getMetodePembayaran() { return metodePembayaran; }
        public void setMetodePembayaran(String metodePembayaran) { this.metodePembayaran = metodePembayaran; }

        public float getKembalian() { return kembalian; }
        public void setKembalian(float kembalian) { this.kembalian = kembalian; }
        
        public int getTotalItem() { return totalItem; } // Getter baru
        public void setTotalItem(int totalItem) { this.totalItem = totalItem; } // Setter baru
    }
    
    // Class model untuk Detail_Transaksi
    public static class DetailTransaksi {
        private String idDetailTransaksi;
        private String idTransaksi;
        private String idProduk;
        private String namaProduk; // Untuk memudahkan tampilan
        private int jumlah;
        private float hargaSatuan;

        // Getters and Setters
        public String getIdDetailTransaksi() { return idDetailTransaksi; }
        public void setIdDetailTransaksi(String idDetailTransaksi) { this.idDetailTransaksi = idDetailTransaksi; }

        public String getIdTransaksi() { return idTransaksi; }
        public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

        public String getIdProduk() { return idProduk; }
        public void setIdProduk(String idProduk) { this.idProduk = idProduk; }

        public String getNamaProduk() { return namaProduk; }
        public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

        public int getJumlah() { return jumlah; }
        public void setJumlah(int jumlah) { this.jumlah = jumlah; }

        public float getHargaSatuan() { return hargaSatuan; }
        public void setHargaSatuan(float hargaSatuan) { this.hargaSatuan = hargaSatuan; }
    }


    // Method baru untuk mengambil riwayat transaksi
    public static List<Transaksi> getRiwayatTransaksi() {
        List<Transaksi> riwayat = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT t.ID_Transaksi, t.Tanggal_Transaksi, t.Total_Harga, t.Metode_Pembayaran, " +
                         "p.Nama_Pembeli, k.Nama_Kasir, SUM(dt.Jumlah) AS Total_Item " + // Hitung total item
                         "FROM Transaksi t " +
                         "JOIN Pembeli p ON t.ID_Pembeli = p.ID_Pembeli " +
                         "JOIN Kasir k ON t.ID_Kasir = k.ID_Kasir " +
                         "JOIN Detail_Transaksi dt ON t.ID_Transaksi = dt.ID_Transaksi " + // Join dengan detail
                         "GROUP BY t.ID_Transaksi, t.Tanggal_Transaksi, t.Total_Harga, t.Metode_Pembayaran, p.Nama_Pembeli, k.Nama_Kasir " +
                         "ORDER BY t.Tanggal_Transaksi DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaksi trans = new Transaksi();
                trans.setIdTransaksi(rs.getString("ID_Transaksi"));
                trans.setTanggalTransaksi(rs.getDate("Tanggal_Transaksi"));
                trans.setTotalHarga(rs.getFloat("Total_Harga"));
                trans.setMetodePembayaran(rs.getString("Metode_Pembayaran"));
                trans.setPembeli(new Pembeli(rs.getString("Nama_Pembeli"))); // Model Pembeli perlu diadaptasi
                trans.setKasir(new Kasir(rs.getString("Nama_Kasir")));     // Model Kasir perlu diadaptasi
                trans.setTotalItem(rs.getInt("Total_Item")); // Set total item
                riwayat.add(trans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return riwayat;
    }

    // Method baru untuk mendapatkan detail transaksi
    public static List<DetailTransaksi> getDetailTransaksi(String idTransaksi) {
        List<DetailTransaksi> details = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT dt.ID_Produk, p.Nama_Produk, dt.Jumlah, dt.Harga_Satuan " +
                         "FROM Detail_Transaksi dt " +
                         "JOIN Produk p ON dt.ID_Produk = p.ID_Produk " +
                         "WHERE dt.ID_Transaksi = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idTransaksi);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                DetailTransaksi detail = new DetailTransaksi();
                detail.setIdProduk(rs.getString("ID_Produk"));
                detail.setNamaProduk(rs.getString("Nama_Produk"));
                detail.setJumlah(rs.getInt("Jumlah"));
                detail.setHargaSatuan(rs.getFloat("Harga_Satuan"));
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return details;
    }
    
    // Model Pembeli (Tambahkan di file DatabaseConnection.java atau buat file terpisah)
    public static class Pembeli {
        private String idPembeli;
        private String namaPembeli;

        public Pembeli(String namaPembeli) { // Konstruktor untuk nama saja
            this.namaPembeli = namaPembeli;
        }
        public Pembeli(String idPembeli, String namaPembeli) {
            this.idPembeli = idPembeli;
            this.namaPembeli = namaPembeli;
        }

        public String getIdPembeli() { return idPembeli; }
        public void setIdPembeli(String idPembeli) { this.idPembeli = idPembeli; }
        public String getNamaPembeli() { return namaPembeli; }
        public void setNamaPembeli(String namaPembeli) { this.namaPembeli = namaPembeli; }
    }

    // Model Kasir (Tambahkan di file DatabaseConnection.java atau buat file terpisah)
    public static class Kasir {
        private String idKasir;
        private String namaKasir;
        private String passwordAkun;

        public Kasir(String namaKasir) { // Konstruktor untuk nama saja
            this.namaKasir = namaKasir;
        }
        public Kasir(String idKasir, String namaKasir, String passwordAkun) {
            this.idKasir = idKasir;
            this.namaKasir = namaKasir;
            this.passwordAkun = passwordAkun;
        }

        public String getIdKasir() { return idKasir; }
        public void setIdKasir(String idKasir) { this.idKasir = idKasir; }
        public String getNamaKasir() { return namaKasir; }
        public void setNamaKasir(String namaKasir) { this.namaKasir = namaKasir; }
        public String getPasswordAkun() { return passwordAkun; }
        public void setPasswordAkun(String passwordAkun) { this.passwordAkun = passwordAkun; }
    }
}