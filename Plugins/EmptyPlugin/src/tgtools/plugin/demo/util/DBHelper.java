package tgtools.plugin.demo.util;

import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:15
 */
public class DBHelper {

    public static int executeUpdate(String pSql) throws APPErrorException {
       return tgtools.db.DataBaseFactory.getDefault().executeUpdate(pSql);
    }
    public static DataTable Query(String pSql) throws APPErrorException {
        return tgtools.db.DataBaseFactory.getDefault().Query(pSql);
    }
    public static DataTable select(String pSql) throws APPErrorException {
        return tgtools.db.DataBaseFactory.getDefault().Query(pSql);
    }
}
