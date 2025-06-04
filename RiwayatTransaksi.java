package marketplace;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class RiwayatTransaksi extends javax.swing.JPanel {
    private MainMarketplace main;
    private DefaultTableModel transactionTableModel;
    private DefaultTableModel detailTableModel;

    public RiwayatTransaksi(MainMarketplace main, String nama) {
        this.main = main;
        initComponents();
        setupTransactionTable();
        setupDetailTable();
        loadRiwayatTransaksi();
    }

    private void setupTransactionTable() {
        transactionTableModel = new DefaultTableModel(
            new String[]{"ID Transaksi", "Tanggal", "Total Item", "Total Harga", "Pembeli", "Kasir", "Metode Pembayaran"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTableTransaksi.setModel(transactionTableModel);

        // Add listener to show details on row click
        jTableTransaksi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = jTableTransaksi.getSelectedRow();
                if (selectedRow != -1) {
                    String idTransaksi = (String) transactionTableModel.getValueAt(selectedRow, 0);
                    loadDetailTransaksi(idTransaksi);
                }
            }
        });
    }

    private void setupDetailTable() {
        detailTableModel = new DefaultTableModel(
            new String[]{"ID Produk", "Nama Produk", "Jumlah", "Harga Satuan"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTableDetail.setModel(detailTableModel);
    }

    public void loadRiwayatTransaksi() {
        transactionTableModel.setRowCount(0); // Clear table
        List<DatabaseConnection.Transaksi> riwayat = DatabaseConnection.getRiwayatTransaksi();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (DatabaseConnection.Transaksi trans : riwayat) {
            Object[] row = {
                trans.getIdTransaksi(),
                sdf.format(trans.getTanggalTransaksi()),
                trans.getTotalItem(),
                trans.getTotalHarga(),
                trans.getPembeli().getNamaPembeli(), // Asumsi Anda punya getter di model Transaksi
                trans.getKasir().getNamaKasir(),     // Asumsi Anda punya getter di model Transaksi
                trans.getMetodePembayaran()
            };
            transactionTableModel.addRow(row);
        }
        detailTableModel.setRowCount(0); // Clear detail table when main table is reloaded
    }

    private void loadDetailTransaksi(String idTransaksi) {
        detailTableModel.setRowCount(0); // Clear detail table
        List<DatabaseConnection.DetailTransaksi> details = DatabaseConnection.getDetailTransaksi(idTransaksi);

        for (DatabaseConnection.DetailTransaksi detail : details) {
            Object[] row = {
                detail.getIdProduk(),
                detail.getNamaProduk(),
                detail.getJumlah(),
                detail.getHargaSatuan()
            };
            detailTableModel.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDetail = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        jLabel1.setText("RIWAYAT TRANSAKSI");
        add(jLabel1);
        jLabel1.setBounds(300, 50, 420, 44);

        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableTransaksi);

        add(jScrollPane1);
        jScrollPane1.setBounds(40, 130, 920, 250);

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel2.setText("DETAIL TRANSAKSI");
        add(jLabel2);
        jLabel2.setBounds(40, 400, 270, 30);

        jTableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableDetail);

        add(jScrollPane2);
        jScrollPane2.setBounds(40, 440, 920, 200);

        refreshButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        add(refreshButton);
        refreshButton.setBounds(810, 680, 150, 50);

        backButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton);
        backButton.setBounds(40, 680, 150, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        loadRiwayatTransaksi();
        JOptionPane.showMessageDialog(this, "Riwayat transaksi berhasil diperbarui.");
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        main.showPage("menu");
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDetail;
    private javax.swing.JTable jTableTransaksi;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}