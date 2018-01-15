package tgtools.plugins.pinyin;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import tgtools.plugin.IPlugin;
import tgtools.util.StringUtil;


public class Plugin implements IPlugin {


	@Override
	public void load() throws Exception {

	}

	@Override
	public void unload() throws Exception {

	}

	@Override
	public Object execute(Object... params) throws Exception {
		if(params.length>3||params.length<1)
		{
			throw new Exception("参数个数不对，请确认;最大支持3个参数，中文String,分隔符String（默认：,）,是否带音标boolean（默认：false）");
		}
		String str=(String)params[0];


		String split=",";
		boolean isyinbiao=false;
		if(params.length>1&&params[1] instanceof String)
		{
			split=(String)params[1];
		}
		if(params.length>2&&params[2] instanceof Boolean)
		{
			isyinbiao=(Boolean)params[2];
		}
		String result= StringUtil.EMPTY_STRING;
		if(!isyinbiao) {
			result= PinyinHelper.convertToPinyinString(str, split, PinyinFormat.WITHOUT_TONE);
		}
		else
		{
			result= PinyinHelper.convertToPinyinString(str, split, PinyinFormat.WITH_TONE_MARK);
		}
		return result;
	}


}
