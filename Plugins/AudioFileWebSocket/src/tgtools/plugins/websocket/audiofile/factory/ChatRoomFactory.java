package tgtools.plugins.websocket.audiofile.factory;

import tgtools.plugins.websocket.audiofile.entity.ChatRoom;
import tgtools.plugins.websocket.audiofile.entity.ChatUser;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:05
 */
public class ChatRoomFactory {
    private static ConcurrentHashMap<String,ChatRoom> mRooms=new ConcurrentHashMap<String,ChatRoom>();

    public synchronized static void join(String pRoomName,ChatUser pUser)
    {
        if(!mRooms.containsKey(pRoomName))
        {
            ChatRoom room =new ChatRoom();
            room.setName(pRoomName);
            mRooms.put(pRoomName,room);
            System.out.println("创建房间："+pRoomName);
        }
        mRooms.get(pRoomName).addUser(pUser);
        System.out.println(pUser.getName()+"加入房间"+pRoomName);
    }


}
