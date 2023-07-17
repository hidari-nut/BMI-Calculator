/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class chatServerBak {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public chatServerBak(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            System.out.println("Chat server started on port " + port);
        } catch (IOException e) {
            System.out.println("Error starting the chat server on port " + port);
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            } catch (IOException e) {
                System.out.println("Error accepting client connection");
                e.printStackTrace();
            }
        }
    }

    public void broadcastMessage(String message, ClientHandler excludeClient) {
        for (ClientHandler client : clients) {
            if (client != excludeClient) {
                client.sendMessage(message);
            }
        }
    }

    private class ClientHandler extends Thread {

        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Error creating input/output streams for client: " + clientSocket);
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    System.out.println("Received message from client: " + clientSocket + " - " + clientMessage);
                    broadcastMessage(clientMessage, this);
                }
            } catch (IOException e) {
                System.out.println("Error reading client message: " + clientSocket);
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                    clientSocket.close();
                    clients.remove(this);
                    System.out.println("Client disconnected: " + clientSocket);
                } catch (IOException e) {
                    System.out.println("Error closing client connection: " + clientSocket);
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }

    public static void main(String[] args) {
        int port = 8888; // Change this to the desired port number
        chatServerBak server = new chatServerBak(port);
        server.start();
    }
}
