package org.example.seminar05.seminar05_chatClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("input yor name: ");

            String name = scanner.nextLine();

            // информация о текущем адресе на локальном компьютере
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, 5000);

            Client client = new Client(socket, name);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("inetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.ListenForMessage();
            client.sendMessage();

        } catch (UnknownHostException ex){
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
