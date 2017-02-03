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
    private String port;

    public Server() {
        this.chatrooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentClientId = 1;
        this.mCastAddressMainAddress = "239.255.255.255";
        this.port = "443";
    }

    public void chatMessage(ChatMessage message) {

    }

    public void chatRequest(ChatRequest request) {

    }

    public void clientUpdateMessage(ClientUpdateMessage update) {

    }

    public void connectCommand(ConnectCommand command) {
        User user = new User(command.getUsername(), Integer.toString(currentClientId), command.getTime());
        this.currentClientId++;
        users.add(user);
        String[] roomNames = new String[this.chatrooms.size()];
        for (int i = 0; i < roomNames.length; i++) {
            roomNames[i] = this.chatrooms.get(i).getChatroomName();
        }
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

            // send the message with a Time-To-Live (TTL)=1
            ms.send(dp, (byte) 1);

            // tidy up - leave the group and close the socket
            ms.leaveGroup(ia);
            ms.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void joinChatroomCommand(JoinChatroomCommand command) {

    }

    public void joinChatroomResponse(JoinChatroomResponse response) {

    }

}
