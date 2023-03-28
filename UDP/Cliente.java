package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // cria um socket UDP
        DatagramSocket socket = new DatagramSocket();

        // obtém o endereço IP do servidor
        InetAddress endereco = InetAddress.getByName("localhost");

        // cria um buffer para enviar os dados ao servidor
        byte[] buffer = new byte[1024];

        try (
        // obtém a mensagem do usuário
        Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite uma mensagem: ");
            String mensagem = scanner.nextLine();

            // converte a mensagem em bytes e coloca no buffer
            buffer = mensagem.getBytes();
        }
        // cria um pacote com os dados e envia para o servidor
        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, endereco, 1234);
        socket.send(pacote);

        // cria um buffer para receber a resposta do servidor
        byte[] bufferResposta = new byte[1024];
        DatagramPacket pacoteResposta = new DatagramPacket(bufferResposta, bufferResposta.length);

        // espera a chegada da resposta do servidor
        socket.receive(pacoteResposta);

        // extrai os dados da resposta
        byte[] dadosResposta = pacoteResposta.getData();
        int tamanhoResposta = pacoteResposta.getLength();
        String resposta = new String(dadosResposta, 0, tamanhoResposta);

        // imprime a resposta do servidor
        System.out.println("Resposta do servidor: " + resposta);

        // fecha o socket
        socket.close();
    }
}

