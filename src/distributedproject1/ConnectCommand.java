/*
 * The client will join the hat system by sending a ConnectCommand
 */
package distributedproject1;

import java.util.Date;

/**
 * The client will join the chat system by sending a Connect command using the
 * username of the user and the time of the connect request.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ConnectCommand {

    private String username;
    private Date time;

    public ConnectCommand() {
        this.username = null;
        this.time = null;
    }

    public ConnectCommand(String username, Date time) {
        this.username = username;
        this.time = time;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return this.username + " " + this.time;
    }
}
