package tgtools.plugins.websocket.audiofile.handles;

import org.springframework.web.socket.WebSocketMessage;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:52
 */
public interface ByteHandle {
    void handleByte(WebSocketMessage pData);
}
