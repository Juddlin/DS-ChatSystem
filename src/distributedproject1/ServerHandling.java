package distributedproject1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Class handles the inner workings of the server.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ServerHandling extends Thread {

    private String mCastAddressMainAddress;
    private String port;
    private static final Server SERVER = new Server();

    public ServerHandling() {
        this.mCastAddressMainAddress = "239.255.255.255";
        this.port = "443";
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }

    private void listen() {
        try {
            // Get the InetAddress of the MCAST group 
            InetAddress ia = InetAddress.getByName(this.mCastAddressMainAddress);

            // Get the port that we will be listening on
            int port = Integer.parseInt(this.port);

            // Create a multicast socket on the specified local port number
            MulticastSocket ms = new MulticastSocket(port);

            // Create an empty datagram packet
            DatagramPacket mainDP = new DatagramPacket(new byte[128], 128);

            // Join a multicast group and wait for some action
            // THIS NEEDS TO BE SLPIT APART, JOINING A GROUP AND RECEIVING IS HAPPENING SIMULTANEOUSLY HERE
            ms.joinGroup(ia);
            ms.receive(mainDP);

            // Print out what we received and quit
            //System.out.println(new String(mainDP.getData()));
            deserialize(CommandParser.determineType(mainDP.getData()).toString(), mainDP.getData());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void deserialize(String type, byte[] data) {
        switch (type) {
            case "CHAT_MESSAGE":
                ChatMessage chatMessage = CommandParser.genChatMessage(data);
                SERVER.chatMessage(chatMessage, mCastAddressMainAddress);
                break;
            case "CHAT_REQUEST":
                ChatRequest chatRequest = CommandParser.genChatRequest(data);
                SERVER.chatRequest(chatRequest);
                break;
            case "CLIENT_UPDATE_MESSAGE":
                ClientUpdateMessage updateMessage = CommandParser.genClientUpdateMessage(data);
                SERVER.clientUpdateMessage(updateMessage);
                break;
            case "CONNECT_COMMAND":
                ConnectCommand connectCommand = CommandParser.genConnectCommand(data);
                SERVER.connectCommand(connectCommand);
                break;
            case "CONNECT_RESPONSE":
                ConnectResponse connectResponse = CommandParser.genConnectResponse(data);
                SERVER.connectResponse(connectResponse);
                break;
            case "JOIN_CHATROOM_COMMAND":
                JoinChatroomCommand joinChatroomCommand = CommandParser.genJoinChatroomCommand(data);
                SERVER.joinChatroomCommand(joinChatroomCommand);
                break;
            case "JOIN_CHATROOM_RESPONSE":
                JoinChatroomResponse joinChatroomResponse = CommandParser.genJoinChatroomResponse(data);
                SERVER.joinChatroomResponse(joinChatroomResponse);
                break;
            default:
                System.out.println("Unknown server command.");
                break;
        }
    }
}
