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

public class chatServer{
    private ServerSocket ss; 
    private Socket incoming;
    private String ServerMsg;
    
    ArrayList<HandleSocket>clients = new ArrayList<>();
    
    public chatServer()  {
        try {
            ss = new ServerSocket(6000);
            
            while(true){
                incoming = ss.accept();
                HandleSocket handleSocket = new HandleSocket(this, incoming);
                handleSocket.start();
                clients.add(handleSocket);
            }
            
        } catch (Exception e) {
            System.out.println("There is error in the application: " + e);
        }
    }
    
    public void showChat(String message){
        System.out.println(message);
        broadCast(message);
    }
    
    public void broadCast(String msg){
        for(HandleSocket client: clients){
            client.sendMessage(msg);
        }
    }
    
    public static void main(String[] args) {
        chatServer chatServer1 = new chatServer();
    }
    
//    public void getChat() throws IOException {
//        BufferedReader chatFromClient = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
//        String chat = chatFromClient.readLine();
//        System.out.println("Client: " + chat);
////        txtChatDisplay.append("Client: " + chat + "\n");
//    }
    
}
