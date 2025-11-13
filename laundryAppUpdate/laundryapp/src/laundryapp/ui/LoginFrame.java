package laundryapp.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Import yang diperlukan
import laundryapp.error.DataTidakValidException; 
import laundryapp.dao.UserRepo;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    
    // Variabel Repo
    private UserRepo userRepo;

    // Main method untuk testing Frame ini sendiri
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginFrame() {
        // Inisialisasi Repo
        userRepo = new UserRepo();
        
        setTitle("Login Sistem Laundry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblJudul = new JLabel("SELAMAT DATANG");
        lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
        lblJudul.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblJudul.setBounds(10, 20, 414, 25);
        contentPane.add(lblJudul);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(60, 70, 80, 14);
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(150, 67, 220, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(60, 110, 80, 14);
        contentPane.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 107, 220, 20);
        contentPane.add(txtPassword);
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.setBounds(150, 150, 220, 30);
        contentPane.add(btnLogin);
    }
    
    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            // 1. Validasi input kosong
            if (username.isBlank() || password.isBlank()) {
                throw new DataTidakValidException("Username dan password tidak boleh kosong!");
            }
            
            // 2. Cek ke database via Repo
            boolean loginBerhasil = userRepo.authenticate(username, password);

            if (loginBerhasil) {
                JOptionPane.showMessageDialog(null, "Login Berhasil!");
                
                // Buka MainFrame (Menu Utama)
                new MainFrame().setVisible(true);
                
                // Tutup LoginFrame
                this.dispose();
                
            } else {
                throw new DataTidakValidException("Username atau password salah.");
            }
            
        } catch (DataTidakValidException ex) {
            JOptionPane.showMessageDialog(this, "Login Gagal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}