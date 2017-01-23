/*
 * The client will send messages with this protocol. The client must be 
 * connected before it can send chat messages
 */
package distributedproject1;

import java.util.Date;

/**
 *
 * @author james
 */
public class ChatRequest {

    String clientId;
    Date time;
    String message;
}
