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
    private String IPAddress;
    private static final CommandParser PARSER = new CommandParser();

    public ServerHandling() {
        this.mCastAddressMainAddress = "239.255.255.255";
        this.IPAddress = "443";
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
            int port = Integer.parseInt(this.IPAddress);

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
                break;
            case "CHAT_REQUEST":
                ChatRequest chatRequest = CommandParser.genChatRequest(data);
                break;
            case "CLIENT_UPDATE_MESSAGE":
                ClientUpdateMessage updateMessage = CommandParser.genClientUpdateMessage(data);
                break;
            case "CONNECT_COMMAND":
                ConnectCommand connectCommand = CommandParser.genConnectCommand(data);
                break;
            case "CONNECT_RESPONSE":
                ConnectResponse connectResponse = CommandParser.genConnectResponse(data);
                break;
            case "JOIN_CHATROOM_COMMAND":
                JoinChatroomCommand joinChatroomCommand = CommandParser.genJoinChatroomCommand(data);
                break;
            case "JOIN_CHATROOM_RESPONSE":
                JoinChatroomResponse joinChatroomResponse = CommandParser.genJoinChatroomResponse(data);
                break;
            default:
                break;
        }
    }
}
