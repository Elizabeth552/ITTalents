import java.util.Scanner;

public class SecureNotepad extends SimpleNotepad implements ISecureNotepad {

    private String password;

    SecureNotepad(String title, int numberOfPages, String password) {
        super(title, numberOfPages);
        this.password = password;
    }

    static SecureNotepad makeSecureNotepad(String title, int numberOfPages, String password) {
        if (password.length() >= 5
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")) {
            System.out.println("A Secured notepad is created with secured password!");
            return new SecureNotepad(title, numberOfPages, password);
        }
        return null;
    }

    @Override
    public boolean checkPassword() {
        String pass = "";
        System.out.println("Please enter your password: ");
        Scanner sc = new Scanner(System.in);
        do {
            pass = sc.nextLine().trim();
            if (pass.equals("e")) {
                System.out.println("Wrong pass, can't help it");
                break;
            }
            if (!pass.equals(password)) {
                System.out.println("Wrong pass! Try again or press 'e' for exit!");
            }

        } while (!pass.equals(password));
        return pass.equals(password);
    }

    @Override
    public void addText(int page, String title, String text) {
        System.out.println("For adding text, ");
        if (checkPassword()) {
            super.addText(page, title, text);
        }
    }

    @Override
    public void deleteText(int page) {
        System.out.println("For deleting text, ");
        if (checkPassword()) {
            super.deleteText(page);
        }
    }

    @Override
    public void replaceText(int page, String title, String text) {
        if (checkPassword()) {
            super.replaceText(page, title, text);
        }
    }

    @Override
    public void previewAllPages() {
        if (checkPassword()) {
            super.previewAllPages();
        }
    }
}
