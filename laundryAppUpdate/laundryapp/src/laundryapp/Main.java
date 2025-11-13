package laundryapp;

// UBAH IMPORT: Import LoginFrame
import laundryapp.ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // PERUBAHAN: Menjalankan LoginFrame (Form Login)
                new LoginFrame().setVisible(true);
            }
        });
    }
}