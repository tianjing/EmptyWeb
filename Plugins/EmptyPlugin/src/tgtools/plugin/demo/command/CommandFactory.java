package tgtools.plugin.demo.command;

import tgtools.exceptions.APPErrorException;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 16:56
 */
public class CommandFactory {
    private static HashMap<String, ICommand> m_Commands = new HashMap<String, ICommand>(){{
    }};

    public static Object process(Object... params) throws APPErrorException {
        String command = (String) params[0];
        Object[] param = Arrays.copyOfRange(params, 1, params.length);
        ICommand comm =m_Commands.get(command);
        if(null==comm)
        {throw new APPErrorException("无法识别的命令；"+command);}
        return comm.excute(param);
    }


}
