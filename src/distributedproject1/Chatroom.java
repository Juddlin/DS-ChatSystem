package distributedproject1;

import java.util.ArrayList;

/**
 * Class contains the information needed for a chat room.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class Chatroom {
    
    private ArrayList<User> users;
    private String chatroomName;
    private String multiCastAddress;
    private int currentMessageId;
    
    public Chatroom(String chatroomName, String multiCastAddress) {
        this.users = new ArrayList<>();
        this.chatroomName = chatroomName;
        this.multiCastAddress = multiCastAddress;
        this.currentMessageId = 1;
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
    
    public ArrayList<User> getUsers(){
        return this.users;
    }
    
    public void addUserToChatroom(User user) {
        this.users.add(user);
    }
    
    public void removeUserFromChatroom(User user) {
        this.users.remove(user);
    }
    
    public int getMessageId() {
        return this.currentMessageId;
    }
    
    public void setMessageId(int currentMessageId) {
        this.currentMessageId = currentMessageId;
    }
    
    public void incrementMessageId(){
        this.currentMessageId++;
    }
    
}
