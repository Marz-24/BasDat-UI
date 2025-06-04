package marketplace;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JFrame;

public class MainMarketplace extends javax.swing.JFrame {
    private final CardLayout cardLayout;
    private String currentKasirId;
    private String currentKasirName;
    private JualBarang jualBarangPanel;
    private Struck struckPanel;
    
    // Data transaksi yang akan dikirim ke struk
    private List<ItemTransaksi> transactionData;
    private String customerID;
    private String cashierID;


    public MainMarketplace() {
        cardLayout = new CardLayout();
        initComponents();
        jPanel1.setLayout(cardLayout);
        
        jPanel1.add(new PanelMarketplace(this, "panel"), "panel");
        jPanel1.add(new Menu(this, "menu"), "menu");
        jPanel1.add(new JualBarang(this, "jualBarang"), "jualBarang");
        jPanel1.add(new InputBarang(this, "inputBarang"), "inputBarang");
        jPanel1.add(new Struck(this, "struck"), "struck");
        
        // Tambahkan mainPanel ke frame utama
        this.add(jPanel1);
        
        // Pengaturan dasar JFrame
        this.setTitle("E-Mart");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null); // posisi tengah layar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        // Tampilkan halaman pertama
        this.showPage("panel");
    }
    
    public void showPage (String nama) {
        // Tampilkan halaman
        cardLayout.show(jPanel1, nama);
    }
    
    // Method untuk set kasir yang sedang login
    public void setCurrentKasir(String kasirId, String kasirName) {
        this.currentKasirId = kasirId;
        this.currentKasirName = kasirName;
    }
    
    // Method untuk get kasir ID
    public String getCurrentKasirId() {
        return currentKasirId;
    }
    
    // Method untuk get kasir name
    public String getCurrentKasirName() {
        return currentKasirName;
    }
    
    // Method untuk logout
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
    
    // Method untuk set data transaksi dari JualBarang
    public void setTransactionData(List<ItemTransaksi> transactionData, String customerID, String cashierID) {
        this.transactionData = transactionData;
        this.customerID = customerID;
        this.cashierID = cashierID;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
