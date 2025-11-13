package laundryapp;

import laundryapp.ui.LaundryForm;
import javax.swing.SwingUtilities;

/**
 * Class utama untuk menjalankan aplikasi.
 * Fungsinya hanya untuk membuat dan menampilkan LaundryForm.
 */
public class Main {

    public static void main(String[] args) {
        // Menjalankan GUI di thread yang benar (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Buat object LaundryForm dan tampilkan
                new LaundryForm().setVisible(true);
            }
        });
    }
}