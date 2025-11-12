package laundryapp.ui;

// Import semua yang diperlukan untuk Swing (GUI)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Import class exception dan utilitas kita
import laundryapp.error.DataTidakValidException;
import laundryapp.util.ValidasiUtil;

/**
 * Ini adalah tampilan GUI (jFrame) utama untuk aplikasi laundry.
 * Mirip dengan LoginFrame di PDF Anda.
 * Di sinilah kita akan mengimplementasikan try-catch.
 */
public class LaundryForm extends JFrame {

    // Komponen GUI
    private JTextField txtNama;
    private JTextField txtNoTelp;
    private JTextField txtBerat;
    private JButton btnSimpan;
    private JLabel lblTotalHarga;

    // Harga per Kg
    private final double HARGA_PER_KG = 7000;

    public LaundryForm() {
        // Pengaturan dasar jFrame
        setTitle("Aplikasi Laundry Sederhana");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Tampilkan di tengah layar
        
        // Panggil method untuk inisialisasi komponen
        initUI();
    }

    private void initUI() {
        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Panel untuk form input
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 baris, 2 kolom
        
        // Inisialisasi komponen
        txtNama = new JTextField();
        txtNoTelp = new JTextField();
        txtBerat = new JTextField();
        btnSimpan = new JButton("Simpan Transaksi");
        lblTotalHarga = new JLabel("Total Harga: Rp 0");
        lblTotalHarga.setFont(new Font("Arial", Font.BOLD, 14));

        // Tambahkan komponen ke formPanel
        formPanel.add(new JLabel("Nama Pelanggan:"));
        formPanel.add(txtNama);
        formPanel.add(new JLabel("Nomor Telepon:"));
        formPanel.add(txtNoTelp);
        formPanel.add(new JLabel("Berat Cucian (Kg):"));
        formPanel.add(txtBerat);
        formPanel.add(lblTotalHarga); // Label total harga

        // Tambahkan formPanel ke bagian tengah
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Tambahkan tombol simpan ke bagian bawah
        mainPanel.add(btnSimpan, BorderLayout.SOUTH);
        
        // Tambahkan panel utama ke jFrame
        add(mainPanel);

        // Tambahkan Aksi untuk Tombol "Simpan"
        // Ini adalah bagian terpenting, mirip dengan di PDF Anda
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil method untuk memproses data
                prosesSimpan();
            }
        });
    }

    /**
     * Method ini dipanggil ketika tombol "Simpan" diklik.
     * Di sinilah kita akan menerapkan try-catch-finally.
     */
    private void prosesSimpan() {
        // 1. Ambil data dari text field
        String nama = txtNama.getText();
        String noTelp = txtNoTelp.getText();
        String beratStr = txtBerat.getText();
        
        try {
            // --- BLOK TRY ---
            // Kode yang mungkin menyebabkan exception
            
            // 2. Coba konversi berat ke double (bisa melempar NumberFormatException)
            double berat;
            try {
                berat = Double.parseDouble(beratStr);
            } catch (NumberFormatException ex) {
                // Kita ubah jadi exception kita sendiri agar seragam
                throw new DataTidakValidException("Format berat tidak valid, masukkan angka (misal: 3.5)");
            }

            // 3. Panggil method validasi dari ValidasiUtil
            // Ini bisa melempar DataTidakValidException atau NullPointerException
            ValidasiUtil.validasiInput(nama, noTelp, berat);

            // 4. Jika lolos validasi (tidak ada exception), lakukan "Business Logic"
            double totalHarga = berat * HARGA_PER_KG;
            
            // Tampilkan hasil
            lblTotalHarga.setText(String.format("Total Harga: Rp %.0f", totalHarga));
            System.out.println("Data berhasil diproses: " + nama);
            
            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, 
                "Data Berhasil Disimpan!\nTotal Harga: Rp " + totalHarga, 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Bersihkan form
            txtNama.setText("");
            txtNoTelp.setText("");
            txtBerat.setText("");
            lblTotalHarga.setText("Total Harga: Rp 0");

        } catch (DataTidakValidException | NullPointerException exception) {
            // --- BLOK CATCH ---
            // Tangkap exception yang sudah kita definisikan
            // (DataTidakValidException dan NullPointerException)
            
            System.out.println("Data tidak valid: " + exception.getMessage());
            
            // Tampilkan pesan error ke pengguna menggunakan JOptionPane
            JOptionPane.showMessageDialog(this, 
                "Input Gagal: " + exception.getMessage(), 
                "Error Validasi", 
                JOptionPane.ERROR_MESSAGE);
            
        } finally {
            // --- BLOK FINALLY ---
            // Kode ini akan SELALU dieksekusi,
            // baik terjadi error ataupun tidak.
            
            System.out.println("Proses validasi dan penyimpanan selesai.");
        }
    }
}