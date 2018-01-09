package tgtools.plugins.qrcode;

import tgtools.plugin.IPlugin;
import tgtools.plugins.qrcode.commands.CommandFactory;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 11:16
 */
public class Plugin implements IPlugin {


    @Override
    public void load() throws Exception {

    }

    @Override
    public void unload() throws Exception {

    }

    @Override
    public Object execute(Object... objects) throws Exception {
        if(null==objects&&objects.length<1)
        {return null;}
        return CommandFactory.process(objects);
    }
}
