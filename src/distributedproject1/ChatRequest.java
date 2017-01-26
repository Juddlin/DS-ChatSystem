/*
 * The client will send messages with this protocol. The client must be 
 * connected before it can send chat messages
 */
package distributedproject1;

import java.util.Date;

/**
 * The client will send chat messages using the protocol containing the
 * clientId, time of message, and the contents of the message.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ChatRequest {

    private String clientId;
    private Date time;
    private String message;

    public ChatRequest() {
        this.clientId = null;
        this.time = null;
        this.message = null;
    }

    public ChatRequest(String clientId, Date time, String message) {
        this.clientId = clientId;
        this.time = time;
        this.message = message;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.clientId + " " + this.time + " " + this.message;
    }
}
