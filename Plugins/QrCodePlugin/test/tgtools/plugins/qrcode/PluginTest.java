package tgtools.plugins.qrcode;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:40
 */
public class PluginTest {

    @Test
    public void test_EncodeString() {
        String content ="fdsafadsfdsafdas";
        String filepath="C:\\tianjing\\Desktop\\项目\\fda.jpg";
        Plugin plugin =new Plugin();
        try {
            Object res= plugin.execute("EncodeString",content);
            if(null!=res)
            {
                File file=new File(filepath);
                new FileOutputStream(file).write((byte[])res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_DecodeFile() {
        String filepath="C:\\tianjing\\Desktop\\项目\\fda.jpg";
        File file=new File(filepath);
        Plugin plugin =new Plugin();
        try {
            Object res= plugin.execute("DecodeFile",file);
            if(null!=res)
            {
                System.out.println("res:"+res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}