package distributedproject1;

import java.util.Date;

/**
 * Class stores the information for a user.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class User {

    private String userName;
    private String clientId;
    private Date time;

    public User(String userName, String clientId, Date time) {
        this.userName = userName;
        this.clientId = clientId;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
