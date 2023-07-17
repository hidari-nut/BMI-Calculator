/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author User
 */
public class HandleSocket extends Thread {

    chatServer parent;
    Socket client;
    DataOutputStream out;
    BufferedReader in;

    public HandleSocket(chatServer _parent, Socket _client) {
        this.parent = _parent;
        this.client = _client;

        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error in HandleSocket: " + e);
        }
    }

    //To send a chat message.
    public void sendMessage(String message) {
        try {
            out.writeBytes(message + "\n");
        } catch (Exception e) {
            System.out.println("Error in SendMessage: " + e);
        }
    }

    //To Receive messages from certain clients
    public void run() {
        //Always accept message
        while (true) {
            try {
                String message = in.readLine();
                parent.showChat(message);
            } catch (Exception e) {
                System.out.println("Error in HandleSocket.run(): " + e);
            }
        }
    }
}
