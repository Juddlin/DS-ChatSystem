/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedproject1;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author james
 */
public class ClientWindow extends javax.swing.JFrame {

    // data fields
    private String userName;
    private String clientId;
    private String roomMulticastIp;
    private MulticastReceiver mainServerReceiver;
    private MulticastReceiver chatroomReceiver;
    private DefaultListModel<String> listofrooms;
    private DefaultListModel<String> usersInRoom;
    private String serverIP = "239.255.255.255";
    private String serverPort = "4000";
    private String localPort = "4000";
    private String[] serverPortIP = {serverIP, serverPort};
    private String currentChatroom;
    private boolean sucessfulConnection;

    /**
     * Creates new form ClientWindow
     */
    public ClientWindow() {
        initComponents();
    }

    /*
    public ClientWindow(String userName) throws IOException, ClassNotFoundException {
        this.userName = userName;
        initComponents();

    }
     */
    private void initialCommunication() throws ClassNotFoundException, IOException {
        ConnectResponse connectResponse = new ConnectResponse();
        connectResponse.setStatus(0);
        listofrooms = new DefaultListModel();
        usersInRoom = new DefaultListModel();
        byte[] response;

        try {
            mainServerReceiver = new MulticastReceiver(this.serverIP, this.localPort, null);
            this.chatArea.append("Waiting for response from server...\n");
            // send initial request to server
            UnicastSender.send(serverPortIP, new ConnectCommand(this.userName, new Date()).toString());
            response = mainServerReceiver.receive();
            if (response != null) {
                if (CommandParser.determineType(response) == CommandType.CONNECT_RESPONSE) {
                    connectResponse = CommandParser.genConnectResponse(response);
                    System.out.println("connectResponse status: " + connectResponse.getStatus());
                } else {
                    System.out.println("connectResponse not received");
                    return;
                }
            } else {
                System.out.println("response was null");
                return;
            }
//            while (connectResponse.getStatus() != 1 || response == null) {
//                if (CommandParser.determineType(response) == CommandType.CONNECT_RESPONSE) {
//                    connectResponse = CommandParser.genConnectResponse(response);
//                    //resend connect command
//                    UnicastSender.send(serverPortIP, new ConnectCommand(this.userName, new Date()).toString());
//                    //mainServerReceiver = new UnicastReceiver(this.serverIP, this.serverPort, null);
//                    response = mainServerReceiver.receive(this.serverPortIP);
//                }
//            }
        } catch (IOException ex) {
            Logger.getLogger(ClientWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.clientId = connectResponse.getClientId();
        String[] rooms = connectResponse.getActiveChatroomNames();
        if (rooms != null) {
            for (String room : rooms) {
                listofrooms.addElement(room);
            }
        }
        roomsList.setModel(listofrooms);
        userList.setModel(usersInRoom);
        this.chatArea.append("Connection sucessful, clientId: " + this.clientId);
        sucessfulConnection = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        roomsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomsList = new javax.swing.JList<>();
        joinRoomButton = new javax.swing.JButton();
        createRoomButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        userLabel = new javax.swing.JLabel();
        leaveRoomButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garrett and James Super Happy Fun Riptide SharkChat");

        messageField.setText("enter message");
        messageField.setToolTipText("enter message");
        messageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageFieldActionPerformed(evt);
            }
        });

        chatArea.setEditable(false);
        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        roomsLabel.setText("Chat Rooms");

        jScrollPane2.setViewportView(roomsList);

