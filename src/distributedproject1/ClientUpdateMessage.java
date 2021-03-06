/*
 * The client will receive notifications of users entering and leaving the
 * chatroom
 */
package distributedproject1;

/**
 * The client will receive notifications of users entering or leaving a chatroom
 * with an updateType where 0 is join and 1 is leave, and the list of names of
 * individuals joining or leaving.
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class ClientUpdateMessage {

    private int updateType; // 0 for join, 1 for leave, 2 for chatroom updates
    private String[] names;
    
    
    public ClientUpdateMessage() {
        this.updateType = -1;
        this.names = null;
    }

    public ClientUpdateMessage(int updateType, String[] names) {
        this.updateType = updateType;
        this.names = names;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    
    @Override
    public String toString() {
        String output = CommandType.CLIENT_UPDATE_MESSAGE.ordinal() + " " + 
                String.valueOf(updateType).length() + " " + this.updateType + " ";
        for (String name : this.names) {
            output += name.length() + " " + name + " ";
        }
        //remove the last " "
        output = output.trim();
        return output;
    }
}
