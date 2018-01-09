package tgtools.plugin;

import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;
import tgtools.exceptions.APPErrorException;
import tgtools.interfaces.IDispose;
import tgtools.json.JSONObject;
import tgtools.message.IMessageListening;
import tgtools.message.Message;
import tgtools.util.LogHelper;
import tgtools.web.platform.PlatformDispatcherServletFactory;
import tgtools.websocket.MyWebSocketHandler;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:02
 */
public class Plugin implements tgtools.plugin.IPlugin {
    @Override
    public void load() throws Exception {
        tgtools.message.MessageFactory.registerListening(new MyMessageListen());
    }

    @Override
    public void unload() throws Exception {
        PlatformDispatcherServletFactory.getDefaultDispatcher().removeWebsocket("/mywebsocket");
    }

    @Override
    public Object execute(Object... objects) throws Exception {
        throw new APPErrorException("没有实现");
    }


    private class MyMessageListen implements IMessageListening {

        @Override
        public String getName() {
            return "SshConsolePluginMyMessageListen";
        }

        @Override
        public void onMessage(Message message) {
            try {
                if ("addDispatcherServlet".equals(message.getEvent())) {
                    System.out.println("Message Content :"+message.getContent());
                    JSONObject json =new JSONObject(message.getContent());
                    if("rest".equals(json.getString("ServletName")))
                    {
                        PlatformDispatcherServletFactory.getDispatcher("rest").addWebsocket("/mywebsocket", new WebSocketHttpRequestHandler(new MyWebSocketHandler()));
                        //PlatformDispatcherServletFactory.getDefaultDispatcher().addWebsocket("/mywebsocket", new WebSocketHttpRequestHandler(new MyWebSocketHandler()));
                        tgtools.message.MessageFactory.unRegisterListening("SshConsolePluginMyMessageListen");
                    }
                }
            } catch (APPErrorException e) {
                LogHelper.error("", "region mywebsocket Error", "SshConsolePluginMyMessageListen", e);
            }

        }
    }

}
