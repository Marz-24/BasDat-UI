package marketplace;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import marketplace.DatabaseConnection;
import marketplace.JualBarang.ItemTransaksi; // Import ItemTransaksi
import java.util.ArrayList;
import java.awt.print.PrinterException;

public class Struck extends javax.swing.JPanel {
    private MainMarketplace main;
    private List<ItemTransaksi> listBarang;
    private String idPembeli;
    private String namaPembeli; // Akan diisi dari JualBarang
    private String idKasir;
    private DefaultTableModel tableModel;
    private float totalBelanja = 0;
    private float jumlahDibayar = 0;
    private float kembalian = 0;
    // private DatabaseConnection DatabaseConnection; // Ini tidak diperlukan karena methodnya static

    public Struck(MainMarketplace main, String nama) {
        this.main = main;
        this.listBarang = new ArrayList<>();
        initComponents();
        setupTable();
        setupEventListeners();
    }
    private void setupTable() {
        tableModel = new DefaultTableModel(
            new String[]{"No", "Nama Barang", "Qty", "Harga", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelBarang.setModel(tableModel);

        tabelBarang.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabelBarang.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelBarang.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelBarang.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabelBarang.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    private void setupEventListeners() {
        fieldJumlahBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hitungKembalian();
            }
        });
    }

    // Ubah parameter setTransactionData untuk menerima namaPembeli
    public void setTransactionData(List<ItemTransaksi> listBarang, String idPembeli, String idKasir, String namaPembeli) {
        this.listBarang = listBarang != null ? new ArrayList<>(listBarang) : new ArrayList<>();
        this.idPembeli = idPembeli;
        this.idKasir = idKasir;
        this.namaPembeli = namaPembeli; // Set nama pembeli dari parameter

        populateStruk();
    }

