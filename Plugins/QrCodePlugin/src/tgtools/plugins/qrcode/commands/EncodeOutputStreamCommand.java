package tgtools.plugins.qrcode.commands;

import tgtools.exceptions.APPErrorException;
import tgtools.plugins.qrcode.util.QrCodeHelper;

import java.io.OutputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:24
 */
public class EncodeOutputStreamCommand implements ICommand {

    @Override
    public Object excute(Object... params) throws APPErrorException {
        if (params.length != 2 && null == params[0] && !(params[0] instanceof String) && null == params[1] && !(params[1] instanceof OutputStream)) {
            throw new APPErrorException("只能输入一个字符串参数,一个输出流");
        }
        String content = (String) params[0];
        OutputStream ops = (OutputStream) params[1];

        QrCodeHelper.encodeQrCode(content, ops);
        return null;
    }
}
