package pratikum4;

public class Magazine extends book {
    private String category; 

    public Magazine(String title, String author, String category) {
        super(title, author);
        this.category = category;
    }

    public String getCategory() { 
        return category; 
    }
}