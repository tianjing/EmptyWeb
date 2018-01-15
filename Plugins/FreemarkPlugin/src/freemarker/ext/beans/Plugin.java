package freemarker.ext.beans;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tgtools.data.DataRow;
import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;
import tgtools.plugin.IPlugin;
import tgtools.util.FileUtil;
import tgtools.util.StringUtil;

import java.io.*;
import java.util.*;

public class Plugin implements IPlugin {
	static {
		m_Configurations = new HashMap<String, Configuration>();

	}
	private static HashMap<String, Configuration> m_Configurations;

	private Configuration get(String path) throws Exception {
		if (!m_Configurations.containsKey(path)) {
			Configuration config = new Configuration();
			//config.setDirectoryForTemplateLoading(new File(path));
			m_Configurations.put(path, config);
		}
		return m_Configurations.get(path);
	}

	@Override
	public void load() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void unload() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(Object... params) throws Exception {
		ByteArrayOutputStream dsa = new ByteArrayOutputStream();
		try {
			if (params.length < 2 || params.length > 5) {
				throw new APPErrorException("参数数量错误！应该有两个参数，第一个为数据，第二个为模板");
			}

			Object value = params[0];
			String encoding = "UTF-8";
			String outencoding="UTF-8";
			String filepath = "";
			try {
				filepath = (String) params[1];
			} catch (Exception e) {
				throw new APPErrorException("模板路径参数错误！");
			}
			try {
				if(!StringUtil.isNullOrEmpty((String) params[2])){
				encoding = (String) params[2];
				}
			} catch (Exception e) {
				encoding="UTF-8";
			}
			try {
				if(!StringUtil.isNullOrEmpty((String) params[3])){
				outencoding = (String) params[3];
				}
				
			} catch (Exception e) {
				outencoding="UTF-8";
			}
			File file;
			String filename="";
			if(params.length>4)
			{
				filepath=(String)params[4];
				filename = (String)params[1];
			}
			else
			{
				file = new File(filepath);
				filepath=file.getParent();
				filename=file.getName();
				if (!file.exists()) {
					throw new APPErrorException("模板不存在！");
				}
			}
			String sysencoding=System.getProperty("file.encoding");
			if(StringUtil.isNullOrEmpty(sysencoding))
			{
				sysencoding=outencoding;
			}

			Configuration cfg = get(filepath);
			
			cfg.setEncoding(Locale.SIMPLIFIED_CHINESE, encoding);
			// 设置模板文件的存放目录
			cfg.setDirectoryForTemplateLoading(new File(filepath));
			
		
			
			// 加载指定的模板
			//Template t = cfg.getTemplate(filename,Locale.SIMPLIFIED_CHINESE,encoding);
			Template t = cfg.getTemplate(filename,encoding);
			//LogHelper.info("", "encoding:" + encoding, "FreemarkPlugin");
			Writer out = new BufferedWriter(new OutputStreamWriter(dsa));
			
			t.process(value, out, new TableWrapper());
			out.flush();
			if("UTF-8".equals(outencoding))
			{
			return dsa.toString();
			}
			else
			{
				String res=removeBom(dsa.toString(),encoding);
				return new String( res.getBytes(encoding),outencoding);
			}
		} finally {
			try {
				if (null != dsa)
					dsa.close();
			} catch (Exception e) {
			}
		}
	}
	private String removeBom(String p_Data,String p_Encoding)
	{
		byte[] data=p_Data.getBytes();
		boolean issame=true;
		try {
			byte[] bom=new String(StringUtil.UTF8Bom,p_Encoding).getBytes();
			for(int i=0;i<bom.length;i++)
			{
				if(bom[i]!=data[i])
				{
					issame=false;
					break;
				}
			}
		if(issame)
		{
			return new String(data,bom.length,data.length-bom.length);
		}

		} catch (UnsupportedEncodingException e) {
			new APPErrorException("字符转换出错",e);
		}
		return p_Data;
	}
	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("message", "Hello World!");
		DataTable dt = new DataTable();
		dt.appendColumn("name");
		dt.appendColumn("sex");
		DataRow row = dt.appendRow();
		row.setValue("name", "tg1");
		row.setValue("sex", 2.2);

		DataRow row1 = dt.appendRow();
		row1.setValue("name", "FDA的放大");
		row1.setValue("sex", 2.2);
		List< A> list=new ArrayList< A>();
		 A a=new A();
		 a.setDt(dt);
		list.add(a);

		root.put("table", dt);
		root.put("fdj", new DataTable());
		root.put("xl", new DataTable());
		root.put("byq", "");
		root.put("list1",list);

		Map<String ,B> dd=new HashMap<String, B>();
		dd.put("114",new B("222","333"));
		dd.put("115",new B("444","555"));
		root.put("marketMap", dd);
		Plugin plu = new Plugin();
		try {
			String result = (String) plu.execute(root,
					"FreemarkPlugin/test1.ftl","gbk","UTF-8","C:/Works/DQ/javademos/");
			FileUtil.writeFile("C:\\tianjing\\Desktop\\fda.txt",result,"GBK");
			
//			 File file=new File("C:/Users/tian_/Desktop/test.txt");
//			 if(!file.exists())
//			 {
//				 file.createNewFile();
//			 }
			// FileOutputStream ss=new FileOutputStream(new File("C:/Users/tian_/Desktop/test.txt"));
			// OutputStreamWriter write =new OutputStreamWriter(ss,"gb2312");
			// write.write( result);
			// write.flush();
			// write.close();
			 //ss.write(result.getBytes());
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static class A{
		private DataTable dt;

		public DataTable getDt() {
			return dt;
		}

		public void setDt(DataTable dt) {
			this.dt = dt;
		}
		
		
	}
	public static class B{
		public B(String p_id,String p_name)
		{
			setId(p_id);
			setName(p_name);
		}
		private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String name;
		
		
	}
}
