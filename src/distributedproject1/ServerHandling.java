package distributedproject1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Class handles the inner workings of the server.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ServerHandling extends Thread {

    private String mCastAddressMainAddress;
    private int port;
    private static final Server SERVER = new Server();
    private InetAddress ia;
    private MulticastSocket ms;

    public ServerHandling() {
        this.mCastAddressMainAddress = NetworkingConst.ADDRESS;
        this.port = NetworkingConst.PORT_INT;
        try {
            this.ia = InetAddress.getByName(this.mCastAddressMainAddress);
        } catch (IOException e) {
            System.out.println("HIT HIT HIT");
        }
        
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }

    private void listen() {
        try {
            // Create an empty datagram packet
            DatagramPacket mainDP = new DatagramPacket(new byte[128], 128);
            this.ms = new MulticastSocket(port);
            
            // Join a multicast group and wait for some action
            // THIS NEEDS TO BE SLPIT APART, JOINING A GROUP AND RECEIVING IS HAPPENING SIMULTANEOUSLY HERE
            ms.joinGroup(ia);
            System.out.println("before receive in listener");
            ms.receive(mainDP);
            System.out.println("after receive in listener");

            // Print out what we received and quit
            System.out.println(new String(mainDP.getData()));
            deserialize(CommandParser.determineType(mainDP.getData()).toString(), mainDP.getData());
        } catch (IOException e) {
            e.printStackTrace();
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
                SERVER.joinChatroomResponse(joinChatroomResponse, null, false);
                break;
            default:
                System.out.println("Unknown server command.");
                break;
        }
    }
}
