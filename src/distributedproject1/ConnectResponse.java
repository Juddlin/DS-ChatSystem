/*
 * The server will respond with a ConnectResponse
 */
package distributedproject1;

import java.util.Date;

/**
 * The server will respond with a response containing the status, clientId, list
 * of activeChatroomNames, and the time of the response.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ConnectResponse {

    private int status;
    private String clientId;
    private String[] activeChatroomNames;
    private Date time;

    public ConnectResponse() {
        this.status = 0;
        this.clientId = null;
        this.activeChatroomNames = null;
        this.time = null;
    }

    public ConnectResponse(int status, String clientId, String[] activeChatroomNames, Date time) {
        this.status = status;
        this.clientId = clientId;
        this.activeChatroomNames = activeChatroomNames;
        this.time = time;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getActiveChatroomNames() {
        return this.activeChatroomNames;
    }

    public void setActiveChatroomNames(String[] activeChatroomNames) {
        this.activeChatroomNames = activeChatroomNames;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        String rooms = new String();
        for (String room: this.activeChatroomNames)
        {
            rooms += room.length() + " " + room + " ";
        }
        return CommandType.CONNECT_RESPONSE.ordinal() + " " + 
                String.valueOf(status).length() + " " + this.status + " " + 
                clientId.length() + " " + this.clientId + " " + 
                //this.activeChatroomNames.length() + " " + this.activeChatroomNames + " " + 
                rooms + 
                this.time.toString().length() + " " + this.time;
    }
}
