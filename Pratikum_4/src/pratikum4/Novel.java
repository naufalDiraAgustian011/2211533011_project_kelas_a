package pratikum4;

public class Novel extends book { 
    private String genre; 
    public Novel(String title, String author, String genre) {
        super(title, author); 
        this.genre = genre; 
    }

    public String getGenre() { 
        return genre; 
    } 
}
