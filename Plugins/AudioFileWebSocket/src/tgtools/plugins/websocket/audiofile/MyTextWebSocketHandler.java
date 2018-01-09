package tgtools.plugins.websocket.audiofile;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import tgtools.plugins.websocket.audiofile.entity.ChatUser;
import tgtools.plugins.websocket.audiofile.factory.ChatRoomFactory;
import tgtools.plugins.websocket.audiofile.factory.ChatUserFactory;
import tgtools.plugins.websocket.audiofile.handles.TextHandle;
import tgtools.plugins.websocket.audiofile.util.HeaderHelper;
import tgtools.util.StringUtil;

import java.io.IOException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 18:14
 */
public class MyTextWebSocketHandler implements WebSocketHandler {

    static String mPath = null;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        try {
            System.out.println("afterConnectionEstablished;ID:" + webSocketSession.getId());
            HeaderHelper.parseParams(webSocketSession);
            String userName = null != webSocketSession.getAttributes().get("username") ? webSocketSession.getAttributes().get("username").toString() : null;
            String room = null != webSocketSession.getAttributes().get("room") ? webSocketSession.getAttributes().get("room").toString() : null;
            if (!StringUtil.isNullOrEmpty(userName)) {
                ChatUser user = ChatUserFactory.addUserTextSession(userName, webSocketSession, this);
                if (!StringUtil.isNullOrEmpty(room)) {
                    ChatRoomFactory.join(room, user);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println(webSocketSession.getId() + "类型：" + webSocketMessage.getClass() + "来自客户端的消息:" + webSocketMessage.getPayload().getClass());
        try {
            ChatUserFactory.sendText(webSocketSession,webSocketMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("handleTransportError");
        throwable.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("afterConnectionClosed");
        ChatUserFactory.removeUserTextSession(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
