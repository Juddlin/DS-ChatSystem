/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedproject1;

import java.net.*;
import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;

//Usage: java MulticastReceiver ALL-SYSTEMS.MCAST.NET 4000
public class MulticastReceiver implements Runnable {
    private InetAddress ia;
    private int port;
    private MulticastSocket ms;
    private DatagramPacket dp;
    private int PACKET_SIZE = 200;
    private byte[] buffer;
    private JButton receiverButton;
    private String address;
    JTextArea clientOutput;
    
    public MulticastReceiver(String address, String port, JButton receiverButton, JTextArea clientOutput) throws UnknownHostException, IOException
    {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        this.ia = InetAddress.getByName(address);
        this.address = address;
        this.port = Integer.parseInt(port);
        this.receiverButton = receiverButton;
        this.clientOutput = clientOutput;
        clientOutput.append("In msReceiver constructor, ia = "+ia + "\n");
        //this.ms = new MulticastSocket(this.port);
        //this.dp = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);

    }
    public byte[] receive() throws IOException, ClassNotFoundException {
        try {
//            if (2 != argv.length) {
//                System.err.println("Usage: java MulticastReceiver <address> <port>");
//                System.err.println(" e.g.: java MulticastReceiver ALL-SYSTEMS.MCAST.NET 4000");
//                System.exit(1);
//            }

            // get the InetAddress of the MCAST group 
            //InetAddress ia = InetAddress.getByName(argv[0]);
            InetAddress iaa = InetAddress.getByName(this.address);
            // get the port that we will be listening on
            //int port = Integer.parseInt(argv[1]);

            // create a multicast socket on the specified local port number
            MulticastSocket ms = new MulticastSocket(port);

            // create an empty datagram packet
            DatagramPacket dp = new DatagramPacket(new byte[167], 167);

            //Join a multicast group and wait for some action
            ms.joinGroup(iaa);
                        //ms.setLoopbackMode(true);

            clientOutput.append("waiting for a packet from " + ia + "...\n");
            ms.receive(dp);
            byte[] data = dp.getData();

            // print out what we received and quit
            clientOutput.append(new String(dp.getData()) + "\n");

            //ms.leaveGroup(ia);
            //ms.close();
            return data;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
        
    }

    @Override
    public void run() {
        ms = null;
        try {
            System.out.println("Setting up multicast receiver");
            this.ms = new MulticastSocket(this.port);
            this.ms.joinGroup(this.ia);
        } catch (IOException ex) {
            Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Multicast receiver set up");
        while (true) {
            try {
                this.buffer = new byte[PACKET_SIZE];
                this.dp = new DatagramPacket(buffer, buffer.length);
                this.ms.receive(this.dp);
                this.buffer = this.dp.getData();
                System.out.println(new String(buffer));
            } catch (IOException ex) {
                Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
//            try {
//                ms.leaveGroup(ia);
//            } catch (IOException ex) {
//                Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ms.close();
//            ChangeEvent e = new ChangeEvent(this);
              receiverButton.doClick();

            
        }
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

    public MulticastSocket getMs() {
        return ms;
    }

    public void setMs(MulticastSocket ms) {
        this.ms = ms;
    }

    public DatagramPacket getDp() {
        return dp;
    }

    public void setDp(DatagramPacket dp) {
        this.dp = dp;
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
