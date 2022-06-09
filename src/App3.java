public class App3 {

    public static void CSV파일만들기(TexttoCsvFileAble ttc) {
        ttc.process("zipcode.txt", "zipcode3.csv");
    }

    public static void main(String[] args) {
        CSV파일만들기(new TextToCsvFile());
    }
}
