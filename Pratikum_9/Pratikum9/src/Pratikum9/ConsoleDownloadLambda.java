package Pratikum9;

public class ConsoleDownloadLambda {
    public static void main(String[] args) {
        // Implementasi Runnable menggunakan Lambda Expression
        Runnable downloadTask = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(name + " progress: " + i + "%");
                try {
                    Thread.sleep(500); // Simulasi delay download
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " selesai diunduh!");
        };

        // Membuat 3 Thread yang berjalan paralel
        Thread t1 = new Thread(downloadTask, "File-1");
        Thread t2 = new Thread(downloadTask, "File-2");
        Thread t3 = new Thread(downloadTask, "File-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
