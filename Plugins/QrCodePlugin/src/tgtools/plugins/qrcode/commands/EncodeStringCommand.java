package tgtools.plugins.qrcode.commands;

import tgtools.exceptions.APPErrorException;
import tgtools.plugins.qrcode.util.QrCodeHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:24
 */
public class EncodeStringCommand implements ICommand{

    @Override
    public Object excute(Object... params) throws APPErrorException {
        if(params.length!=1 &&null== params[0]&&!(params[0] instanceof String))
        {
            throw new APPErrorException("只能输入一个字符串参数");
        }
        String content=(String)params[0];
        ByteArrayOutputStream ops=new ByteArrayOutputStream();
        try {
            QrCodeHelper.encodeQrCode(content,ops);
            return ops.toByteArray();
        }
        finally {
            try {
                ops.close();
            } catch (IOException e) {

            }
        }
    }
}
