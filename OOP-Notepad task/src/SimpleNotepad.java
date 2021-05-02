public class SimpleNotepad implements INotepad {

    private Page[] pages;
    private int numberOfPages;
    private String title;

    SimpleNotepad(String title, int numberOfPages) {
        if (isValidText(title)) {
            this.title = title;
        }
        if (numberOfPages > 0) {
            this.numberOfPages = numberOfPages;
        }
        pages = new Page[numberOfPages];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = new Page();
        }
        System.out.println("A simple notepad is created with title " + title + " and " + numberOfPages + " pages!");
    }

    @Override
    public void addText(int page, String title, String text) {
        if (isValid(page, title, text)) {
            System.out.println("Adding text!");
            pages[page - 1].addTitle(title);
            pages[page - 1].addText(text);
            System.out.println("New text and title are added to page " + page);
        } else {
            System.out.println("Invalid text or/and page!");
        }
        System.out.println();
    }

    @Override
    public void replaceText(int page, String title, String text) {
        System.out.println("Replacing text!");
        if (isValid(page, title, text)) {
            pages[page - 1].deleteText();
            pages[page - 1].addText(text);
            pages[page - 1].addTitle(title);
            System.out.println("The text and title on page " + page + " are replaced!");
        } else {
            System.out.println("Invalid text or/and page!");
        }
        System.out.println();
    }

    @Override
    public void deleteText(int page) {
        System.out.println("Deleting text");
        if (isValidPage(page)) {
            pages[page - 1].deleteText();
            System.out.println("The text on page " + page + " is deleted");
        } else {
            System.out.println("Invalid page");
        }
        System.out.println();
    }

    @Override
    public void previewAllPages() {
        System.out.println("==========PREVIEW==========");
        for (int i = 0; i < pages.length; i++) {
                System.out.println("Page " + (i + 1));
                pages[i].previewPage();
        }
    }

    @Override
    public boolean searchWord(String word) {
        boolean containsWord = false;
        if (word != null) {
            for (int i = 0; i < pages.length; i++) {
                if (pages[i].containsDigits()) {
                    containsWord = true;
                    break;
                }
            }
        }
        return containsWord;
    }

    @Override
    public void printAllPagesWithDigits() {
        boolean containsDigits = false;
        for (int i = 0; i < pages.length; i++) {
            if (pages[i].containsDigits()) {
                pages[i].previewPage();
                containsDigits = true;
            }
        }
        if (!containsDigits) {
            System.out.println("The text doesn't have any pages, containing digits");
        }
    }

    protected boolean isValidText(String text) {
        return text != null && !text.isEmpty();
    }

    protected boolean isValidPage(int page) {
        return page > 0 && page <= pages.length;
    }

    protected boolean isValid(int page, String title, String text) {
        return isValidPage(page) && isValidText(title) && isValidText(text);
    }
}
