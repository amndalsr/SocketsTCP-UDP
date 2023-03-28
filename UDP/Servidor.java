package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) throws IOException {
        try (
        // cria um socket UDP na porta 1234
        DatagramSocket socket = new DatagramSocket(1234)) {
            System.out.println("Servidor iniciado...");

            while (true) {
                // cria um buffer para receber os dados do cliente
                byte[] buffer = new byte[1024];
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

                // espera a chegada de um pacote do cliente
                socket.receive(pacote);

                // extrai os dados do pacote
                byte[] dados = pacote.getData();
                int tamanho = pacote.getLength();
                InetAddress endereco = pacote.getAddress();
                int porta = pacote.getPort();

                // imprime os dados recebidos
                System.out.println("Recebido: " + new String(dados, 0, tamanho) +
                        " de " + endereco.getHostAddress() + ":" + porta);

                // cria a resposta para o cliente
                String resposta = "Olá, cliente!";
                byte[] dadosResposta = resposta.getBytes();

                // cria um pacote com a resposta e envia para o cliente
                DatagramPacket pacoteResposta = new DatagramPacket(dadosResposta, dadosResposta.length, endereco, porta);
                socket.send(pacoteResposta);
            }
        }
    }
}
