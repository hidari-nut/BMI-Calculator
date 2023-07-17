
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author mputu
 */
public class ChatUI extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form ChatUI
     */
    private Socket clientSocket;
    private Thread t;
//    private BufferedReader reader;
//    private PrintWriter writer;
//    
//    private String serverIP = "localhost";
//    private int serverPort = 8888;
    
    public ChatUI() {
        initComponents();
        try {
            clientSocket = new Socket("localhost", 6000);
            if(t==null){
                t =new Thread(this, "Client");
                t.start();
            }
        } catch (Exception e) {
                   Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
        @Override
    public void run() {
        try {
            while (true) {
                getMessage();
            }
        } catch (Exception e) {
            Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void getMessage() throws IOException {
        String chatServer;
        try {
            //Await response from server.
            //Prep method for server response
            BufferedReader chatFromServer = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            //Retreive only string messages.
            chatServer = chatFromServer.readLine();

            //Display chat from server
            jTextAreaResult.append(chatServer + "\n");
        } catch (Exception e) {
            System.out.println("Error is in FormClient.getMessage(): " + e);
        }

    }
    
//    public void sendMessage(){
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResult = new javax.swing.JTextArea();
        jTextFieldChat = new javax.swing.JTextField();
        jButtonSend = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaResult.setColumns(20);
        jTextAreaResult.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResult);

        jTextFieldChat.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N

        jButtonSend.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jButtonHome.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonHome.setText("Home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonHome)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jTextFieldChat)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtonHome)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
//        sendMessage();
        // TODO add your handling code here:
        try {
            String chatClient, chatServer; //variables
            String name = "John";
            chatClient = name + ": " + jTextFieldChat.getText(); //Get string from textfield
            //txtChat.append("Client: " + chatClient + "\n");

            //Prep to send message to server.
            DataOutputStream sendToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Send message to server using writeBytes
            sendToServer.writeBytes(chatClient + "\n");

        } catch (Exception e) {
            System.out.println("The error is in buttonSend: " + e);
        }
    }//GEN-LAST:event_jButtonSendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldChat;
    // End of variables declaration//GEN-END:variables


}
