package laundryapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * IMPLEMENTASI SINGLETON PATTERN
 * * Class ini bertanggung jawab untuk koneksi database.
 * Pola Singleton memastikan bahwa hanya ada *satu* instance (objek) 
 * dari koneksi (Connection) yang dibuat selama aplikasi berjalan.
 */
public class Database {

    // 1. Variabel 'conn' dibuat 'static' untuk menyimpan satu-satunya instance koneksi.
    private static Connection conn;

    /**
     * 2. Method 'koneksi()' adalah 'static' sehingga bisa dipanggil
     * langsung dari mana saja (cth: Database.koneksi()).
     * * @return Connection
     */
    public static Connection koneksi() {
        
        // 3. Pengecekan utama Singleton:
        //    Jika koneksi BELUM ADA (masih null), maka buat koneksi baru.
        if (conn == null) {
            try {
                // Load driver JDBC untuk MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Buat koneksi baru dan simpan ke variabel static 'conn'
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/laundry_apps", // URL Database
                    "root", // Username DB
                    ""      // Password DB (kosong jika pakai XAMPP default)
                );
            } catch (Exception e) {
                // Tampilkan pesan jika koneksi gagal
                JOptionPane.showMessageDialog(null, "Koneksi database gagal: " + e.getMessage());
                return null;
            }
        }
        
        // 4. Jika koneksi SUDAH ADA (bukan null), 
        //    langsung kembalikan koneksi yang sudah ada tersebut.
        return conn;
    }
}