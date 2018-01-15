package tgtools.plugin.demo.rest;

import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.message.IMessageListening;
import tgtools.message.Message;
import tgtools.plugin.demo.util.LogHelper;
import tgtools.web.platform.Platform;
import tgtools.web.platform.PlatformDispatcherServletFactory;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:49
 */
public class RestStartuper {
    private static final String URL="/demo";
    private static final String REST="rest";


    public static void Statup() {
        try {
            tgtools.message.MessageFactory.registerListening(new MyMessageListen());
        }catch (Exception e)
        {
            LogHelper.error("NotifyRest加载失败。原因："+e.getMessage(),e);
        }

    }

    public static void Shutdown() {
        try {
            Platform.removeRest(URL);
            PlatformDispatcherServletFactory.getDefaultDispatcher().removeWebsocket(URL);
        }catch (Exception e)
        {
            LogHelper.error("NotifyRest卸载失败。原因："+e.getMessage(),e);
        }

    }

    private static class MyMessageListen implements IMessageListening {

        @Override
        public String getName() {
            return "WebSocketListen";
        }

        @Override
        public void onMessage(Message message) {
            try {
                if ("addDispatcherServlet".equals(message.getEvent())) {
                    System.out.println("Message Content :"+message.getContent());
                    JSONObject json =new JSONObject(message.getContent());
                    if(REST.equals(json.getString("ServletName")))
                    {
                        Platform.addRest(REST,URL,DemoRest.class);
                        tgtools.message.MessageFactory.unRegisterListening(getName());
                    }
                }
            } catch (APPErrorException e) {
                tgtools.util.LogHelper.error("", "region mywebsocket Error", getName(), e);
            }

        }
    }
}
