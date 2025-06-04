package marketplace;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JFrame;

public class MainMarketplace extends javax.swing.JFrame {
    private final CardLayout cardLayout;
    private String currentKasirId;
    private String currentKasirName;
    private JualBarang jualBarangPanel; // Anda tidak menggunakan ini secara langsung untuk navigasi, tapi tidak masalah
    private Struck struckPanel; // Anda tidak menggunakan ini secara langsung untuk navigasi, tapi tidak masalah

    // Data transaksi yang akan dikirim ke struk
    private List<JualBarang.ItemTransaksi> transactionData; // Ubah ke JualBarang.ItemTransaksi
    private String customerID;
    private String cashierID;
    private String customerNameForStruck; // Tambahkan ini

    public MainMarketplace() {
        cardLayout = new CardLayout();
        initComponents();
        jPanel1.setLayout(cardLayout);

        jPanel1.add(new PanelMarketplace(this, "panel"), "panel");
        jPanel1.add(new Menu(this, "menu"), "menu");
        jualBarangPanel = new JualBarang(this, "jualBarang"); // Inisialisasi di sini
        jPanel1.add(jualBarangPanel, "jualBarang");
        jPanel1.add(new InputBarang(this, "inputBarang"), "inputBarang");
        struckPanel = new Struck(this, "struck"); // Inisialisasi di sini
        jPanel1.add(struckPanel, "struck");
        
        // PENAMBAHAN: Panel untuk daftar produk
        jPanel1.add(new ListProduk(this, "listProduk"), "listProduk");
        // PENAMBAHAN: Panel untuk riwayat transaksi
        jPanel1.add(new RiwayatTransaksi(this, "riwayatTransaksi"), "riwayatTransaksi");

        this.add(jPanel1);

        this.setTitle("E-Mart");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.showPage("panel");
    }

    public void showPage (String nama) {
        // Jika pindah ke struck, set datanya dulu
        if ("struck".equals(nama) && struckPanel != null) {
            struckPanel.setTransactionData(this.transactionData, this.customerID, this.cashierID, this.customerNameForStruck);
        } else if ("jualBarang".equals(nama) && jualBarangPanel != null) {
             // Set current kasir ID saat kembali ke jual barang
             jualBarangPanel.idKasir = this.currentKasirId;
        }
        cardLayout.show(jPanel1, nama);
    }

    public void setCurrentKasir(String kasirId, String kasirName) {
        this.currentKasirId = kasirId;
        this.currentKasirName = kasirName;
    }

    public String getCurrentKasirId() {
        return currentKasirId;
    }

    public String getCurrentKasirName() {
        return currentKasirName;
    }

    public void logout() {
        this.currentKasirId = null;
        this.currentKasirName = null;
        this.showPage("panel");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMarketplace().setVisible(true);
            }
        });
    }
    
    public void setTransactionData(List<JualBarang.ItemTransaksi> transactionData, String customerID, String cashierID) { // Ubah ke JualBarang.ItemTransaksi
        this.transactionData = transactionData;
        this.customerID = customerID;
        this.cashierID = cashierID;
    }
    
    // Getter for transaction data (for Struck panel)
    public List<JualBarang.ItemTransaksi> getTransactionData() { // Ubah ke JualBarang.ItemTransaksi
        return transactionData;
    }
    
    // Setter for customer name (to be passed to Struck panel)
    public void setCustomerNameForStruck(String name) {
        this.customerNameForStruck = name;
    }
    
    // Getter for customer name (for Struck panel)
    public String getCustomerNameForStruck() {
        return customerNameForStruck;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
