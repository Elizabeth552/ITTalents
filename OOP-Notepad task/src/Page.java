import java.util.regex.Pattern;

public class Page {

    private String title = "";
    private String text = "";

    public void addText(String text) {
        if (text != null && !text.isEmpty()) {
            this.text = text;
        } else {
            System.out.println("Invalid text");
        }
    }

    public void addTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            System.out.println("Invalid title");
        }
    }

    public void deleteText() {
        this.text = "";
        this.title = "";
    }

    public void previewPage() {

        System.out.println("Title ");
        System.out.println("Text ");
        System.out.println();
    }

    public boolean searchWord(String word) {
        return text.toLowerCase().contains(word.toLowerCase().trim());
    }

    public boolean containsDigits() {
        return Pattern.compile("[0-9]").matcher(text).find();
    }
}
