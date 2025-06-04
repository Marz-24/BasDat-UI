package marketplace;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JualBarang extends javax.swing.JPanel {
    MainMarketplace main;
    private DefaultTableModel tableModel;
    private List<ItemTransaksi> listBarang;
    private String idKasir = "113";
    
    public JualBarang(MainMarketplace main, String nama) {
        this.main = main;
        this.listBarang = new ArrayList<>();
        initComponents();
        setupTable();
    }
    
    private void setupTable() {
        tableModel = new DefaultTableModel(
            new String[]{"Nomor", "Nama Barang", "Jumlah Barang", "Harga Satuan", "Total Harga", "Metode Pembayaran"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kembaliButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        buatStruck = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        jLabel1.setText("JUAL BARANG");
        add(jLabel1);
        jLabel1.setBounds(410, 70, 260, 44);

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel2.setText("ID PEMBELI");
        add(jLabel2);
        jLabel2.setBounds(140, 150, 160, 30);

        jTextField1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField1);
        jTextField1.setBounds(140, 190, 320, 50);

        jLabel5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel5.setText("ID BARANG");
        add(jLabel5);
        jLabel5.setBounds(140, 360, 160, 30);

        jTextField2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2);
        jTextField2.setBounds(140, 390, 320, 50);

        jLabel4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel4.setText("JUMLAH BARANG");
        add(jLabel4);
        jLabel4.setBounds(140, 460, 220, 30);

        jTextField4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        add(jTextField4);
        jTextField4.setBounds(140, 490, 320, 50);

        saveButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton);
        saveButton.setBounds(540, 680, 150, 50);

        jTable1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nomor", "Nama Barang", "Jumlah Barang", "Metode Pembayaran"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(480, 160, 480, 230);

        kembaliButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        kembaliButton.setText("BACK");
        kembaliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliButtonActionPerformed(evt);
            }
        });
        add(kembaliButton);
        kembaliButton.setBounds(50, 680, 150, 50);

        deleteButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton);
        deleteButton.setBounds(630, 420, 150, 50);

        updateButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        add(updateButton);
        updateButton.setBounds(810, 420, 150, 50);

        buatStruck.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        buatStruck.setText("RECEIPT STRUCK");
        buatStruck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buatStruckActionPerformed(evt);
            }
        });
        add(buatStruck);
        buatStruck.setBounds(720, 680, 240, 50);

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel3.setText("METODE PEMBAYARAN");
        add(jLabel3);
        jLabel3.setBounds(140, 560, 270, 30);

        jComboBox1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "QRIS", "Transfer Bank", " " }));
        add(jComboBox1);
        jComboBox1.setBounds(140, 600, 320, 50);

        jLabel6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel6.setText("NAMA PEMBELI");
        add(jLabel6);
        jLabel6.setBounds(140, 260, 190, 30);

        jTextField3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(jTextField3);
        jTextField3.setBounds(140, 292, 310, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            listBarang.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            updateTableNumbers();
            JOptionPane.showMessageDialog(this, "Item berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            try {
                String idBarang = jTextField2.getText().trim();
                String jumlahStr = jTextField4.getText().trim();
                String metodePembayaran = jComboBox1.getSelectedItem().toString();
                
                if (idBarang.isEmpty() || jumlahStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Mohon isi ID Barang dan Jumlah!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int jumlah = Integer.parseInt(jumlahStr);
                Produk produk = DatabaseConnection.getProduk(idBarang);
                
                if (produk == null) {
                    JOptionPane.showMessageDialog(this, "Barang tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (produk.getStok() < jumlah) {
                    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Stok tersedia: " + produk.getStok(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Update item
                ItemTransaksi item = listBarang.get(selectedRow);
                item.setIdBarang(idBarang);
                item.setNamaBarang(produk.getNamaProduk());
                item.setJumlah(jumlah);
                item.setHargaSatuan(produk.getHarga());
                item.setTotalHarga(jumlah * produk.getHarga());
                item.setMetodePembayaran(metodePembayaran);
                
                // Update table
                tableModel.setValueAt(produk.getNamaProduk(), selectedRow, 1);
                tableModel.setValueAt(jumlah, selectedRow, 2);
                tableModel.setValueAt(produk.getHarga(), selectedRow, 3);
                tableModel.setValueAt(jumlah * produk.getHarga(), selectedRow, 4);
                tableModel.setValueAt(metodePembayaran, selectedRow, 5);
                
                clearFields();
                JOptionPane.showMessageDialog(this, "Item berhasil diupdate!");
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        try {
        String idPembeli = jTextField1.getText().trim();
        String namaPembeli = jTextField3.getText().trim(); // Tambahkan validasi nama
        String idBarang = jTextField2.getText().trim();
        String jumlahStr = jTextField4.getText().trim();
        String metodePembayaran = jComboBox1.getSelectedItem().toString();
        
        // Validasi input yang lebih lengkap
        if (idPembeli.isEmpty() || namaPembeli.isEmpty() || idBarang.isEmpty() || jumlahStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon isi semua field!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int jumlah;
        try {
            jumlah = Integer.parseInt(jumlahStr);
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Debug: Cek koneksi database
        System.out.println("Mencari produk dengan ID: " + idBarang);
        
        // Ambil data produk dari database
        Produk produk = DatabaseConnection.getProduk(idBarang);
        if (produk == null) {
            JOptionPane.showMessageDialog(this, 
                "Barang dengan ID " + idBarang + " tidak ditemukan!\n" +
                "Pastikan ID barang benar dan ada di database.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Debug: Tampilkan info produk
        System.out.println("Produk ditemukan: " + produk.getNamaProduk() + ", Stok: " + produk.getStok());
        
        // Cek stok
        if (produk.getStok() < jumlah) {
            JOptionPane.showMessageDialog(this, 
                "Stok tidak mencukupi!\n" +
                "Stok tersedia: " + produk.getStok() + "\n" +
                "Jumlah diminta: " + jumlah, 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buat item transaksi
        ItemTransaksi item = new ItemTransaksi();
        item.setIdBarang(idBarang);
        item.setNamaBarang(produk.getNamaProduk());
        item.setJumlah(jumlah);
        item.setHargaSatuan(produk.getHarga());
        item.setTotalHarga(jumlah * produk.getHarga());
        item.setMetodePembayaran(metodePembayaran);
        
        // Tambah ke list dan table (pastikan listBarang sudah diinisialisasi)
        if (listBarang == null) {
            listBarang = new ArrayList<>();
        }
        
        listBarang.add(item);
        Object[] rowData = {
            listBarang.size(),
            produk.getNamaProduk(),
            jumlah,
            produk.getHarga(),
            jumlah * produk.getHarga(),
            metodePembayaran
        };
        tableModel.addRow(rowData);
        
        // Clear fields
        clearFields();
        
        JOptionPane.showMessageDialog(this, 
            "Item berhasil ditambahkan!\n" +
            "Barang: " + produk.getNamaProduk() + "\n" +
            "Jumlah: " + jumlah + "\n" +
            "Total: Rp " + (jumlah * produk.getHarga()));
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Terjadi kesalahan: " + e.getMessage() + "\n" +
            "Silakan coba lagi atau hubungi administrator.", 
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Untuk debugging
    }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void buatStruckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buatStruckActionPerformed
        // TODO add your handling code here:
        if (listBarang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada barang yang ditambahkan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idPembeli = jTextField1.getText().trim();
        if (idPembeli.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon isi ID Pembeli!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Proses semua transaksi
            boolean allSuccess = true;
            String idTransaksiBase = generateTransactionId();
            
            for (int i = 0; i < listBarang.size(); i++) {
                ItemTransaksi item = listBarang.get(i);
                
                // Buat objek transaksi
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(idTransaksiBase + "_" + (i + 1));
                transaksi.setIdPembeli(idPembeli);
                transaksi.setIdProduk(item.getIdBarang());
                transaksi.setIdKasir(idKasir);
                transaksi.setKodeTransaksi("TRX" + System.currentTimeMillis());
                transaksi.setTanggalTransaksi(new Date());
                transaksi.setTotalItem(item.getJumlah());
                transaksi.setTotalHarga(item.getTotalHarga());
                transaksi.setMetodePembayaran(item.getMetodePembayaran());
                transaksi.setKembalian(0); // Kembalian bisa dihitung nanti
                
                // Simpan transaksi ke database
                if (!DatabaseConnection.simpanTransaksi(transaksi)) {
                    allSuccess = false;
                    break;
                }
                
                // Update stok produk
                if (!DatabaseConnection.updateStokProduk(item.getIdBarang(), item.getJumlah())) {
                    allSuccess = false;
                    break;
                }
            }
            
            if (allSuccess) {
                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
                
                // Pass data ke struck
                if (main instanceof MainMarketplace) {
                    MainMarketplace marketplace = (MainMarketplace) main;
                    marketplace.setTransactionData(listBarang, idPembeli, idKasir);
                }
                
                main.showPage("struck");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_buatStruckActionPerformed

    private void kembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliButtonActionPerformed
        // TODO add your handling code here:
        MainMarketplace parent = (MainMarketplace) SwingUtilities.getWindowAncestor(this);
        parent.showPage("menu");
    }//GEN-LAST:event_kembaliButtonActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    public void testDatabaseConnection() {
    try {
        // Test koneksi dengan mengambil produk tertentu
        String testId = "P001"; // Ganti dengan ID yang ada di database
        Produk produk = DatabaseConnection.getProduk(testId);
        
        if (produk != null) {
            System.out.println("Database connection OK");
            System.out.println("Test produk: " + produk.getNamaProduk());
        } else {
            System.out.println("Produk dengan ID " + testId + " tidak ditemukan");
        }
    } catch (Exception e) {
        System.out.println("Error koneksi database: " + e.getMessage());
        e.printStackTrace();
    }
}

    // Perbaikan method clearFields
    private void clearFields() {
    jTextField2.setText("");
    jTextField4.setText("");
    jComboBox1.setSelectedIndex(0);
    // Jangan clear jTextField1 (ID Pembeli) dan jTextField3 (Nama Pembeli)
    // karena biasanya satu pembeli beli banyak barang
}
    
    private void updateTableNumbers() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(i + 1, i, 0);
        }
    }
    
    public static String generateTransactionId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        String timestamp = sdf.format(new Date());
        return "TX" + timestamp; // Total: 13 karakter (TXN + 10 digit)
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buatStruck;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton kembaliButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

class ItemTransaksi {
    private String idBarang;
    private String namaBarang;
    private int jumlah;
    private float hargaSatuan;
    private float totalHarga;
    private String metodePembayaran;
    
    // Getters and Setters
    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }
    
    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public int getJumlah() {return jumlah;}
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public float getHargaSatuan () {
        return hargaSatuan;
    }
    
    public void setHargaSatuan(float hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
    
    public float getTotalHarga () {
        return totalHarga;
    }
    
    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }
    
    public String getMetodePembayaran () {
        return metodePembayaran;
    }
    
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
