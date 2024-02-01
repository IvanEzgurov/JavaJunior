package org.example.seminar05.seminar05_chatServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            Server server = new Server(serverSocket);
            server.runServer();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