        joinRoomButton.setText("Join");
        joinRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinRoomButtonActionPerformed(evt);
            }
        });

        createRoomButton.setText("Create");
        createRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(userList);

        userLabel.setText("Users");

        leaveRoomButton.setText("Leave");
        leaveRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRoomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(joinRoomButton)
                    .addComponent(createRoomButton)
                    .addComponent(leaveRoomButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(messageField))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userLabel)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(roomsLabel)
                .addGap(573, 573, 573))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roomsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(joinRoomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createRoomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leaveRoomButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void messageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageFieldActionPerformed
        String message = messageField.getText();
        messageField.setText("");
        if (roomMulticastIp != null) {
            ChatRequest cr = new ChatRequest(clientId, new Date(), message);
            String[] roomIpPort = {roomMulticastIp, serverPort};
            MulticastSender.send(roomIpPort, cr.toString());
        } else {
            Color old = this.chatArea.getForeground();
            this.chatArea.setForeground(Color.RED);
            this.chatArea.append("Need to join a chatroom first\n");
            this.chatArea.setForeground(old);
        }
    }//GEN-LAST:event_messageFieldActionPerformed

    private void joinRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinRoomButtonActionPerformed
        //get the room seleced in list
        String room = roomsList.getSelectedValue();
        if (room == null) {
            Color old = this.chatArea.getForeground();
            this.chatArea.setForeground(Color.RED);
            this.chatArea.append("Please select a chatroom\n");
            this.chatArea.setForeground(old);
        } else {
            String info = "Joining chatroom " + room + "\n";
            this.chatArea.append(info);
            joinChatroom(room);
        }
    }//GEN-LAST:event_joinRoomButtonActionPerformed

    private void createRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRoomButtonActionPerformed

        this.setFocusableWindowState(false);
        String newRoom = (String) JOptionPane.showInputDialog(
                this,
                "Enter new room name:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE);

        //If a string was returned, say so.
        if ((newRoom != null) && (newRoom.length() > 0)) {
            String info = "Creating new chatroom: " + newRoom + "\n";
            this.chatArea.append(info);
            joinChatroom(newRoom);
        }
        this.setFocusableWindowState(true);
    }//GEN-LAST:event_createRoomButtonActionPerformed

    private void leaveRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRoomButtonActionPerformed
        if (this.roomMulticastIp != null) {
            UnicastSender.send(serverPortIP, new JoinChatroomCommand(this.userName, this.currentChatroom, new Date(), 0).toString());
            this.roomMulticastIp = null;
            this.currentChatroom = null;
            this.chatArea.append("Chatroom left\n");
            this.createRoomButton.setEnabled(true);
            this.joinRoomButton.setEnabled(true);
            this.leaveRoomButton.setEnabled(false);
        }

    }//GEN-LAST:event_leaveRoomButtonActionPerformed

    private void joinChatroom(String room) {
        try {
            JoinChatroomCommand jcc = new JoinChatroomCommand(userName, room, new Date(), 1);
            UnicastSender.send(serverPortIP, jcc.toString());
            byte[] response = mainServerReceiver.receive();

            if (CommandParser.determineType(response) == CommandType.JOIN_CHATROOM_RESPONSE) {
                JoinChatroomResponse jcr = CommandParser.genJoinChatroomResponse(response);
                if (jcr.getClientId().equals(this.clientId)) {
                    // start listening for messages
                    JButton receiverButton = new JButton();
                    receiverButton.setVisible(false);
                    this.roomMulticastIp = jcr.getRoomMulticastIp();
                    chatroomReceiver = new MulticastReceiver(jcr.getRoomMulticastIp(), serverPort, receiverButton);
                    receiverButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            messageReceived();
                        }
                    });
                    new Thread(chatroomReceiver, "MulticastReceiver").start();

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientWindow.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientWindow.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        this.currentChatroom = room;
        this.createRoomButton.setEnabled(false);
        this.joinRoomButton.setEnabled(false);
        this.leaveRoomButton.setEnabled(true);
    }

    public void runClient_OLD() {
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
            java.util.logging.Logger.getLogger(ClientWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientWindow().setVisible(true);
            }
        });
    }

    public void runClient() throws ClassNotFoundException, IOException {
        this.leaveRoomButton.setEnabled(false);
        this.setVisible(true);
        while (userName == null) {
            getUsername();
        }

        /*
        listofrooms = new DefaultListModel();
        usersInRoom = new DefaultListModel();
        roomsList.removeAll();
        userList.removeAll();
        listofrooms.addElement("test1");
        listofrooms.addElement("test2");
        usersInRoom.addElement("test3");
        usersInRoom.addElement("test4");
        roomsList.setModel(listofrooms);
        //roomsList.updateUI();
        userList.setModel(usersInRoom);
         */
        sucessfulConnection = false;
        while (!sucessfulConnection) {
            initialCommunication();
        }
    }

    private void getUsername() {
        this.setFocusableWindowState(false);
        String userName = (String) JOptionPane.showInputDialog(
                this,
                "Enter user name:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE);

        //If a string was returned, say so.
        if ((userName != null) && (userName.length() > 0)) {
            this.userName = userName;
            String greeting = "Hello, " + userName + "!\nSelect join or create a chatroom\n";
            this.chatArea.setText(greeting);

        } else {
            Color old = this.chatArea.getForeground();
            this.chatArea.setForeground(Color.RED);
            this.chatArea.append("Invalid username!\n");
            this.chatArea.setForeground(old);
        }
        //If you're here, the return value was null/empty.
        this.setFocusableWindowState(true);
    }

    private void messageReceived() {

        byte[] message = mainServerReceiver.getBuffer();
        if (CommandParser.determineType(message) != null) {
            switch (CommandParser.determineType(message)) {
                case CHAT_MESSAGE:
                    ChatMessage cm = CommandParser.genChatMessage(message);
                    chatArea.append(cm.getSenderName());
                    chatArea.append(cm.getTime().toString());
                    chatArea.append(cm.getMessage());
                    chatArea.append("\n");
                    break;
                case CLIENT_UPDATE_MESSAGE:
                    ClientUpdateMessage cum = CommandParser.genClientUpdateMessage(message);
                    String[] names = cum.getNames();
                    /*
                    if (cum.getUpdateType() == 0) {
                        for (String name : names) {
                            usersInRoom.addElement(name);
                        }
                    } else if (cum.getUpdateType() == 1) {
                        for (String name : names) {
                            usersInRoom.removeElement(name);
                        }
                    }*/
                    if (cum.getUpdateType() == 1 || cum.getUpdateType() == 0) {
                        usersInRoom.clear();
                        for (String user : cum.getNames()) {
                            usersInRoom.addElement(user);
                        }
                    } else if (cum.getUpdateType() == 2) {
                        listofrooms.clear();
                        for (String room : cum.getNames()) {
                            listofrooms.addElement(room);
                        }
                    }
                    //userList.setListData(names);
                    break;
                default:
                    chatArea.append(new String(message));
                    System.out.println("Invalid message");
            }
        } else {
            chatArea.append(new String(message));
        }
    }

    public javax.swing.JTextArea getChatArea() {
        return this.chatArea;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton createRoomButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton joinRoomButton;
    private javax.swing.JButton leaveRoomButton;
    private javax.swing.JTextField messageField;
    private javax.swing.JLabel roomsLabel;
    private javax.swing.JList<String> roomsList;
    private javax.swing.JLabel userLabel;
    private javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables

}
