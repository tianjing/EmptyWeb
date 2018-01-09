package tgtools.plugins.websocket.audiofile.entity;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import tgtools.interfaces.IDispose;
import tgtools.plugins.websocket.audiofile.handles.ByteHandle;
import tgtools.plugins.websocket.audiofile.handles.TextHandle;

import java.io.IOException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:20
 */
public class ChatUser implements TextHandle, ByteHandle, IDispose {

    private String mName;
    private WebSocketSession mTextSession;
    private WebSocketSession mAudioSession;
    private ChatRoom mRoom;

    public ChatRoom getRoom() {
        return mRoom;
    }

    public void setRoom(ChatRoom pRoom) {
        mRoom = pRoom;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public WebSocketSession getTextSession() {
        return mTextSession;
    }

    public void setTextSession(WebSocketSession pTextSession) {
        mTextSession = pTextSession;
    }

    public WebSocketSession getAudioSession() {
        return mAudioSession;
    }

    public void setAudioSession(WebSocketSession pAudioSession) {
        mAudioSession = pAudioSession;
    }

    public void sendText(WebSocketMessage pMessage) {
        if (null == mTextSession || !mTextSession.isOpen()) {
            System.out.println("user sendText:mTextSession not open");
            return;
        }
        try {
            mTextSession.sendMessage(pMessage);
            System.out.println("user sendText"+pMessage.getPayload());
        } catch (IOException e) {
            System.out.println("发送消息错误！" + e.getMessage());
        }
    }

    public void sendByte(WebSocketMessage pMessage) {
        if (null == mAudioSession || !mAudioSession.isOpen()) {
            System.out.println("user:"+getName()+" sendByte:mAudioSession not open");
            return;
        }
        try {
            mAudioSession.sendMessage(pMessage);
            System.out.println("user:"+getName()+" sendByte");
        } catch (IOException e) {
            System.out.println("发送消息错误！" + e.getMessage());
        }
    }

    @Override
    public void handleText(WebSocketMessage pData) {
        if (null == mRoom) {
            System.out.println("handleText:null==mRoom");
            return;
        }
        System.out.println("User:handleText");
        mRoom.sendText(this, pData);
    }

    @Override
    public void handleByte(WebSocketMessage pData) {
        if (null == mRoom) {
            System.out.println("handleText:null==mRoom");
            return;
        }
        System.out.println("user:"+getName()+";;;handleByte");
        mRoom.sendByte(this, pData);
    }

    @Override
    public void Dispose() {
        mName = null;
        try {
            if(null!=mTextSession&&mTextSession.isOpen()) {
                mTextSession.close();
            }
        } catch (IOException e) {
        }
        mTextSession=null;

        try {
            if(null!=mAudioSession&&mAudioSession.isOpen()) {
                mAudioSession.close();
            }
        } catch (IOException e) {
        }
        mAudioSession=null;
        if(null!=mRoom) {
            mRoom.removeUser(this);
            mRoom = null;
        }
        System.out.println("user Dispose");
    }
}
