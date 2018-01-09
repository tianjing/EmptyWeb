package tgtools.ssh;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import tgtools.exceptions.APPErrorException;
import tgtools.interfaces.IDispose;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;

import java.io.*;
import java.util.Properties;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:35
 */
public class SshShellClient extends Task implements IDispose {

    private String mUser;
    private String mPassword;
    private String mHost;
    private int mPort;
    private Session mSession;
    private ChannelShell mChannelShell = null;
    private Properties mConfig;

    public void setIReceveMessageLinsten(IReceveMessageLinsten pIReceveMessageLinsten) {
        this.mIReceveMessageLinsten = pIReceveMessageLinsten;
    }

    private IReceveMessageLinsten mIReceveMessageLinsten;
    private InputStream mInputStream;
    private OutputStream mOutputStream;

    public SshShellClient() {
    }

    public Properties getConfig() {
        if (null == mConfig) {
            mConfig = new Properties();
            mConfig.put("StrictHostKeyChecking", "no");
        }
        return mConfig;
    }

    public void setConfig(Properties pConfig) {
        this.mConfig = mConfig;
    }


    public void sendMessage(String pMessage) {
        try {
            mOutputStream.write(pMessage.getBytes());
            mOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(String pUser, String pPassword, String pHost, int pPort) throws APPErrorException {
        mUser = pUser;
        mPassword = pPassword;
        mHost = pHost;
        mPort = pPort;
        System.out.println("mUser:"+mUser+"mPassword:"+mPassword+"mHost:"+mHost+"mPort:"+mPort);
        JSch jsch = new JSch();
        try {
            // getSession()只是创建一个session，需要设置必要的认证信息之后，调用connect()才能建立连接。
            mSession = jsch.getSession(mUser, mHost, mPort);
            mSession.setConfig(getConfig());
            mSession.setPassword(mPassword);

            mSession.connect();
            mChannelShell = (ChannelShell) mSession.openChannel("shell");

            PipedInputStream input = new PipedInputStream();
            mOutputStream = new PipedOutputStream(input);
            PipedOutputStream out = new PipedOutputStream();
            mInputStream = new PipedInputStream(out);


            mChannelShell.setInputStream(input);
            mChannelShell.setOutputStream(out);
            mChannelShell.connect(15000);
            this.runThread(null);
        } catch (Exception e) {
            throw new APPErrorException("创建SSH连接失败;"+e.getMessage(), e);
        }
    }
    public static void main(String[]args)
    {
        SshShellClient client =new SshShellClient();
        try {
            client.connect("tianjing","tianjing","192.168.88.32",22);
        } catch (APPErrorException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected boolean canCancel() {
        return true;
    }

    @Override
    public void run(TaskContext taskContext) {
        InputStreamReader dd=new InputStreamReader(mInputStream);
        char[] data = new char[4096];
        while(true) {
            if(isCancel()){return;}
            int length = 0;
            try {
                while ((length = dd.read(data)) > 0) {
                    System.out.println(new String(data, 0, length));
                    if (null != mIReceveMessageLinsten) {
                        MessageEvent event = new MessageEvent(new String(data, 0, length));
                        mIReceveMessageLinsten.OnReceveMessage(event);
                        length=0;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(isCancel()){return;}
        }
    }
    @Override
    public void Dispose() {
        this.cancel();
        try {
            if(null!=mInputStream){mInputStream.close();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        mInputStream=null;

        try {
            if(null!=mOutputStream){ mOutputStream.close();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        mOutputStream=null;

        if (null != mChannelShell && mChannelShell.isConnected()) {
            mChannelShell.disconnect();
        }
        mChannelShell = null;

        if (null != mSession && mSession.isConnected()) {
            mSession.disconnect();
        }
        mSession = null;
        mIReceveMessageLinsten=null;
        mConfig=null;
    }


}
