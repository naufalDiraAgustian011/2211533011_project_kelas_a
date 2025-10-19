package pratikum4;

public class User {
    private String name; 

    public User() {
        this.name = "Pengguna Umum";
    }

    public User(String name) {
        this.name = name; 
    }

   
    public void borrowBook(book book) {
        if (book.isAvailable()) { 
            book.borrowBook(); 
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil dipinjam oleh " + this.name); // [cite: 158, 159]
        } else {
            System.out.println("Maaf " + this.name + ", buku \"" + book.getTitle() + "\" sedang tidak tersedia."); // [cite: 161]
        }
    }

    
    public void returnBook(book book) {
        if (!book.isAvailable()) { 
            book.returnBook(); 
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil dikembalikan."); 
        } else {
            System.out.println("Buku \"" + book.getTitle() + "\" sudah tersedia.");
        }
    }

    
    public void viewBookDetails(book book) {
        System.out.println("Judul: " + book.getTitle()); 
        System.out.println("Penulis: " + book.getAuthor()); 
        System.out.println("Tersedia: " + (book.isAvailable() ? "Ya" : "Tidak")); 

        
        if (book instanceof Novel) {
            Novel novel = (Novel) book; 
            System.out.println("Genre: " + novel.getGenre()); 
        } else if (book instanceof Magazine) {
            Magazine magazine = (Magazine) book;
            System.out.println("Kategori: " + magazine.getCategory());  
        } else if (book instanceof TextBook) {
            TextBook textbook = (TextBook) book;
            System.out.println("Bidang Studi: " + textbook.getStudyField()); 
        }
    }
}