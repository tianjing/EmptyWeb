package tgtools.plugins.qrcode.util;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import tgtools.exceptions.APPErrorException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 11:31
 */
public class QrCodeHelper {
    public static void encodeQrCode(String pEncodeContent, OutputStream pOutputStream) throws APPErrorException {
        encodeQrCode(pEncodeContent, pOutputStream, 400);
    }

    public static void encodeQrCode(String pEncodeContent, OutputStream pOutputStream, int pSize) throws APPErrorException {
        try {

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(pEncodeContent, BarcodeFormat.QR_CODE, pSize, pSize, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", pOutputStream);
        } catch (Exception e) {
            throw new APPErrorException("生成二维码失败；原因：" + e.getMessage(), e);
        }
    }


    public static String decodeQrCode(File pFile) throws APPErrorException {
        if(!pFile.exists()) {
            throw new APPErrorException("文件不存在；File："+pFile.getAbsolutePath());
        }
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(pFile);
            return decodeQrCode(fis);
        } catch (FileNotFoundException e) {
            throw new APPErrorException("文件不存在；File："+pFile.getAbsolutePath());
        }
        finally {
            if(null!=fis)
            {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }

    }
    public static String decodeQrCode(InputStream pIutputStream) throws APPErrorException {

        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            BufferedImage image = ImageIO.read(pIutputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer  binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap,hints);
            return result.getText();
        } catch (Exception e) {
            throw new APPErrorException("解析出错；原因："+e.getMessage(),e);
        }
    }
}
