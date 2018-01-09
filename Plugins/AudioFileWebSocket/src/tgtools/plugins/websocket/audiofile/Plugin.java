package tgtools.plugins.websocket.audiofile;

import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.message.IMessageListening;
import tgtools.message.Message;
import tgtools.util.LogHelper;
import tgtools.web.platform.PlatformDispatcherServletFactory;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:02
 */
public class Plugin implements tgtools.plugin.IPlugin {

    private String REST_AUDIO_NAME="/myaudiowebsocket";
    private String REST_TEXT_NAME="/myaudiowebsockettext";
    @Override
    public void load() throws Exception {
        tgtools.message.MessageFactory.registerListening(new MyMessageListen());
    }

    @Override
    public void unload() throws Exception {
        PlatformDispatcherServletFactory.getDefaultDispatcher().removeWebsocket(REST_AUDIO_NAME);
        PlatformDispatcherServletFactory.getDefaultDispatcher().removeWebsocket(REST_TEXT_NAME);
    }

    @Override
    public Object execute(Object... objects) throws Exception {
        throw new APPErrorException("没有实现");
    }


    private class MyMessageListen implements IMessageListening {

        @Override
        public String getName() {
            return "AudioFileWebsocketListener";
        }

        @Override
        public void onMessage(Message message) {
            try {
                if ("addDispatcherServlet".equals(message.getEvent())) {
                    System.out.println("Message Content :"+message.getContent());
                    JSONObject json =new JSONObject(message.getContent());
                    if("rest".equals(json.getString("ServletName")))
                    {
                        PlatformDispatcherServletFactory.getDispatcher("rest").addWebsocket(REST_AUDIO_NAME, new WebSocketHttpRequestHandler(new MyWebSocketHandler()));
                        PlatformDispatcherServletFactory.getDispatcher("rest").addWebsocket(REST_TEXT_NAME, new WebSocketHttpRequestHandler(new MyTextWebSocketHandler()));

                        tgtools.message.MessageFactory.unRegisterListening(getName());
                    }
                }
            } catch (APPErrorException e) {
                LogHelper.error("", "region mywebsocket Error", getName(), e);
            }

        }
    }

}
