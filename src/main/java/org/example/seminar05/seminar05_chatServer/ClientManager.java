package org.example.seminar05.seminar05_chatServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                // чтение данных
                messageFromClient = bufferedReader.readLine();
                if(messageFromClient == null){
                    // для macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                String[] messageFromClientArray = messageFromClient.split(" ", 3);

                if(messageFromClientArray[1].charAt(0) == '@'){
                    // Отправка личных данных
                    String name = messageFromClientArray[1].substring( 1);
                    String message = messageFromClientArray[0] + " " + messageFromClientArray[2];
                    System.out.println("name:" + name);
                    System.out.println("message" + message);
                    privateMessage(message, name);
                } else {
                    // Отправка данных всем слушателям
                    broadcastMessage(messageFromClient);
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void privateMessage(String message, String name) {
        for (ClientManager client : clients) {
            try {
                if (client.name.equals(name) && message != null) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }
            catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }


    private void broadcastMessage(String messageFromClient) {
        for(ClientManager client: clients){
            try{
                if(!client.name.equals(name)&& messageFromClient != null){
                    client.bufferedWriter.write(messageFromClient);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);

            }
        }
    }

}
