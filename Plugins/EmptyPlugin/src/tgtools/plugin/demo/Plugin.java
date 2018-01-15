package tgtools.plugin.demo;

import tgtools.plugin.IPlugin;
import tgtools.plugin.demo.command.CommandFactory;
import tgtools.plugin.demo.rest.RestStartuper;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:09
 */
public class Plugin implements IPlugin {
    @Override
    public void load() throws Exception {
        RestStartuper.Statup();
    }

    @Override
    public void unload() throws Exception {
        RestStartuper.Shutdown();
    }

    @Override
    public Object execute(Object... objects) throws Exception {
        if(null==objects&&objects.length<1)
        {return null;}
        return CommandFactory.process(objects);
    }



}
