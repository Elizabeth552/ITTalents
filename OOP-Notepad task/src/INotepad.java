public interface INotepad {

    void addText(int page, String title, String text);
    void replaceText(int page, String title, String text);
    void deleteText(int page);
    void previewAllPages();
    boolean searchWord(String word);
    void printAllPagesWithDigits();
}
