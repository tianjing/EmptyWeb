package tgtools.plugins.websocket.audiofile.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import tgtools.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:24
 */
public final class HeaderHelper {

    public static void parseParams(WebSocketSession pWebSocketSession)
    {
        Map<String,String> params=getParams(pWebSocketSession.getUri().toString());
        pWebSocketSession.getAttributes().putAll(params);
    }
    public static Map<String,String> getParams(String pUrl){
        int index=pUrl.indexOf("?")+1;
        HashMap<String,String> map =new  HashMap<String,String>();
        if(index>0) {
            pUrl = pUrl.substring(index);
            String[] params=pUrl.split("&");
            for(String param :params)
            {
                System.out.println("param:"+param);
                int paramindex=param.indexOf("=");
                if(paramindex>0)
                {

                    map.put(param.substring(0,paramindex),param.substring(paramindex+1));
                }
            }
        }
        return map;
    }

}
