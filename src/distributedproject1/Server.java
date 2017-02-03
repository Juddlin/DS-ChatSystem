package distributedproject1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class handles the server, such as lists of chat rooms, users, etc.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class Server {
    
    private ArrayList<Chatroom> chatrooms;
    private ArrayList<User> users;
    private int currentClientId;
    private String mCastAddressMainAddress;
    private String startingMCastAddress;
    private int mCastConcat;
    private String port;
    
    public Server() {
        this.chatrooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentClientId = 1;
        this.mCastAddressMainAddress = "239.255.255.255";
        this.startingMCastAddress = "239.255.255.";
        this.mCastConcat = 1;
        this.port = "443";
    }
    
    public void chatMessage(ChatMessage message, String mCastAddress) {
        try {
            // get the InetAddress of the MCAST group 
            InetAddress ia = InetAddress.getByName(mCastAddress);

            // get the port that the MCAST group members will be listening on
            int recvPort = Integer.parseInt(this.port);

            // create a datagram with a suitable message
            byte[] data = message.toString().getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, recvPort);

            // create a multicast socket bound to any local port
            MulticastSocket ms = new MulticastSocket();

            //Join the multicast group
            ms.joinGroup(ia);

            // send the message with a Time-To-Live (TTL)=1
            ms.send(dp, (byte) 1);

            // tidy up - leave the group and close the socket
            ms.leaveGroup(ia);
            ms.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void chatRequest(ChatRequest request) {
        User tempUser = null;
        for (User user : this.users) {
            if (user.getClientId().equals(request.getClientId())) {
                tempUser = user;
            }
        }
        
        Chatroom tempChatroom = null;
        for (Chatroom room : this.chatrooms) {
            if (room.getUsers().contains(tempUser)) {
                tempChatroom = room;
            }
        }
        
        ChatMessage message = new ChatMessage(tempUser.getUserName(), request.getTime(), tempChatroom.getMessageId(), request.getMessage());
        for (int i = 0; i < this.chatrooms.size(); i++) {
            if (this.chatrooms.get(i).getUsers().contains(tempUser)) {
                this.chatrooms.get(i).incrementMessageId();
                chatMessage(message, this.chatrooms.get(i).getMultiCastAddress());
            }
        }
        
    }
    
    public void clientUpdateMessage(ClientUpdateMessage update) {
        ArrayList<String> iNetAddresses = new ArrayList<>();
        for (Chatroom room : this.chatrooms) {
            iNetAddresses.add(room.getMultiCastAddress());
        }
        iNetAddresses.add(this.mCastAddressMainAddress);
        
        for (int i = 0; i < iNetAddresses.size(); i++) {
            try {
                // get the InetAddress of the MCAST group 
                InetAddress ia = InetAddress.getByName(iNetAddresses.get(i));

                // get the port that the MCAST group members will be listening on
                int recvPort = Integer.parseInt(this.port);

                // create a datagram with a suitable message
                byte[] data = update.toString().getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length, ia, recvPort);

                // create a multicast socket bound to any local port
                MulticastSocket ms = new MulticastSocket();

                //Join the multicast group
                ms.joinGroup(ia);

                // send the message with a Time-To-Live (TTL)=1
                ms.send(dp, (byte) 1);

                // tidy up - leave the group and close the socket
                ms.leaveGroup(ia);
                ms.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public void connectCommand(ConnectCommand command) {
        User user = new User(command.getUsername(), Integer.toString(this.currentClientId), command.getTime());
        this.currentClientId++;
        this.users.add(user);
        String[] roomNames = new String[this.chatrooms.size()];
        for (int i = 0; i < roomNames.length; i++) {
            roomNames[i] = this.chatrooms.get(i).getChatroomName();
        }
        System.out.println("AFTER FOR LOOP");
        try {
            ConnectResponse response = new ConnectResponse(1, user.getClientId(), roomNames, new Date());
            connectResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            ConnectResponse response = new ConnectResponse(0, user.getClientId(), roomNames, new Date());
            connectResponse(response);
        }
    }
    
    public void connectResponse(ConnectResponse response) {
        try {
            // get the InetAddress of the MCAST group 
            InetAddress ia = InetAddress.getByName(this.mCastAddressMainAddress);

            // get the port that the MCAST group members will be listening on
            int recvPort = Integer.parseInt(this.port);

            // create a datagram with a suitable message
            byte[] data = response.toString().getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, recvPort);

            // create a multicast socket bound to any local port
            MulticastSocket ms = new MulticastSocket();

            //Join the multicast group
            ms.joinGroup(ia);
            System.out.println("before connectResponse sent");
            // send the message with a Time-To-Live (TTL)=1
            ms.send(dp, (byte) 1);
            System.out.println("connectResponse sent");
            // tidy up - leave the group and close the socket
            ms.leaveGroup(ia);
            ms.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void joinChatroomCommand(JoinChatroomCommand command) {
        if (command.getStatus() == 0) {
            ArrayList<User> tempList = null;
            int j = 0;
            // take user out of the chat room
            nestedLoopOne:
            for (Chatroom room : this.chatrooms) {
                tempList = room.getUsers();
                for (User user : tempList) {
                    if (command.getUsername().equals(user.getUserName())) {
                        this.chatrooms.get(j).removeUserFromChatroom(user);
                        break nestedLoopOne;
                    }
                }
                j++;
            }
            
            ArrayList<String> nameList = new ArrayList<>();
            for (int i = 0; i < tempList.size(); i++) {
                nameList.add(tempList.get(i).getUserName());
            }
            ClientUpdateMessage message = new ClientUpdateMessage(1, nameList.toArray(new String[0]));
            clientUpdateMessage(message);
        } else {
            // need to add an if-else here for whether it is a join or leave command.
            boolean flag = false;
            int i = 0;
            // Check if chatroom exists alraedy
            for (Chatroom room : this.chatrooms) {
                if (room.getChatroomName().equals(command.getChatroomName())) {
                    flag = true;
                    break;
                }
                i++;
            }

            // Check to see if the user is already in a chatroom, if so don't create or put them in a new one.
            String tempClientID = "";
            String responseMCastAddress = "";
            boolean userFlag = false;
            nestedLoopTwo:
            for (Chatroom room : this.chatrooms) {
                ArrayList<User> tempList = room.getUsers();
                for (User user : tempList) {
                    if (command.getUsername().equals(user.getUserName())) {
                        userFlag = true;
                        tempClientID = user.getClientId();
                        responseMCastAddress = room.getMultiCastAddress();
                        break nestedLoopTwo;
                    }
                }
            }
            
            if (!userFlag) {
                // If the chatroom exists, add the user to it, else create a chatroomt then add the user
                ArrayList<User> tempList = null;
                if (flag) {
                    for (User user : this.users) {
                        if (user.getUserName().equals(command.getUsername())) {
                            this.chatrooms.get(i).addUserToChatroom(user);
                            tempList = this.chatrooms.get(i).getUsers();
                            tempClientID = user.getClientId();
                            responseMCastAddress = this.chatrooms.get(i).getMultiCastAddress();
                        }
                    }
                } else {
                    for (User user : this.users) {
                        if (user.getUserName().equals(command.getUsername())) {
                            responseMCastAddress = this.startingMCastAddress + Integer.toString(this.mCastConcat);
                            this.mCastConcat++;
                            tempList = this.chatrooms.get(i).getUsers();
                            tempClientID = user.getClientId();
                            Chatroom chatroom = new Chatroom(command.getChatroomName(), responseMCastAddress);
                            this.chatrooms.add(chatroom);
                            
                            ArrayList<String> roomNames = new ArrayList<>();
                            for (int j = 0; j < this.chatrooms.size(); j++) {
                                roomNames.add(this.chatrooms.get(j).getChatroomName());
                            }
                            
                            ClientUpdateMessage message = new ClientUpdateMessage(2, roomNames.toArray(new String[0]));
                            clientUpdateMessage(message);
                            break;
                        }
                    }
                }
                
                ArrayList<String> nameList = new ArrayList<>();
                for (int v = 0; v < tempList.size(); v++) {
                    nameList.add(tempList.get(v).getUserName());
                }
                
                JoinChatroomResponse response = new JoinChatroomResponse(1, tempClientID, responseMCastAddress, new Date());
                ClientUpdateMessage message = new ClientUpdateMessage(0, nameList.toArray(new String[0]));
                joinChatroomResponse(response, message, true);
            } else {
                JoinChatroomResponse response = new JoinChatroomResponse(0, tempClientID, responseMCastAddress, new Date());
                joinChatroomResponse(response, null, false);
            }
        }
    }
    
    public void joinChatroomResponse(JoinChatroomResponse response, ClientUpdateMessage message, boolean messageSend) {
        // int status = if you can join a chatroom 1, 0 if they are already in a chatroom

        try {
            // get the InetAddress of the MCAST group 
            InetAddress ia = InetAddress.getByName(this.mCastAddressMainAddress);

            // get the port that the MCAST group members will be listening on
            int recvPort = Integer.parseInt(this.port);

            // create a datagram with a suitable message
            byte[] data = response.toString().getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, recvPort);

            // create a multicast socket bound to any local port
            MulticastSocket ms = new MulticastSocket();

            //Join the multicast group
            ms.joinGroup(ia);

            // send the message with a Time-To-Live (TTL)=1
            ms.send(dp, (byte) 1);

            // tidy up - leave the group and close the socket
            ms.leaveGroup(ia);
            ms.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        if (messageSend) {
            clientUpdateMessage(message);
        }
    }
    
}
