package pratikum4;

public class Main {
    public static void main(String[] args) {
       
        book novel = new Novel("Laskar Pelangi", "Andrea Hirata", "Drama"); 
        book magazine = new Magazine("National Geographic", "Various Authors", "Science"); 
        book textbook = new TextBook("Pemrograman Java", "Anonimous", "Informatika"); 
        

        User user = new User("Superman"); 
        
        System.out.println("=== Detail Buku ===");
        user.viewBookDetails(novel); 
        System.out.println(); 
        user.viewBookDetails(magazine); 
        System.out.println(); 
        user.viewBookDetails(textbook); 
        System.out.println(); 

        
        System.out.println("=== Proses Peminjaman Buku ===");
        user.borrowBook(novel); 
        user.borrowBook(magazine); 

        
        System.out.println("\nStatus Buku Setelah Dipinjam:");
        System.out.println(novel.getTitle() + " tersedia: " + novel.isAvailable()); 
        System.out.println(magazine.getTitle() + " tersedia: " + magazine.isAvailable()); 

        
        System.out.println("\n=== Proses Pengembalian Buku ===");
        user.returnBook(novel); 

        
        System.out.println("\nStatus Buku Setelah Dikembalikan:");
        System.out.println(novel.getTitle() + " tersedia: " + novel.isAvailable()); 
    }
}
