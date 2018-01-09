package tgtools.plugins.websocket.audiofile;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.socket.*;
import tgtools.plugins.websocket.audiofile.entity.ChatRoom;
import tgtools.plugins.websocket.audiofile.entity.ChatUser;
import tgtools.plugins.websocket.audiofile.factory.ChatRoomFactory;
import tgtools.plugins.websocket.audiofile.factory.ChatUserFactory;
import tgtools.plugins.websocket.audiofile.handles.ByteHandle;
import tgtools.plugins.websocket.audiofile.util.HeaderHelper;
import tgtools.util.GUID;
import tgtools.util.StringUtil;
import tgtools.web.platform.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 18:14
 */
public class MyWebSocketHandler implements WebSocketHandler {
    private static final int Size_Limit=128*1024;
    private static Base64 mBase64 = new Base64();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
   // private static CopyOnWriteArraySet<MyWebSocketHandler> webSocketSet = new CopyOnWriteArraySet<MyWebSocketHandler>();


    private WebSocketSession mSession=null;
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        webSocketSession.setBinaryMessageSizeLimit(Size_Limit);
        webSocketSession.setTextMessageSizeLimit(Size_Limit);
        mSession=webSocketSession;
        try {
            System.out.println("afterConnectionEstablished;ID:" + webSocketSession.getId());
            HeaderHelper.parseParams(webSocketSession);
            String userName = null != webSocketSession.getAttributes().get("username") ? webSocketSession.getAttributes().get("username").toString() : null;
            String room = null != webSocketSession.getAttributes().get("room") ? webSocketSession.getAttributes().get("room").toString() : null;
            if (!StringUtil.isNullOrEmpty(userName)) {
                ChatUser user = ChatUserFactory.addUserByteSession(userName, webSocketSession, this);
                if (!StringUtil.isNullOrEmpty(room)) {
                    ChatRoomFactory.join(room, user);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    static String mPath=null;
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println(webSocketSession.getId()+"类型："+webSocketMessage.getClass()+"来自客户端的消息:"+webSocketMessage.getPayload().getClass() );
        System.out.println("username::"+webSocketSession.getAttributes().get("username")+";;this.hashCode::"+this.hashCode());
        try {
            ChatUserFactory.sendByte(webSocketSession,webSocketMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(WebSocketMessage<?>  message) throws IOException {
        this.mSession.sendMessage(message);
    }
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("handleTransportError");
        throwable.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("afterConnectionClosed");
        ChatUserFactory.removeUserAudioSession(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



}
