package laundryapp.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * (Opsional tapi Direkomendasikan)
 * * Main Frame (Menu Utama)
 * Ini akan menjadi jendela utama yang membuka form lain,
 * yaitu LaundryForm (dari Praktikum 6) dan CustomerFrame (Praktikum 7).
 */
public class MainFrame extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setTitle("Sistem Manajemen Laundry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 2, 20, 20)); // Layout 1 baris, 2 kolom

        JButton btnTransaksi = new JButton("Transaksi Laundry");
        btnTransaksi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Buka LaundryForm (dari Praktikum 6)
                LaundryForm laundryForm = new LaundryForm();
                laundryForm.setVisible(true);
            }
        });
        btnTransaksi.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnTransaksi);

        JButton btnPelanggan = new JButton("Data Pelanggan");
        btnPelanggan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Buka CustomerFrame (dari Praktikum 7)
                CustomerFrame customerFrame = new CustomerFrame();
                customerFrame.setVisible(true);
            }
        });
        btnPelanggan.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnPelanggan);
    }
}