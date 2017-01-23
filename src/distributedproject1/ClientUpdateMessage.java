/*
 * The client will receive notifications of users entering and leaving the
 * chatroom
 */
package distributedproject1;

/**
 *
 * @author james
 */
public class ClientUpdateMessage {

    int updateType; // 0 for join, 1 for leave
    String[] names;
}
