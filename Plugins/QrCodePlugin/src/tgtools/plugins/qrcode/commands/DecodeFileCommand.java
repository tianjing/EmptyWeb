package tgtools.plugins.qrcode.commands;

import tgtools.exceptions.APPErrorException;
import tgtools.plugins.qrcode.util.QrCodeHelper;

import java.io.File;
import java.io.InputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:24
 */
public class DecodeFileCommand implements ICommand {

    @Override
    public Object excute(Object... params) throws APPErrorException {
        if (params.length != 1 && null == params[0] && !(params[0] instanceof File)) {
            throw new APPErrorException("只能输入一个File");
        }
        File content = (File) params[0];
        return QrCodeHelper.decodeQrCode(content);
    }
}
