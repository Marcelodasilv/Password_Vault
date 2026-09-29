
import java.util.Scanner;

public class Password_Master {


    public void passWord(Scanner scan){
        Integer MasterPM = 123;

        System.out.print("Escreva sua senha: ");
        Integer scanNumber = scan.nextInt();
        String scanNumber2 = scan.nextLine();

        if (scanNumber == MasterPM){
            System.out.println("Parabéns, sua senha: " + scanNumber + " está certa!");

        }else if (scanNumber != MasterPM){
            System.out.println("Perdedor, sua senha: " + scanNumber + " está errada.");
            System.exit(0);
        }
    }
}
