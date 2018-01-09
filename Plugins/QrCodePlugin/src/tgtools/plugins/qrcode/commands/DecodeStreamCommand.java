package tgtools.plugins.qrcode.commands;

import tgtools.exceptions.APPErrorException;
import tgtools.plugins.qrcode.util.QrCodeHelper;

import java.io.InputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:24
 */
public class DecodeStreamCommand implements ICommand {

    @Override
    public Object excute(Object... params) throws APPErrorException {
        if (params.length != 1 && null == params[0] && !(params[0] instanceof InputStream)) {
            throw new APPErrorException("只能输入一个InputStream");
        }
        InputStream content = (InputStream) params[0];
        return QrCodeHelper.decodeQrCode(content);
    }
}
