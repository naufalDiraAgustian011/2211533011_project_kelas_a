package pratikum4;

public class book {
    private String title;
    private String author; 
    private boolean isAvailable; 

    // Constructor
    public book(String title, String author) {
        this.title = title; 
        this.author = author; 
        this.isAvailable = true; 
    }

    // Getter methods (for accessing private data) [cite: 100]
    public String getTitle() { 
        return title; 
    } 

    public String getAuthor() { 
        return author; 
    } 

    public boolean isAvailable() { 
        return isAvailable; 
    }

    // Methods to modify private data (like setters) [cite: 100]
    public void borrowBook() { 
        this.isAvailable = false; 
    } 

    public void returnBook() { 
        this.isAvailable = true; 
    } 
}
