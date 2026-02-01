package laundryapp.util;

import laundryapp.error.DataTidakValidException;

/**
 * Ini adalah class Utility untuk validasi,
 * mirip dengan ValidationUtil di PDF Anda.
 * Method di sini akan 'melempar' (throw) exception jika data tidak lolos validasi.
 */
public class ValidasiUtil {

    /**
     * Memvalidasi input dari form laundry.
     * @param nama Nama pelanggan
     * @param noTelp Nomor telepon
     * @param berat Berat cucian
     * @throws DataTidakValidException Jika data tidak logis (misal: nama kosong, berat 0)
     * @throws NullPointerException Jika salah satu data adalah null (program error)
     */
    public static void validasiInput(String nama, String noTelp, double berat)
            throws DataTidakValidException, NullPointerException {

        // Cek Nama Pelanggan
        if (nama == null) {
            throw new NullPointerException("Nama pelanggan adalah null");
        }
        if (nama.isBlank()) {
            // Melempar custom exception kita
            throw new DataTidakValidException("Nama pelanggan tidak boleh kosong");
        }

        // Cek Nomor Telepon
        if (noTelp == null) {
            throw new NullPointerException("Nomor telepon adalah null");
        }
        if (noTelp.isBlank()) {
            throw new DataTidakValidException("Nomor telepon tidak boleh kosong");
        }

        // Cek Berat Cucian
        if (berat <= 0) {
            throw new DataTidakValidException("Berat cucian harus lebih dari 0 Kg");
        }
        
        // Jika semua lolos, method selesai tanpa error
    }
}