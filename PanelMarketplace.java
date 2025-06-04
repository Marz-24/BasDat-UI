package marketplace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PanelMarketplace extends javax.swing.JPanel {
    MainMarketplace main;
    private String currentKasirId;
    private String currentKasirName;
   
    public PanelMarketplace(MainMarketplace main, String nama) {
        this.main = main;
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        judul = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        judul2 = new javax.swing.JLabel();
        textFieldID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        exitButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setLayout(null);

        judul.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        judul.setText("E-MART");
        add(judul);
        judul.setBounds(420, 80, 190, 59);

        loginButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        add(loginButton);
        loginButton.setBounds(420, 490, 200, 70);

        judul2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        judul2.setText("PASSWORD");
        add(judul2);
        judul2.setBounds(440, 320, 140, 40);

        textFieldID.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        add(textFieldID);
        textFieldID.setBounds(350, 230, 340, 50);

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel3.setText("INPUT ID");
        add(jLabel3);
        jLabel3.setBounds(450, 180, 120, 40);

        password.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        add(password);
        password.setBounds(350, 380, 340, 50);

        exitButton.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        exitButton.setText("EXIT");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        add(exitButton);
        exitButton.setBounds(755, 673, 150, 60);
        add(background);
        background.setBounds(0, 0, 1000, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
        
    }//GEN-LAST:event_exitButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String kasirId = textFieldID.getText().trim();
        String passwordInput = new String(password.getPassword()).trim();
        
        if (kasirId.isEmpty() || passwordInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID dan Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (authenticateKasir(kasirId, passwordInput)) {
            // Login berhasil, bersihkan field dan pindah ke menu
            textFieldID.setText("");
            password.setText("");
            main.setCurrentKasir(currentKasirId, currentKasirName);
            main.showPage("menu");
        } else {
            JOptionPane.showMessageDialog(this, "ID atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            password.setText(""); // Clear password field
        }
    }//GEN-LAST:event_loginButtonActionPerformed

     private boolean authenticateKasir(String kasirId, String passwordInput) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database gagal!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            String sql = "SELECT ID_Kasir, Nama_Kasir FROM Kasir WHERE ID_Kasir = ? AND Password_Akun = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kasirId);
            pstmt.setString(2, passwordInput);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                currentKasirId = rs.getString("ID_Kasir");
                currentKasirName = rs.getString("Nama_Kasir");
                JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang " + currentKasirName, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel judul2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField textFieldID;
    // End of variables declaration//GEN-END:variables
}
