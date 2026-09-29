import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class Menu {

    private final ArrayList<String> listaSenhas = new ArrayList<>();
    private final ArrayList<String> listaDescarrega = new ArrayList<>();
    private static final String PathName = "C:\\Users\\Marcelo\\Desktop\\DadosList.txt";
    private static final File fl = new File(PathName);
    private static final Password_Master pm = new Password_Master();
    private static final Random r = new Random();
    private static int ns1;
    private static int ns2;
    private static String ns3;

    public void mn(Scanner scan){

        String scanNumber;

        do {
            System.out.println("----- PASSWOLD COLLECTION ACESS ----- \n");
            System.out.println("--->  Bloquear Password Master;");
            System.out.println("--->  Gerar senha;");
            System.out.println("--->  Pesquisar;");
            System.out.println("--->  Adicionar;");
            System.out.println("--->  Remover;");
            System.out.println("--->  Editar;");
            System.out.println("--->  Listar;");
            System.out.println("--->  Salvar;");
            System.out.println("--->  Limpar;");
            System.out.println("--->  Carregar;");
            System.out.println("--->  Sair.");
            System.out.print("Resposta: ");

            scanNumber = scan.nextLine().trim().toLowerCase();

            if (!scanNumber.equals("sair")) {
                operationList(scanNumber, scan);
            }

        } while(!scanNumber.equals("sair"));

        System.out.println("Saindo [...]");
    }

    public void operationList (String scanNumber, Scanner scan){
        Predicate<ArrayList<String>> predicate = texto -> texto.isEmpty();

        try {
            FileWriter fw = new FileWriter(fl, true);
            Scanner scanner = new Scanner(fl);

            switch (scanNumber) {

                case "adicionar":
                    boolean primeiraE = false;
                    boolean segundaE = false;
                    boolean terceiraE = false;
                    boolean quartaE = false;

                    System.out.print("Adicione sua senha:  ");
                    String ns = scan.nextLine();

                    for(int i = 0; i < ns.length(); i++){
                        char cc = ns.charAt(i);

                        if (cc >= 'a' && cc <= 'z'){
                            primeiraE = true;
                        }  else if (cc >= 'A' && cc <= 'Z'){
                            segundaE = true;
                        }  else if (cc >= '0' && cc <= '9'){
                            terceiraE = true;
                        }  else{
                            quartaE = true;
                        }
                    }
                    if (!primeiraE){
                        System.out.println("Sua senha não tem letras minusculas!");
                    }if (!segundaE){
                    System.out.println("Sua senha não tem letras maiusculas!");
                    }if (!terceiraE){
                    System.out.println("Sua senha não tem números!");
                    }if (!quartaE){
                    System.out.println("Sua senha não tem simbolos!");
                    }

                    if(primeiraE && segundaE && terceiraE && quartaE){
                        System.out.println("Senha permitida!");
                        listaSenhas.add(ns);
                    }

                    break;

                case "remover":
                    if (listaSenhas.isEmpty()) {
                        System.out.println("Você precisa adicionar algo antes!");
                    } else {
                        System.out.print("Remova a senha:  ");
                        ns1 = scan.nextInt();
                        scan.nextLine();

                        if (ns1 >= 0 && ns1 < listaSenhas.size()) {
                            listaSenhas.remove(ns1);
                            System.out.println("Senha removida!");
                        } else {
                            System.out.println("Posição inválida!");
                        }
                    }
                    break;

                case "pesquisar":
                    System.out.print("Pesquise sua senha (índice):  ");
                    ns2 = scan.nextInt();
                    scan.nextLine();

                    if (ns2 >= 0 && ns2 < listaSenhas.size()) {
                        System.out.println("Essa posição pertence a senha " + listaSenhas.get(ns2) + ".");
                    } else {
                        System.out.println("Posição inválida!");
                    }
                    break;

                case "editar":
                    System.out.print("Digite o índice que quer editar: ");
                    int n = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Adicione sua nova senha: ");
                    ns3 = scan.nextLine();

                    if (n >= 0 && n < listaSenhas.size()) {
                        listaSenhas.set(n, ns3);
                        System.out.println("Senha alterada!");
                    } else {
                        System.out.println("Posição inválida!");
                    }
                    break;

                case "listar":
                    if (predicate.test(listaSenhas)) {
                        System.out.println("Você precisa adicionar algo antes!");
                    } else {
                        System.out.println("Lista:");
                        for (int i = 0; i < listaSenhas.size(); i++) {
                            System.out.println("[" + i + "] " + listaSenhas.get(i));
                        }
                    }
                    break;

                case "gerar":
                    int a = r.nextInt(900 * 120);
                    System.out.print("Senha Gerada! \n");
                    listaSenhas.add(String.valueOf(a));
                    break;

                case "bloquear":
                    pm.passWord(scan);
                    break;

                case "salvar":
                    for (String item : listaSenhas) {
                        for(int i = 0; i < item.length(); i++) {
                            char caracter = item.charAt(i);
                            int mudaCaracter = caracter + 3;
                            char b = (char) mudaCaracter;
                            fw.write(b);
                        }
                        fw.write(System.lineSeparator());
                    }
                    fw.flush();
                    System.out.println("Lista salva com sucesso!");
                    break;

                case "limpar":
                    FileWriter clear = new FileWriter(PathName, false);
                    break;

                case "carregar":

                    if (!fl.exists()) {
                        fl.createNewFile();
                    }
                    listaSenhas.clear();

                    try (Scanner leitorArquivo = new Scanner(fl)) {
                        while (leitorArquivo.hasNextLine()) {
                            String linha = leitorArquivo.nextLine();
                            StringBuilder descriptografado = new StringBuilder();

                            for (int i = 0; i < linha.length(); i++) {
                                char caracter = linha.charAt(i);
                                int mudaCaracter = caracter - 3;
                                char b = (char) mudaCaracter;
                                descriptografado.append(b);
                            }
                            listaSenhas.add(descriptografado.toString());
                        }
                    }
                    System.out.println("Carregamos a lista!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            fw.close();
            scanner.close();

        } catch(IOException e){
            System.out.println("ERRO de Arquivo: " + e.getMessage());
        }
    }
}
