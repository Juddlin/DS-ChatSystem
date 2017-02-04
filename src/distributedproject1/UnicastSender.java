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

/**
 *
 * @author james
 */
public class UnicastSender {
	public static void send( String[] argv, String str ) 
	{
            System.setProperty("java.net.preferIPv4Stack" , "true");
		try 
		{
			if (2 != argv.length) {
				System.err.println("Usage: java MulticastSender <address> <port>");
				System.err.println(" e.g.: java MulticastSender ALL-SYSTEMS.MCAST.NET 4000");
				System.exit(1);
			}

			// get the InetAddress of the MCAST group 
			InetAddress ia = InetAddress.getByName( argv[0] );

			// get the port that the MCAST group members will be listening on
			int recvPort = Integer.parseInt( argv[1] );

			// create a datagram with a suitable message
			//String str = "Hello from: "+InetAddress.getLocalHost();
			byte[] data = str.getBytes();
			DatagramPacket dp = new DatagramPacket(data, data.length, ia, recvPort);

			// create a multicast socket bound to any local port
			DatagramSocket ds = new DatagramSocket();

			//Join the multicast group
			//ms.joinGroup(ia); 

			// send the message with a Time-To-Live (TTL)=1
                        ds.setReuseAddress(true);
			ds.send(dp); 

			// tidy up - leave the group and close the socket
			//ms.leaveGroup(ia);
			ds.close();
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
