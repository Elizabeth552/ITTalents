public class ElectronicSecuredNotepad extends SecureNotepad implements IElectronicDevice {

    boolean isStarted;

    ElectronicSecuredNotepad(String title, int numberOfPages, String password) {
        super(title, numberOfPages, password);
    }

    @Override
    public void start() {
        isStarted = true;
    }

    @Override
    public void stop() {
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void addText(int page, String title, String text) {
        if(isStarted) {
            super.addText(page, title, text);
        } else {
            System.out.println("The device is turned off!");
        }
    }

    @Override
    public void replaceText(int page, String title, String text) {
        if(isStarted) {
            super.replaceText(page, title, text);
        } else {
            System.out.println("The device is turned off!");
        }
    }

    @Override
    public void deleteText(int page) {
        if(isStarted) {
            super.deleteText(page);
        } else {
            System.out.println("The device is turned off!");
        }
    }

    @Override
    public void previewAllPages() {
        if(isStarted) {
            super.previewAllPages();
        } else {
            System.out.println("The device is turned off!");
        }
    }

    @Override
    public void printAllPagesWithDigits() {
        if(isStarted) {
            super.printAllPagesWithDigits();
        } else {
            System.out.println("The device is turned off!");
        }
    }

    @Override
    public boolean searchWord(String word) {
        if(isStarted) {
            return super.searchWord(word);
        } else {
            System.out.println("The device is turned off!");
        }
        return false;
    }
}
