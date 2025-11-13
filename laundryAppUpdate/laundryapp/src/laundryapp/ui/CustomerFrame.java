package laundryapp.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

// Import DAO, Model, Builder, dan TableModel
import laundryapp.dao.CustomerRepo;
import laundryapp.model.Customer;
import laundryapp.model.CustomerBuilder;
import laundryapp.table.TableCustomer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Tampilan GUI (jFrame) untuk CRUD Customer.
 * Ini adalah file utama yang menggabungkan semua
 * (Builder, DAO, Singleton, TableModel) menjadi satu.
 */
public class CustomerFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtHp;
    private JTextField txtEmail;
    private JTable tableCustomers;

    // Variabel untuk menyimpan data
    private String selectedId; // Menyimpan ID customer yang dipilih di tabel
    private List<Customer> listCustomer;
    
    // Objek repositori untuk akses database
    private CustomerRepo customerRepo;

    /**
     * Method main untuk testing (bisa juga dijalankan dari Main.java)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor untuk membuat Frame
     */
    public CustomerFrame() {
        // Inisialisasi repo
        customerRepo = new CustomerRepo();
        
        // Pengaturan dasar JFrame
        setTitle("Manajemen Data Pelanggan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE agar tidak menutup aplikasi utama
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Kita gunakan layout Absolut (sesuai PDF)

        // --- Panel Form Input ---
        JPanel panelForm = new JPanel();
        panelForm.setBounds(10, 10, 564, 150);
        contentPane.add(panelForm);
        panelForm.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(10, 10, 46, 14);
        panelForm.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(66, 7, 488, 20);
        panelForm.add(txtNama);
        txtNama.setColumns(10);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(10, 40, 46, 14);
        panelForm.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(66, 37, 488, 20);
        panelForm.add(txtAlamat);
        txtAlamat.setColumns(10);

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setBounds(10, 70, 46, 14);
        panelForm.add(lblNoHp);

        txtHp = new JTextField();
        txtHp.setBounds(66, 67, 200, 20);
        panelForm.add(txtHp);
        txtHp.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(276, 70, 46, 14);
        panelForm.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(322, 67, 232, 20);
        panelForm.add(txtEmail);
        txtEmail.setColumns(10);
        
        // --- Tombol-tombol ---
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(176, 110, 89, 23);
        panelForm.add(btnSimpan);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(275, 110, 89, 23);
        panelForm.add(btnUpdate);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(374, 110, 89, 23);
        panelForm.add(btnHapus);

        JButton btnBatal = new JButton("Batal");
        btnBatal.setBounds(473, 110, 89, 23);
        panelForm.add(btnBatal);

        // --- Panel Tabel Data ---
        JPanel panelTable = new JPanel();
        panelTable.setBounds(10, 170, 564, 280);
        contentPane.add(panelTable);
        panelTable.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 564, 280);
        panelTable.add(scrollPane);

        tableCustomers = new JTable();
        scrollPane.setViewportView(tableCustomers);

        // --- Logika Tombol (Action Listeners) ---

        // Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // IMPLEMENTASI BUILDER PATTERN
                // Membuat objek Customer menggunakan Builder dari data form
                Customer customer = new CustomerBuilder()
                        .setNama(txtNama.getText())
                        .setAlamat(txtAlamat.getText())
                        .setHp(txtHp.getText())
                        .setEmail(txtEmail.getText())
                        .build();

                // Panggil DAO untuk menyimpan
                customerRepo.save(customer);
                
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                loadTable(); // Muat ulang data di tabel
                reset();     // Kosongkan form
            }
        });

        // Tombol Update
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan di-update dari tabel.");
                    return;
                }
                
                // Buat objek Customer baru dengan ID yang sudah ada
                Customer customer = new CustomerBuilder()
                        .setId(selectedId) // Penting: set ID untuk WHERE clause
                        .setNama(txtNama.getText())
                        .setAlamat(txtAlamat.getText())
                        .setHp(txtHp.getText())
                        .setEmail(txtEmail.getText())
                        .build();

                // Panggil DAO untuk update
                customerRepo.update(customer);
                
                JOptionPane.showMessageDialog(null, "Data berhasil di-update!");
                loadTable();
                reset();
            }
        });

        // Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus dari tabel.");
                    return;
                }

                // Konfirmasi penghapusan
                int response = JOptionPane.showConfirmDialog(null, 
                        "Apakah Anda yakin ingin menghapus data ini?", 
                        "Konfirmasi Hapus", 
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    // Panggil DAO untuk delete
                    customerRepo.delete(selectedId);
                    
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                    loadTable();
                    reset();
                }
            }
        });

        // Tombol Batal
        btnBatal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // Event Klik pada Tabel
        tableCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ambil baris yang diklik
                int selectedRow = tableCustomers.getSelectedRow();
                
                // Ambil data dari list berdasarkan baris yang diklik
                Customer cs = listCustomer.get(selectedRow);
                
                // Simpan ID yang dipilih
                selectedId = cs.getId();
                
                // Tampilkan data ke form
                txtNama.setText(cs.getNama());
                txtAlamat.setText(cs.getAlamat());
                txtHp.setText(cs.getHp());
                txtEmail.setText(cs.getEmail());
            }
        });

        // Muat data tabel saat frame pertama kali dibuka
        loadTable();
    }

    /**
     * Method untuk memuat/me-refresh data tabel
     */
    public void loadTable() {
        // Ambil data terbaru dari database
        listCustomer = customerRepo.show();
        // Buat TableModel baru dengan data tersebut
        TableCustomer tc = new TableCustomer(listCustomer);
        // Set model JTable
        tableCustomers.setModel(tc);
    }

    /**
     * Method untuk membersihkan form input
     */
    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtHp.setText("");
        txtEmail.setText("");
        selectedId = null; // Reset ID yang dipilih
    }
}