package tgtools.plugins.websocket.audiofile.factory;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import tgtools.plugins.websocket.audiofile.MyTextWebSocketHandler;
import tgtools.plugins.websocket.audiofile.MyWebSocketHandler;
import tgtools.plugins.websocket.audiofile.entity.ChatUser;
import tgtools.util.StringUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:04
 */
public class ChatUserFactory {
    private static ConcurrentHashMap<String,ChatUser> mUsers=new ConcurrentHashMap<String,ChatUser>();
    public synchronized static ChatUser addUserTextSession(String pUserName, WebSocketSession pSession, MyTextWebSocketHandler pWebSocketHandler)
    {
        System.out.println("addUserTextSession："+pUserName);
        if(!mUsers.containsKey(pUserName))
        {
            ChatUser user =new ChatUser();
            user.setName(pUserName);
            mUsers.put(pUserName,user);
        }
        ChatUser user=mUsers.get(pUserName);
        user.setTextSession(pSession);
        return user;
    }
    public synchronized static void removeUserTextSession(WebSocketSession pWebSocketSession)
    {
        String username=(String)pWebSocketSession.getAttributes().get("username");
        if(mUsers.containsKey(username))
        {
           ChatUser user= mUsers.get(username);
           mUsers.remove(username);
           user.Dispose();
           user=null;
        }
    }

    public synchronized static ChatUser addUserByteSession(String pUserName, WebSocketSession pSession, MyWebSocketHandler pWebSocketHandler)
    {
        System.out.println("addUserByteSession："+pUserName);
        if(!mUsers.containsKey(pUserName))
        {
            ChatUser user =new ChatUser();
            user.setName(pUserName);
            mUsers.put(pUserName,user);
        }
        ChatUser user=mUsers.get(pUserName);
        user.setAudioSession(pSession);
        return user;
    }
    public static void removeUserAudioSession(WebSocketSession pWebSocketSession)
    {
        removeUserTextSession(pWebSocketSession);
    }

    public static void sendText(WebSocketSession pWebSocketSession, WebSocketMessage<?> webSocketMessage)
    {
        String username=(String)pWebSocketSession.getAttributes().get("username");
        if(mUsers.containsKey(username))
        {
            mUsers.get(username).handleText(webSocketMessage);
        }
    }
    public static void sendByte(WebSocketSession pWebSocketSession, WebSocketMessage<?> webSocketMessage)
    {
        String username=(String)pWebSocketSession.getAttributes().get("username");
        if(mUsers.containsKey(username))
        {
            mUsers.get(username).handleByte(webSocketMessage);
        }
    }
}
