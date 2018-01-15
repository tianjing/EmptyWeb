package freemarker.ext.beans;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tgtools.data.DataRow;
import tgtools.data.DataTable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Test {
public static void main(String[] args)
{
    try {
        Map<String,Object>  root = new HashMap<String,Object>();
        root.put( "message", "Hello World!" );
        DataTable dt=new DataTable();
        dt.appendColumn("name");
        dt.appendColumn("sex");
        DataRow row= dt.appendRow();
        row.setValue("name", "tg1");
        row.setValue("sex", 2.2);
        
        DataRow row1= dt.appendRow();
        row1.setValue("name", "放大");
        row1.setValue("sex", 2.2);
        
        root.put("table", dt);
 Configuration cfg = new Configuration();
  //设置模板文件的存放目录
 cfg.setDirectoryForTemplateLoading( new File("C:\\Works\\DQ\\javademos/FreemarkPlugin" ));
  //加载指定的模板
  Template t = cfg.getTemplate( "test.ftl");
  //处理合并
 ByteArrayOutputStream dsa= new ByteArrayOutputStream();
Writer out = new BufferedWriter(
     new OutputStreamWriter(dsa, t.getEncoding()));

 t.process(root, out,new TableWrapper());
 out.flush();
 
 File file=new File("C:/Users/tian_/Desktop/test.txt");
 if(!file.exists())
 {
	 file.createNewFile();
 }
 FileOutputStream ss=new FileOutputStream(new File("C:/Users/tian_/Desktop/test.txt"));
 OutputStreamWriter write =new OutputStreamWriter(ss,"UTF-8");
 write.write(dsa.toString());
 write.flush();
 write.close();
 System. out .println(dsa.toString());
 }
  catch (Exception e)
 {
       e.printStackTrace();
 }
}
	

}

	

