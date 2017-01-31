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
public class ServerHandling {

    private String mCastAddress;
    private String IPAddress;

    public ServerHandling() {
        this.mCastAddress = "239.255.255.255";
        this.IPAddress = "127.0.0.1";
    }

    public void run() {
        while (true) {
            try {
                // Get the InetAddress of the MCAST group 
                InetAddress ia = InetAddress.getByName(this.mCastAddress);

                // Get the port that we will be listening on
                int port = Integer.parseInt(this.IPAddress);

                // Create a multicast socket on the specified local port number
                MulticastSocket ms = new MulticastSocket(port);

                // Create an empty datagram packet
                DatagramPacket mainDP = new DatagramPacket(new byte[128], 128);

                // Join a multicast group and wait for some action
                // THIS NEEDS TO BE SLPIT APART, JOINING A GROUP AND RECEIVING IS HAPPENING SIMULTANEOUSLY HERE
                ms.joinGroup(ia);
                System.out.println("waiting for a packet from " + ia + "...");
                ms.receive(mainDP);

                // Print out what we received and quit
                System.out.println(new String(mainDP.getData()));

                ms.leaveGroup(ia);
                ms.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
