package laundryapp.error;

/**
 * Ini adalah class Custom Exception kita.
 * Dibuat mirip dengan ValidationException di PDF Anda.
 * Kita akan 'throw' exception ini jika ada data input yang tidak valid
 * (misal: nama kosong, berat 0).
 */
public class DataTidakValidException extends Exception {

    /**
     * Constructor yang menerima pesan error.
     * Pesan ini akan kita tampilkan di JOptionPane.
     * @param message Pesan error yang spesifik.
     */
    public DataTidakValidException(String message) {
        // Meneruskan pesan error ke class induk (Exception)
        super(message);
    }
}
