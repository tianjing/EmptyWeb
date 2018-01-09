package tgtools.plugins.websocket.audiofile.entity;

import org.springframework.web.socket.WebSocketMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:53
 */
public class ChatRoom {

    private String mName;
    private List<ChatUser> mUsers=new ArrayList<ChatUser>();

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public synchronized void addUser(ChatUser pUser) {
        if (null == pUser) {
            return;
        }
        if(mUsers.contains(pUser)){return;}
        mUsers.add(pUser);
        pUser.setRoom(this);
    }

    public synchronized void removeUser(ChatUser pUser) {
        if (null == pUser) {
            return;
        }
        System.out.println("romm removeUser");
        mUsers.remove(pUser);
    }

    public void sendText(ChatUser pUser, WebSocketMessage pMessage) {
        System.out.println("room sendText   mUsers.size:"+ mUsers.size());
        for (int i = 0; i < mUsers.size(); i++) {
                mUsers.get(i).sendText(pMessage);
        }
    }

    public void sendByte(ChatUser pUser, WebSocketMessage pMessage) {
        System.out.println("room sendByte   mUsers.size:"+ mUsers.size());
        for (int i = 0; i < mUsers.size(); i++) {
            ChatUser user= mUsers.get(i);
            System.out.println("user sendByte user:"+user.getName()+"usergetAudioSession:"+ user.getAudioSession().hashCode());
            if (user != pUser) {
                user.sendByte(pMessage);
            }
        }
    }
}
