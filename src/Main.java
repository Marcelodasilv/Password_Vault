import java.util.Scanner;

public class Main {

    private static final Menu menu = new Menu();
    private static final Password_Master pm = new Password_Master();

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        pm.passWord(scan);
        menu.mn(scan);

    }
}