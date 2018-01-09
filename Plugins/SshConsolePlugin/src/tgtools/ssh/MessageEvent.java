package tgtools.ssh;

import tgtools.interfaces.Event;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 9:53
 */
public class MessageEvent extends Event {
    public MessageEvent(String pMessage)
    {
        mMessage=pMessage;
    }
    public String getMessage() {
        return mMessage;
    }

    private String mMessage;
}
