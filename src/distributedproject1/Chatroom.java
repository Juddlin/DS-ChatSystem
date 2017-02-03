package distributedproject1;

import java.util.ArrayList;

/**
 * Class contains the information needed for a chat room.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class Chatroom {
    
    private ArrayList<String> users;
    private String chatroomName;
    private String multiCastAddress;
    
    public Chatroom(String chatroomName, String multiCastAddress) {
        this.users = new ArrayList<>();
        this.chatroomName = chatroomName;
        this.multiCastAddress = multiCastAddress;
    }
    
    public String getChatroomName() {
        return this.chatroomName;
    }
    
    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }
    
    public String getMultiCastAddress() {
        return this.multiCastAddress;
    }
    
    public void setMultiCastAddress(String multiCastAddress) {
        this.multiCastAddress = multiCastAddress;
    }
    
    public void addUserToChatroom(String user) {
        this.users.add(user);
    }
    
    public void removeUserFromChatroom(String user) {
        this.users.remove(user);
    }
    
}
