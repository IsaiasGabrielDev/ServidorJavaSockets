package Client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int Port = 1234;
        // final String IP = "127.0.0.1";
        final String IP = "45.232.126.249";
        Scanner input = null;
        PrintStream output = null;
        Socket clientSocket = null;
        // solicitar a conexão
        try {
            clientSocket = new Socket(IP, Port);
        } catch (Exception e) {
            System.out.println("Não foi possível estabelecer a conexão");
            System.out.println(e.getMessage());
            return;
        }
        // comunicação
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String texto = scan.nextLine();
            output.println(texto);
            String recebido = null;

            texto = scan.nextLine();
            output.println(texto);
            while(true){
                do{
                    texto = scan.nextLine();
                    output.println(texto);
                }while(!texto.contains("Aguardar"));
                do{
                    recebido = input.nextLine();
                    System.out.println(recebido);
                }while(!recebido.contains("Aguardar"));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // encerrar a conexão
        try {
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Conexão encerrada");
            System.out.println(e.getMessage());
        }
    }
}
