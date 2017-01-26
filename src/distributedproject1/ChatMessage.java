/*
 * The client will receive from the server in the chatroom
 */
package distributedproject1;

import java.util.Date;

/**
 * The server will send this message to the correct client containing the
 * senderName, time of message sent, messageId, and the message itself.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ChatMessage {

    private String senderName;
    private Date time;
    private int messageId;
    private String message;

    public ChatMessage() {
        this.senderName = null;
        this.time = null;
        this.messageId = 0;
        this.message = null;
    }

    public ChatMessage(String senderName, Date time, int messageId, String message) {
        this.senderName = senderName;
        this.time = time;
        this.messageId = messageId;
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.senderName + " " + this.time + " " + this.messageId + " " + this.message;
    }
}
