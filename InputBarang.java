package marketplace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class InputBarang extends javax.swing.JPanel {
    MainMarketplace main;
    
    public InputBarang(MainMarketplace main, String nama) {
        this.main = main;
        initComponents();
        setupDefaultValues();
        loadKategoriComboBox();     
    }
    
    // Method baru untuk setup nilai default
    private void setupDefaultValues() {
        // Set tanggal default dengan format yang benar
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        
        jTextField3.setText(currentDate); // Tanggal kirim
        jTextField4.setText(currentDate); // Tanggal terima
        
        // Set placeholder atau nilai default lainnya jika diperlukan
        jTextField1.setText(""); // ID Pengiriman
        jTextField2.setText(""); // ID Barang
        jTextField5.setText(""); // Nama Barang
        jTextField6.setText("1"); // Jumlah default 1
        jTextField7.setText("0"); // Harga default 0
    }
    public void refreshKategori() {
        loadKategoriComboBox();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        jLabel1.setText("INPUT BARANG");
        add(jLabel1);
        jLabel1.setBounds(410, 70, 280, 44);

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel2.setText("ID PENGIRIMAN");
        add(jLabel2);
        jLabel2.setBounds(120, 170, 190, 30);

        jTextField1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        add(jTextField1);
        jTextField1.setBounds(120, 210, 310, 50);

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel3.setText("ID BARANG");
        add(jLabel3);
        jLabel3.setBounds(570, 170, 190, 30);

        jTextField2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        add(jTextField2);
        jTextField2.setBounds(570, 210, 310, 50);

        jLabel4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel4.setText("TANGGAL KIRIM");
        add(jLabel4);
        jLabel4.setBounds(120, 290, 210, 30);

        jTextField3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        add(jTextField3);
        jTextField3.setBounds(120, 330, 310, 50);

        jComboBox1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman", "Obat", "Alat Makan", "Produk Kecantikan", "Perlengkapan Pribadi", "Alat Kebersihan", " ", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(570, 450, 290, 50);

        jLabel5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel5.setText("KATEGORI");
        add(jLabel5);
        jLabel5.setBounds(570, 410, 140, 30);

        jLabel6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel6.setText("TANGGAL TERIMA");
        add(jLabel6);
        jLabel6.setBounds(120, 410, 240, 30);

        jTextField4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField4);
        jTextField4.setBounds(120, 450, 310, 50);

        jLabel7.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel7.setText("NAMA BARANG");
        add(jLabel7);
        jLabel7.setBounds(570, 280, 220, 30);

        jTextField5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField5);
        jTextField5.setBounds(570, 320, 300, 50);

        saveButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton);
        saveButton.setBounds(450, 680, 130, 50);

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel8.setText("JUMLAH");
        add(jLabel8);
        jLabel8.setBounds(570, 530, 110, 30);

        jTextField6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField6);
        jTextField6.setBounds(570, 570, 280, 50);

        backButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton);
        backButton.setBounds(90, 680, 150, 50);

        jLabel9.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel9.setText("HARGA");
        add(jLabel9);
        jLabel9.setBounds(130, 530, 100, 30);

        jTextField7.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField7);
        jTextField7.setBounds(130, 570, 300, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        clearFields();
        MainMarketplace parent = (MainMarketplace) SwingUtilities.getWindowAncestor(this);
        parent.showPage("menu");
    }//GEN-LAST:event_backButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
         saveBarang();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadKategoriComboBox() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Clear existing items first
            jComboBox1.removeAllItems();
            
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed, using default categories");
                setupDefaultCategories();
                return;
            }
            
            String sql = "SELECT ID_Kategori, Nama_Kategori FROM Kategori_Produk ORDER BY Nama_Kategori";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
                String[] additionalCategories = {
                    "KT001 - Makanan",
                    "KT002 - Minuman", 
                    "KT003 - Obat-obatan",
                    "KT004 - Alat Makan",
                    "KT005 - Produk Kecantikan",
                    "KT006 - Perlengkapan Pribadi",
                    "KT007 - Alat Kebersihan",
                    "KT008 - Elektronik",
                    "KT009 - Pakaian",
                    "KT010 - Peralatan Rumah Tangga",
                    "KT011 - Buku & Alat Tulis",
                    "KT012 - Olahraga & Rekreasi"
                };
                
                for (String category : additionalCategories) {
                    jComboBox1.addItem(category);
                    System.out.println("Added default: " + category);
                }
            
            
            System.out.println("Total categories loaded: " + jComboBox1.getItemCount());
            
            // Force UI update
            jComboBox1.revalidate();
            jComboBox1.repaint();
            
        } catch (SQLException e) {
            System.out.println("SQL Error loading categories: " + e.getMessage());
            e.printStackTrace();
            setupDefaultCategories();
        } catch (Exception e) {
            System.out.println("General Error loading categories: " + e.getMessage());
            e.printStackTrace();
            setupDefaultCategories();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void setupDefaultCategories() {
        try {
            jComboBox1.removeAllItems();
            
            // Tambahkan lebih banyak kategori default
            String[] defaultCategories = {
                "KT001 - Makanan",
                "KT002 - Minuman", 
                "KT003 - Obat-obatan",
                "KT004 - Alat Makan",
                "KT005 - Produk Kecantikan",
                "KT006 - Perlengkapan Pribadi",
                "KT007 - Alat Kebersihan",
                "KT008 - Elektronik",
                "KT009 - Pakaian",
                "KT010 - Peralatan Rumah Tangga",
                "KT011 - Buku & Alat Tulis",
                "KT012 - Olahraga & Rekreasi"
            };
            
            System.out.println("Default categories loaded: " + jComboBox1.getItemCount() + " items");
            
            // Set default selection
            if (jComboBox1.getItemCount() > 0) {
                jComboBox1.setSelectedIndex(0);
            }
            
            // Force repaint to ensure UI updates
            jComboBox1.revalidate();
            jComboBox1.repaint();
            
        } catch (Exception e) {
            System.out.println("Error setting up default categories: " + e.getMessage());
            e.printStackTrace();
        }
    } 
      
    private void saveBarang() {
        // Validasi input
        if (!validateInput()) {
            return;
        }
        
        Connection conn = null;
        PreparedStatement pstmtProduk = null;
        PreparedStatement pstmtSupplier = null;
        PreparedStatement pstmtKategori = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database gagal!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            conn.setAutoCommit(false); // Start transaction
            
            // Get kategori ID from combo box
            String selectedKategori = (String) jComboBox1.getSelectedItem();
            if (selectedKategori == null || selectedKategori.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String[] kategoriParts = selectedKategori.split(" - ");
            if (kategoriParts.length < 2) {
                JOptionPane.showMessageDialog(this, "Format kategori tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String kategoriId = kategoriParts[0];
            String namaKategori = kategoriParts[1];
            
            // Insert kategori jika belum ada (SQL Server syntax)
            String sqlKategori = "IF NOT EXISTS (SELECT 1 FROM Kategori_Produk WHERE ID_Kategori = ?) " +
                               "INSERT INTO Kategori_Produk (ID_Kategori, Nama_Kategori) VALUES (?, ?)";
            pstmtKategori = conn.prepareStatement(sqlKategori);
            pstmtKategori.setString(1, kategoriId);
            pstmtKategori.setString(2, kategoriId);
            pstmtKategori.setString(3, namaKategori);
            pstmtKategori.executeUpdate();
            
            // Insert atau update produk (SQL Server syntax)
            String sqlProduk = "IF EXISTS (SELECT 1 FROM Produk WHERE ID_Produk = ?) " +
                              "UPDATE Produk SET Nama_Produk = ?, Harga = ?, Stok = Stok + ?, ID_Kategori = ? WHERE ID_Produk = ? " +
                              "ELSE " +
                              "INSERT INTO Produk (ID_Produk, Nama_Produk, Harga, Stok, ID_Kategori) VALUES (?, ?, ?, ?, ?)";
            
            pstmtProduk = conn.prepareStatement(sqlProduk);
            pstmtProduk.setString(1, jTextField2.getText().trim());
            pstmtProduk.setString(2, jTextField5.getText().trim());
            pstmtProduk.setDouble(3, Double.parseDouble(jTextField7.getText().trim()));
            pstmtProduk.setInt(4, Integer.parseInt(jTextField6.getText().trim()));
            pstmtProduk.setString(5, kategoriId);
            pstmtProduk.setString(6, jTextField2.getText().trim());
            pstmtProduk.setString(7, jTextField2.getText().trim());
            pstmtProduk.setString(8, jTextField5.getText().trim());
            pstmtProduk.setDouble(9, Double.parseDouble(jTextField7.getText().trim()));
            pstmtProduk.setInt(10, Integer.parseInt(jTextField6.getText().trim()));
            pstmtProduk.setString(11, kategoriId);
            pstmtProduk.executeUpdate();
            
            // Insert supplier/pengiriman
            String sqlSupplier = "INSERT INTO Supplier (ID_Pengiriman, Tanggal_Kirim, Tanggal_Terima, ID_Produk) VALUES (?, ?, ?, ?)";
            pstmtSupplier = conn.prepareStatement(sqlSupplier);
            pstmtSupplier.setString(1, jTextField1.getText().trim());
            pstmtSupplier.setString(2, jTextField3.getText().trim());
            pstmtSupplier.setString(3, jTextField4.getText().trim());
            pstmtSupplier.setString(4, jTextField2.getText().trim());
            pstmtSupplier.executeUpdate();
            
            conn.commit(); // Commit transaction
            
            JOptionPane.showMessageDialog(this, "Barang berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmtKategori != null) pstmtKategori.close();
                if (pstmtProduk != null) pstmtProduk.close();
                if (pstmtSupplier != null) pstmtSupplier.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean validateInput() {
        if (jTextField1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Pengiriman tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            return false;
        }
        
        if (jTextField2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Barang tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return false;
        }
        
        if (jTextField5.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Barang tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField5.requestFocus();
            return false;
        }
        
        if (jTextField3.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal Kirim tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
            return false;
        }
        
        if (jTextField4.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal Terima tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocus();
            return false;
        }
        
        try {
            double harga = Double.parseDouble(jTextField7.getText().trim());
            if (harga < 0) {
                JOptionPane.showMessageDialog(this, "Harga tidak boleh negatif!", "Error", JOptionPane.ERROR_MESSAGE);
                jTextField7.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField7.requestFocus();
            return false;
        }
        
        try {
            int stok = Integer.parseInt(jTextField6.getText().trim());
            if (stok < 0) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh negatif!", "Error", JOptionPane.ERROR_MESSAGE);
                jTextField6.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField6.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void clearFields() {
    // Kosongkan semua text field
    jTextField1.setText(""); // ID Pengiriman
    jTextField2.setText(""); // ID Barang
    jTextField5.setText(""); // Nama Barang
    jTextField6.setText("1"); // Jumlah - reset ke default 1
    jTextField7.setText("0"); // Harga - reset ke default 0
    
    // Reset combo box ke index pertama
    if (jComboBox1.getItemCount() > 0) {
        jComboBox1.setSelectedIndex(0);
    }
    
    // Set tanggal ke tanggal hari ini
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());
    jTextField3.setText(currentDate); // Tanggal kirim
    jTextField4.setText(currentDate); // Tanggal terima
    
    // Set focus ke field pertama untuk kemudahan input berikutnya
    jTextField1.requestFocus();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
