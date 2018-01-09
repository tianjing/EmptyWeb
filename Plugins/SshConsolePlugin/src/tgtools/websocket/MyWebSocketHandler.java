package tgtools.websocket;

import org.springframework.web.socket.*;
import tgtools.exceptions.APPErrorException;
import tgtools.exceptions.APPRuntimeException;
import tgtools.ssh.IReceveMessageLinsten;
import tgtools.ssh.MessageEvent;
import tgtools.ssh.SshShellClient;
import tgtools.util.RegexHelper;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 18:14
 */
public class MyWebSocketHandler implements WebSocketHandler {

    private WebSocketSession mWebSocketSession;
    private SshShellClient mSshShellClient;


    private void sendMessage(String pMessage) {
        if (null != mWebSocketSession) {
            try {
                TextMessage text = new TextMessage(pMessage);
                mWebSocketSession.sendMessage(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("afterConnectionEstablished;ID:"+webSocketSession.getId());
        mSshShellClient = new SshShellClient();
        mSshShellClient.setIReceveMessageLinsten(new MyReceveListen());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        mWebSocketSession = webSocketSession;
        String message = (String) webSocketMessage.getPayload();
        System.out.println("handleMessage:" + message);

        if (message.length() > 10 && message.indexOf(",") > 0) {
            String[] messages = message.split(",");
            if (messages.length == 3 || messages.length == 4) {
                String user = messages[0];
                String pwd = messages[1];
                String host = messages[2];
                int port = 22;
                if (messages.length == 4 && RegexHelper.isNubmer(messages[3])) {
                    port = Integer.parseInt(messages[3]);
                }
                try {
                    mSshShellClient.connect(user, pwd, host, port);
                    System.out.println("已连接");
                }
                catch (Exception ex)
                {
                    sendMessage("连接失败；原因："+ex.getMessage());
                    throw new APPRuntimeException("连接失败；原因：",ex);
                }
                return;
            }

        }
        mSshShellClient.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        mWebSocketSession = webSocketSession;
        afterConnectionClosed(webSocketSession,CloseStatus.PROTOCOL_ERROR);
        System.out.println("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("afterConnectionClosed");
        mSshShellClient.Dispose();
        mWebSocketSession = null;
        mSshShellClient = null;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    private class MyReceveListen implements IReceveMessageLinsten {
        @Override
        public void OnReceveMessage(MessageEvent pEvent) {
            if (null != MyWebSocketHandler.this.mWebSocketSession && MyWebSocketHandler.this.mWebSocketSession.isOpen()) {
                MyWebSocketHandler.this.sendMessage(pEvent.getMessage());
            }
        }
    }


}
