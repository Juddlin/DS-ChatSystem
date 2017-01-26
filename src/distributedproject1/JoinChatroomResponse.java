/*
 * After client joins a chatroom, server will respond with JoinChatroomResponse
 */
package distributedproject1;

import java.util.Date;

/**
 * The server respond to the JoinChatroomCommand with the response containing
 * the status of the response, clientId, the roomMulticastIp, and the time.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class JoinChatroomResponse {

    private int status;
    private String clientId;
    private String roomMulticastIp;
    private Date time;

    public JoinChatroomResponse() {
        this.status = 0;
        this.clientId = null;
        this.roomMulticastIp = null;
        this.time = null;
    }
    
    public JoinChatroomResponse(int status, String clientId, String roomMulticastIp, Date time) {
        this.status = status;
        this.clientId = clientId;
        this.roomMulticastIp = roomMulticastIp;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRoomMulticastIp() {
        return roomMulticastIp;
    }

    public void setRoomMulticastIp(String roomMulticastIp) {
        this.roomMulticastIp = roomMulticastIp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return CommandType.JOIN_CHATROOM_RESPONSE + " " + 
                String.valueOf(this.status).length() + " " + this.status + " " + 
                this.clientId.length() + " " + this.clientId + " " + 
                roomMulticastIp.length() + " " + this.roomMulticastIp + " " + 
                this.time.toString().length() + " " + this.time;
    }
}
