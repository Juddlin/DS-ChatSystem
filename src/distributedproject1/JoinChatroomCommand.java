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

    public JoinChatroomCommand() {
        this.username = null;
        this.chatroomName = null;
        this.time = null;
    }

    public JoinChatroomCommand(String username, String chatroomName, Date time) {
        this.username = username;
        this.chatroomName = chatroomName;
        this.time = time;
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
    
    @Override
    public String toString() {
        return this.username + " " + this.chatroomName + " " + this.time;
    }
}
