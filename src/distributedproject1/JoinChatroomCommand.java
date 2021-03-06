/*
 * The client will join the chatroom by sending a JoinChatroomCommand
 */
package distributedproject1;

import java.util.Date;

/**
 * The client will join the chat room by sending a Connect command with the
 * userName of the user, the chatroomName, and the time of connection.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class JoinChatroomCommand {

    private String username;
    private String chatroomName;
    private Date time;
    private int status; // 0 for leave, 1 for join

    public JoinChatroomCommand() {
        this.username = null;
        this.chatroomName = null;
        this.time = null;
        this.status = -1;
    }

    public JoinChatroomCommand(String username, String chatroomName, Date time, int status) {
        this.username = username;
        this.chatroomName = chatroomName;
        this.time = time;
        this.status = status;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatroomName() {
        return this.chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    @Override
    public String toString() {
        return CommandType.JOIN_CHATROOM_COMMAND.ordinal() + " " + 
                this.username.length() + " " + this.username + " " + 
                this.chatroomName.length() + " " + this.chatroomName + " " + 
                this.time.toString().length() + " " + this.time + " " + 
                String.valueOf(status).length() + " " + this.status;
    }
}
