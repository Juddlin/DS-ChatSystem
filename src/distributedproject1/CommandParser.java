/**
 * CommandParser is used to determine the object type in a byte array, and
 * create an object of that type. To generate the corresponding object, either
 * the byte array or a string can be passed into the function gen*().
 */
package distributedproject1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author James Anderson
 * @author Garrett Goodman
 */
public class CommandParser {

    /**
     * Returns the enum corresponding to which CommandType is encoded in the
     * byte array. If not found, returns null
     *
     * @param bytes a byte array of an encoded Command
     * @return enum value of CommandType, otherwise null
     */
    public static CommandType determineType(byte[] bytes) {
        return determineType(new String(bytes));
    }

    /**
     * Returns the enum corresponding to which CommandType is encoded in the
     * string. If not found, returns null
     *
     * @param data
     * @return the CommandType encoded in the string, otherwise null
     */
    public static CommandType determineType(String data) {
        int index;
        try {
            index = Integer.parseInt(data.substring(0, data.indexOf(" ", 0)));
        } catch (NumberFormatException e) {
            return null;
        }
        return determineType(index);
    }

    /**
     * Given the integer value of the CommandType, returns the corresponding
     * enum object
     *
     * @param index the integer value of a CommandType
     * @return the corresponding CommandType, otherwise null
     */
    private static CommandType determineType(int index) {
        for (CommandType type : CommandType.values()) {
            if (type.ordinal() == index) {
                return type;
            }
        }
        return null;
    }

    public static ChatMessage genChatMessage(byte[] bytes) {
        return genChatMessage(new String(bytes));
    }

    public static ChatMessage genChatMessage(String data) {
        assert (determineType(data) == CommandType.CHAT_MESSAGE);
        ArrayList<String> strings = parseStr(data);
        return new ChatMessage(strings.get(0), new Date(strings.get(1)), Integer.parseInt(strings.get(2)), strings.get(3));

    }

    public static ChatRequest genChatRequest(byte[] bytes) {
        return genChatRequest(new String(bytes));
    }

    public static ChatRequest genChatRequest(String data) {
        assert (determineType(data) == CommandType.CHAT_REQUEST);
        ArrayList<String> strings = parseStr(data);
        return new ChatRequest(strings.get(0), new Date(strings.get(1)), strings.get(2));
    }

    public static ClientUpdateMessage genClientUpdateMessage(byte[] bytes) {
        return genClientUpdateMessage(new String(bytes));
    }

    public static ClientUpdateMessage genClientUpdateMessage(String data) {
        assert (determineType(data) == CommandType.CLIENT_UPDATE_MESSAGE);
        ArrayList<String> strings = parseStr(data);
        String[] names = new String[strings.size() - 1];
        for (int i = 1; i < strings.size(); i++) {
            names[i - 1] = strings.get(i);
        }
        return new ClientUpdateMessage(Integer.parseInt(strings.get(0)), names);
    }

    public static ConnectCommand genConnectCommand(byte[] bytes) {
        return genConnectCommand(new String(bytes));
    }

    public static ConnectCommand genConnectCommand(String data) {
        assert (determineType(data) == CommandType.CONNECT_COMMAND);
        ArrayList<String> strings = parseStr(data);
        return new ConnectCommand(strings.get(0), new Date(strings.get(1)));
    }

    public static ConnectResponse genConnectResponse(byte[] bytes) {
        return genConnectResponse(new String(bytes));
    }

    public static ConnectResponse genConnectResponse(String data) {
        assert (determineType(data) == CommandType.CONNECT_RESPONSE);
        ArrayList<String> strings = parseStr(data);

        String[] rooms = new String[strings.size() - 3];
        for (int i = 2; i < strings.size() - 1; i++) {
            rooms[i - 2] = strings.get(i);
        }
        return new ConnectResponse(
                Integer.parseInt(strings.get(0)),
                strings.get(1),
                rooms,
                new Date(strings.get(strings.size() - 1))
        );
    }

    public static JoinChatroomCommand genJoinChatroomCommand(byte[] bytes) {
        return genJoinChatroomCommand(new String(bytes));
    }

    public static JoinChatroomCommand genJoinChatroomCommand(String data) {
        assert (determineType(data) == CommandType.JOIN_CHATROOM_COMMAND);
        ArrayList<String> strings = parseStr(data);
        return new JoinChatroomCommand(
                strings.get(0),
                strings.get(1),
                new Date(strings.get(2))
        );
    }

    public static JoinChatroomResponse genJoinChatroomResponse(byte[] bytes) {
        return genJoinChatroomResponse(new String(bytes));
    }

    public static JoinChatroomResponse genJoinChatroomResponse(String data) {
        assert (determineType(data) == CommandType.JOIN_CHATROOM_RESPONSE);
        ArrayList<String> strings = parseStr(data);
        return new JoinChatroomResponse(
                Integer.parseInt(strings.get(0)),
                strings.get(1),
                strings.get(2),
                new Date(strings.get(3))
        );
    }
    
    public static byte[] serializeObject(Object obj) {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private static ArrayList<String> parseStr(String str) {
        int nextSpace = str.indexOf(" ", 0);
        str = str.substring(nextSpace + 1, str.length());
        ArrayList<String> result = new ArrayList<>();
        while (!str.equals("")) {
//            System.out.println(str);
            nextSpace = str.indexOf(" ", 0);
            if (nextSpace < 0) {
                str = "";
                continue;
            }
            int nextLen = Integer.parseInt(str.substring(0, nextSpace));
//            System.out.println("nextlen = " + nextLen);
            str = str.substring(nextSpace + 1, str.length());
            result.add(str.substring(0, nextLen));
            if (nextLen + 1 < str.length()) {
                str = str.substring(nextLen + 1, str.length());
            } else {
                str = "";
            }
//            System.out.println("result: " + result);
//            System.out.println("str: " + str);
//            System.out.println("str.len: " + str.length());
//            System.out.println("");
        }
        return result;
    }
}
