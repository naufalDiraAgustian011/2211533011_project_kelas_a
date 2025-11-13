/**
 * */
/**
 * @author nda
 *
 */
/**
 * File konfigurasi modul.
 * Diperbarui untuk Praktikum 7.
 */
module laundryapp {
    // Izin untuk GUI (Swing/AWT) - Sudah ada sejak Praktikum 6
    requires java.desktop;
    
    // TAMBAHAN BARU (Praktikum 7):
    // Izin untuk Database (JDBC) agar bisa koneksi ke MySQL
    requires java.sql;
    
    // TAMBAHAN BARU (Praktikum 7):
    // Izin untuk Logging yang digunakan di CustomerRepo
    requires java.logging;
}