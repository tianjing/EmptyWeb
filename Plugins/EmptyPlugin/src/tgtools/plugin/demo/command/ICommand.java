package tgtools.plugin.demo.command;

import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 16:56
 */
public interface ICommand {
    Object excute(Object... params) throws APPErrorException;
}
