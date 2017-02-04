/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedproject1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author james
 */
public class UnicastReceiver {
    private InetAddress ia;
    private int port;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private int PACKET_SIZE = 200;
    private byte[] buffer;
    private JButton receiverButton;
    private ClientWindow parent;
    
    public UnicastReceiver(String address, String port, JButton receiverButton, ClientWindow parent) throws UnknownHostException, IOException
    {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        this.ia = InetAddress.getByName(address);
        this.port = Integer.parseInt(port);
        this.datagramSocket = new DatagramSocket(this.port);
        this.receiverButton = receiverButton;
        System.out.println("In Unicast Receiver constructor, ia = "+ia);
        this.parent = parent;
        //this.datagramSocket = new MulticastSocket(this.port);
        //this.datagramPacket = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
    }
    
    public byte[] receive() throws IOException, ClassNotFoundException {

            // get the InetAddress of the MCAST group 
            //InetAddress ia = InetAddress.getByName(argv[0]);

            // get the port that we will be listening on
            //int port = Integer.parseInt(argv[1]);

            // create a multicast socket on the specified local port number
            //MulticastSocket ms = new MulticastSocket(port);

            // create an empty datagram packet
            this.datagramPacket = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);

            //Join a multicast group and wait for some action
            //ms.joinGroup(ia);
            
            try {
            //System.out.println("waiting for a packet from " + ia + "...");
            parent.getChatArea().append("Waiting for unicast packet...");
            this.datagramSocket.receive(this.datagramPacket);
            byte[] data = this.datagramPacket.getData();

            // print out what we received and quit
            parent.getChatArea().append(new String(this.datagramPacket.getData()));

            //ms.leaveGroup(ia);
            //ms.close();
            return data;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
        
    }

    public InetAddress getIa() {
        return ia;
    }

    public void setIa(InetAddress ia) {
        this.ia = ia;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DatagramSocket getMs() {
        return datagramSocket;
    }

    public void setMs(DatagramSocket ms) {
        this.datagramSocket = ms;
    }

    public DatagramPacket getDp() {
        return datagramPacket;
    }

    public void setDp(DatagramPacket dp) {
        this.datagramPacket = dp;
    }

    public int getPACKET_SIZE() {
        return PACKET_SIZE;
    }

    public void setPACKET_SIZE(int PACKET_SIZE) {
        this.PACKET_SIZE = PACKET_SIZE;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

}
