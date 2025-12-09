package Pratikum9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DownloadManagerApp extends JFrame {

    // Komponen GUI
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JButton btnStart;

    public DownloadManagerApp() {
        // Setup Window (JFrame)
        setTitle("Download Manager App");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar
        setLayout(null); // Menggunakan Absolute Layout

        // --- Label File 1 ---
        JLabel lblFile1 = new JLabel("File 1");
        lblFile1.setBounds(20, 20, 50, 20);
        add(lblFile1);

        // Progress Bar 1
        progressBar1 = new JProgressBar(0, 100);
        progressBar1.setBounds(80, 20, 280, 20);
        progressBar1.setStringPainted(true); 
        progressBar1.setForeground(new Color(51, 153, 255)); 
        add(progressBar1);

        // --- Label File 2 ---
        JLabel lblFile2 = new JLabel("File 2");
        lblFile2.setBounds(20, 60, 50, 20);
        add(lblFile2);

        // Progress Bar 2
        progressBar2 = new JProgressBar(0, 100);
        progressBar2.setBounds(80, 60, 280, 20);
        progressBar2.setStringPainted(true);
        progressBar2.setForeground(new Color(51, 153, 255));
        add(progressBar2);

        // --- Label File 3 ---
        JLabel lblFile3 = new JLabel("File 3");
        lblFile3.setBounds(20, 100, 50, 20);
        add(lblFile3);

        // Progress Bar 3
        progressBar3 = new JProgressBar(0, 100);
        progressBar3.setBounds(80, 100, 280, 20);
        progressBar3.setStringPainted(true);
        progressBar3.setForeground(new Color(51, 153, 255));
        add(progressBar3);

        // --- Tombol Downloading ---
        // INI BAGIAN PENTING YANG SEBELUMNYA HILANG/NULL:
        btnStart = new JButton("Downloading"); 
        btnStart.setBounds(240, 150, 120, 30);
        add(btnStart);

        // Action Listener (Baru boleh dipanggil setelah btnStart = new JButton...)
        btnStart.addActionListener((ActionEvent e) -> {
            startDownload();
        });
    }

    // Method untuk memulai proses download secara paralel
    private void startDownload() {
        // Non-aktifkan tombol agar tidak diklik berkali-kali
        btnStart.setEnabled(false);
        btnStart.setText("Process...");

        // Reset progress bar
        progressBar1.setValue(0);
        progressBar2.setValue(0);
        progressBar3.setValue(0);

        // Jalankan Thread untuk masing-masing progress bar
        new Thread(() -> runDownload(progressBar1, 50)).start();  
        new Thread(() -> runDownload(progressBar2, 70)).start();  
        new Thread(() -> runDownload(progressBar3, 30)).start();  
    }

    // Logika download simulasi
    private void runDownload(JProgressBar bar, int sleepTime) {
        try {
            for (int i = 0; i <= 100; i++) {
                // Update nilai progress bar (harus dalam EDT untuk best practice, tapi ini aman untuk simulasi simple)
                final int progress = i;
                SwingUtilities.invokeLater(() -> bar.setValue(progress));
                
                // Simulasi delay waktu download
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DownloadManagerApp frame = new DownloadManagerApp();
            frame.setVisible(true);
        });
    }
}