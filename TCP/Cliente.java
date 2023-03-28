package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // cria um socket para se conectar ao servidor na porta 1234
            socket = new Socket("localhost", 1234);
            System.out.println("Cliente conectado ao servidor na porta 1234.");
            // cria um PrintWriter para enviar mensagens para o servidor
            System.out.print("Digite uma mensagem: ");
            out = new PrintWriter(socket.getOutputStream(), true);
            // cria um BufferedReader para ler as respostas do servidor
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Endereço do servidor inválido.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não foi possível se conectar ao servidor.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        // envia mensagens para o servidor e recebe as respostas
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Mensagem enviada para o servidor: " + userInput);
            System.out.println("Resposta do servidor: " + in.readLine());
        }

        // fecha os recursos utilizados
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
