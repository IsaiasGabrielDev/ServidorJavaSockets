package Server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.sound.sampled.Port;

public class Server {
    public static void main(String[] args) {
        final int Port = 1234;
        ServerSocket serverSocket;
        Socket clientSocket = null;
        Scanner input = null;
        PrintStream output = null;
        Scanner scan = null;

        // criar o socket
        try {
            serverSocket = new ServerSocket(Port);
        } catch (Exception e) {
            System.out.println("Porta em uso");
            System.out.println(e.getMessage());
            return;
        }
        // esperar a conexão
        try {
            System.out.println("Aguardando conexão...");
            clientSocket = serverSocket.accept();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // comunicação
        scan = new Scanner(System.in);
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());
            String recebido = input.nextLine();
            System.out.println("Recebido: " + recebido);
            String texto = null;
            while(true){
                do{
                    recebido = input.nextLine();
                    System.out.println("Recebido: " + recebido);
                }while(!recebido.contains("Aguardar"));
                do{
                    texto = scan.nextLine();
                    output.println(texto);
                }while(!texto.contains("Aguardar"));
        }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Erro na comunicação");
        }


        // encerrar a conexão
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}