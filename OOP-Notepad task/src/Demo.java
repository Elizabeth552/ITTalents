public class Demo {

    public static void main(String[] args) {

        SecureNotepad sn = new SecureNotepad("Desi", 5, "Avalsise1976");
        sn.addText(1, "Pizza Peperoni", "recepta");
        sn.addText(2, "Pizza Meat Mania", "recepta");
        sn.previewAllPages();
        System.out.println("Pizza!!!!");


    }
}
