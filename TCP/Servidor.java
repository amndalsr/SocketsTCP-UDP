package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        System.out.println("Servidor aguardando conexões na porta 1234...");

        try {
            // cria um ServerSocket na porta 1234
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.err.println("Não foi possível ouvir na porta 1234.");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Aguardando conexão do cliente...");

        try {
            // aguarda uma conexão de um cliente
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Falha ao aceitar a conexão do cliente.");
            System.exit(1);
        }

        System.out.println("Conexão estabelecida com o cliente " + clientSocket.getInetAddress().getHostName());

        // cria um BufferedReader para ler as mensagens do cliente
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // cria um PrintWriter para enviar respostas de volta ao cliente
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine, outputLine;

        // vai ler a mensagem do cliente e enviar uma resposta
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensagem recebida do cliente: " + inputLine);
            outputLine = "Resposta do servidor: " + inputLine;
            out.println(outputLine);
            System.out.println("Mensagem enviada para o cliente: " + outputLine);
        }

        // fecha os recursos utilizados
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