    private void populateStruk() {
        try {
            tableModel.setRowCount(0);

            SimpleDateFormat refFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            labelRef.setText("REF" + refFormat.format(new Date()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            labelTanggal.setText(dateFormat.format(new Date()));

            String namaKasirStr = DatabaseConnection.getNamaKasir(idKasir);
            labelKasir.setText(namaKasirStr.isEmpty() ? "Unknown" : namaKasirStr);

            // Tampilkan nama pembeli yang sudah ada
            labelPembeli.setText(idPembeli + " - " + (namaPembeli.isEmpty() ? "Guest" : namaPembeli));

            DecimalFormat df = new DecimalFormat("#,##0");
            int totalItemCount = 0;
            float totalHargaAll = 0;

            for (int i = 0; i < listBarang.size(); i++) {
                ItemTransaksi item = listBarang.get(i);
                Object[] rowData = {
                    (i + 1),
                    item.getNamaBarang(),
                    item.getJumlah(),
                    "Rp " + df.format(item.getHargaSatuan()),
                    "Rp " + df.format(item.getTotalHarga())
                };
                tableModel.addRow(rowData);

                totalItemCount += item.getJumlah();
                totalHargaAll += item.getTotalHarga();
            }

            labelTotalItem.setText(String.valueOf(totalItemCount));
            labelTotalHarga.setText("Rp " + df.format(totalHargaAll));
            this.totalBelanja = totalHargaAll;

            fieldJumlahBayar.setText("");
            labelKembalian.setText("Rp 0");
            labelStatus.setText("MENUNGGU PEMBAYARAN");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saat memuat data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void hitungKembalian() {
        try {
            String jumlahBayarText = fieldJumlahBayar.getText().trim();
            if (jumlahBayarText.isEmpty()) {
                labelKembalian.setText("Rp 0");
                return;
            }

            jumlahBayarText = jumlahBayarText.replace("Rp", "").replace(",", "").trim();

            jumlahDibayar = Float.parseFloat(jumlahBayarText);
            kembalian = jumlahDibayar - totalBelanja;

            DecimalFormat df = new DecimalFormat("#,##0");
            labelKembalian.setText("Rp " + df.format(kembalian));

            if (kembalian < 0) {
                labelKembalian.setForeground(java.awt.Color.RED);
                labelStatus.setText("KURANG BAYAR: Rp " + df.format(Math.abs(kembalian)));
                labelStatus.setForeground(java.awt.Color.RED);
            } else {
                labelKembalian.setForeground(java.awt.Color.BLACK);
                labelStatus.setText("PEMBAYARAN SELESAI");
                labelStatus.setForeground(java.awt.Color.GREEN);
            }

        } catch (NumberFormatException e) {
            labelKembalian.setText("Rp 0");
            labelStatus.setText("Format jumlah bayar tidak valid");
            labelStatus.setForeground(java.awt.Color.RED);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelRef = new javax.swing.JLabel();
        labelKasir = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelTotalItem = new javax.swing.JLabel();
        labelTotalHarga = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        labelKembalian = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelPembeli = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        simpanButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        transaksiBaruButton = new javax.swing.JButton();
        labelTanggal = new javax.swing.JLabel();
        fieldJumlahBayar = new javax.swing.JTextField();

        setLayout(null);

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel1.setText("E-MART");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(410, 50, 210, 59);

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 14)); // NOI18N
        jLabel2.setText("Jalan Bandung No. 36a, Penanggungan,\n Kec. Klojen, Kota Malang, Jawa Timur 65113");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(250, 110, 520, 40);

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel3.setText("Ref:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 180, 32, 23);

        labelRef.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelRef.setText("...");
        jPanel1.add(labelRef);
        labelRef.setBounds(150, 180, 240, 23);

        labelKasir.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelKasir.setText("...");
        jPanel1.add(labelKasir);
        labelKasir.setBounds(780, 180, 190, 23);

        jLabel4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel4.setText("Kasir: ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(720, 180, 60, 23);

        jLabel6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel6.setText("Total Item:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(100, 430, 100, 23);

        labelTotalItem.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelTotalItem.setText("...");
        jPanel1.add(labelTotalItem);
        labelTotalItem.setBounds(350, 430, 120, 23);

        labelTotalHarga.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelTotalHarga.setText("...");
        jPanel1.add(labelTotalHarga);
        labelTotalHarga.setBounds(740, 430, 190, 23);

        jLabel7.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel7.setText("Bayar:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(100, 470, 90, 23);

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel8.setText("Kembalian:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(100, 510, 140, 23);

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Barang", "Jumlah Barang", "Harga Satuan", "Jumlah Harga"
            }
        ));
        jScrollPane1.setViewportView(tabelBarang);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(92, 220, 840, 180);

        labelKembalian.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelKembalian.setText("...");
        jPanel1.add(labelKembalian);
        labelKembalian.setBounds(740, 510, 180, 23);

        jLabel9.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel9.setText("Member: ");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(320, 600, 90, 23);

        labelPembeli.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelPembeli.setText("...");
        jPanel1.add(labelPembeli);
        labelPembeli.setBounds(430, 600, 230, 23);

        jLabel10.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel10.setText("TERIMA KASIH TELAH BERBELANJA");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(300, 690, 430, 30);

        labelStatus.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelStatus.setText("...");
        jPanel1.add(labelStatus);
        labelStatus.setBounds(380, 560, 290, 30);

        simpanButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });
        jPanel1.add(simpanButton);
        simpanButton.setBounds(810, 690, 130, 50);

        backButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel1.add(backButton);
        backButton.setBounds(60, 670, 130, 50);

        transaksiBaruButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        transaksiBaruButton.setText("TRANSAKSI BARU");
        transaksiBaruButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiBaruButtonActionPerformed(evt);
            }
        });
        jPanel1.add(transaksiBaruButton);
        transaksiBaruButton.setBounds(20, 730, 240, 50);

        labelTanggal.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        labelTanggal.setText("....");
        jPanel1.add(labelTanggal);
        labelTanggal.setBounds(390, 650, 240, 30);

        fieldJumlahBayar.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jPanel1.add(fieldJumlahBayar);
        fieldJumlahBayar.setBounds(740, 460, 190, 40);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
       MainMarketplace parent = (MainMarketplace) SwingUtilities.getWindowAncestor(this);
       parent.showPage("jualBarang");
    }//GEN-LAST:event_backButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        if (listBarang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada barang untuk disimpan!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jumlahDibayar < totalBelanja) {
            JOptionPane.showMessageDialog(this, "Jumlah pembayaran kurang!",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Ambil metode pembayaran dari item pertama atau tambahkan pilihan di Struck
        String metodePembayaran = "Cash"; // Default atau ambil dari JualBarang
        if (!listBarang.isEmpty()) {
            metodePembayaran = listBarang.get(0).getMetodePembayaran();
        }


        try {
            // Cek apakah tabel exists (ini hanya contoh, sudah di-handle di DatabaseConnection)
            if (!DatabaseConnection.checkTableExists("Detail_Transaksi")) {
                JOptionPane.showMessageDialog(this,
                    "Tabel Detail_Transaksi tidak ditemukan!\n" +
                    "Silakan jalankan script SQL untuk membuat tabel terlebih dahulu.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Simpan transaksi menggunakan method yang sudah diperbaiki
            boolean success = DatabaseConnection.simpanTransaksiMultiple(listBarang, idPembeli, idKasir, totalBelanja, kembalian, metodePembayaran);

            if (success) {
                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan ke database!",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                simpanButton.setEnabled(false); // Disable button setelah sukses
                labelStatus.setText("TRANSAKSI TERSIMPAN");
                labelStatus.setForeground(java.awt.Color.BLUE);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi ke database!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void transaksiBaruButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBaruButtonActionPerformed
        listBarang.clear();
        tableModel.setRowCount(0);
        fieldJumlahBayar.setText("");
        labelTotalItem.setText("0");
        labelTotalHarga.setText("Rp 0");
        labelKembalian.setText("Rp 0");
        labelStatus.setText("MENUNGGU PEMBAYARAN");
        simpanButton.setEnabled(true);

        main.showPage("jualBarang");
    }//GEN-LAST:event_transaksiBaruButtonActionPerformed

public static String generateTransactionId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        String timestamp = sdf.format(new Date());
        return "TX" + timestamp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField fieldJumlahBayar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelKasir;
    private javax.swing.JLabel labelKembalian;
    private javax.swing.JLabel labelPembeli;
    private javax.swing.JLabel labelRef;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTanggal;
    private javax.swing.JLabel labelTotalHarga;
    private javax.swing.JLabel labelTotalItem;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JButton transaksiBaruButton;
    // End of variables declaration//GEN-END:variables

}
