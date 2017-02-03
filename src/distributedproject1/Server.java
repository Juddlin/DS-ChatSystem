package distributedproject1;

import java.util.ArrayList;

/**
 * Class handles the server, such as lists of chat rooms, users, etc.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class Server {
    
    private ArrayList<Chatroom> chatrooms;
    
    public Server() {
        this.chatrooms = new ArrayList<>();
    }
    
    public void chatMessage(ChatMessage message) {
        
    }
    
    public void chatRequest(ChatRequest request) {
        
    }
    
    public void clientUpdateMessage(ClientUpdateMessage update) {
        
    }
    
    public void connectCommand(ConnectCommand command) {
        
    }
    
    public void connectResponse(ConnectResponse response) {
        
    }
    
    public void joinChatroomCommand(JoinChatroomCommand command) {
        
    }
    
    public void joinChatroomResponse(JoinChatroomResponse response) {
        
    }
    
}
