package marketplace;

import javax.swing.SwingUtilities;

public class Menu extends javax.swing.JPanel {
    MainMarketplace main;

    public Menu(MainMarketplace main, String nama) {
        this.main = main;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputButton = new javax.swing.JButton();
        judul = new javax.swing.JLabel();
        jualButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        listBarang = new javax.swing.JButton();
        riwayatTransaksi = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        inputButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        inputButton.setText("INPUT BARANG");
        inputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputButtonActionPerformed(evt);
            }
        });
        add(inputButton);
        inputButton.setBounds(410, 200, 220, 70);

        judul.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        judul.setText("MENU");
        add(judul);
        judul.setBounds(470, 100, 110, 44);

        jualButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jualButton.setText("JUAL BARANG");
        jualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jualButtonActionPerformed(evt);
            }
        });
        add(jualButton);
        jualButton.setBounds(410, 380, 220, 70);

        logoutButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        logoutButton.setText("LOG OUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        add(logoutButton);
        logoutButton.setBounds(410, 560, 220, 70);

        listBarang.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        listBarang.setText("LIST PRODUK");
        listBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listBarangActionPerformed(evt);
            }
        });
        add(listBarang);
        listBarang.setBounds(410, 290, 220, 70);

        riwayatTransaksi.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 15)); // NOI18N
        riwayatTransaksi.setText("RIWAYAT TRANSAKSI");
        riwayatTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayatTransaksiActionPerformed(evt);
            }
        });
        add(riwayatTransaksi);
        riwayatTransaksi.setBounds(410, 470, 220, 70);
        add(background);
        background.setBounds(0, 0, 1000, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void inputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputButtonActionPerformed
        // TODO add your handling code here:
        main.showPage("inputBarang");
    }//GEN-LAST:event_inputButtonActionPerformed

    private void jualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jualButtonActionPerformed
        // TODO add your handling code here:
        main.showPage("jualBarang");
    }//GEN-LAST:event_jualButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        MainMarketplace parent = (MainMarketplace) SwingUtilities.getWindowAncestor(this);
        parent.showPage("panel");
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void listBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listBarangActionPerformed
        main.showPage("listProduk");
    }//GEN-LAST:event_listBarangActionPerformed

    private void riwayatTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayatTransaksiActionPerformed
        main.showPage("riwayatTransaksi");
    }//GEN-LAST:event_riwayatTransaksiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton inputButton;
    private javax.swing.JButton jualButton;
    private javax.swing.JLabel judul;
    private javax.swing.JButton listBarang;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton riwayatTransaksi;
    // End of variables declaration//GEN-END:variables
}
