package pratikum4;

public class TextBook extends book {
    private String studyField; 

    public TextBook(String title, String author, String studyField) {
        super(title, author);
        this.studyField = studyField;
    }

    public String getStudyField() { 
        return studyField; 
    }
}


