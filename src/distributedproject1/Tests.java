/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedproject1;

import java.util.Date;

/**
 *
 * @author james
 */
public class Tests {
    public static void main (String[] args)
    {
        
     int status = 69;
     String clientId = "420";
     String[] activeChatroomNames = {"balls", "hairy balls", "Washington Redskins"};
     Date time = new Date();
     ConnectResponse cr = new ConnectResponse(status, clientId, activeChatroomNames, time);
     byte[] bytes = cr.toString().getBytes();
     if(CommandParser.determineType(bytes) == CommandType.CONNECT_RESPONSE){
         ConnectResponse newcr = CommandParser.genConnectResponse(bytes);
         System.out.println(newcr);
     } else {
         System.out.println("Type was wrong");
     }
     
    }
}
