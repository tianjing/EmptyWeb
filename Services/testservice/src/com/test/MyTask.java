package com.test;

import com.test.util.LogHelper;
import tgtools.data.DataTable;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;

/**
 * 名  称：
 * 编写者：田径
 * 功  能：
 * 时  间：16:35
 */
public class MyTask  extends Task{
        @Override
        protected boolean canCancel() {
            return false;
        }

        @Override
        public void run(TaskContext taskContext) {
            int count=0;
            try {
                while(count<1000) {
                    DataTable dt = tgtools.db.DataBaseFactory.getDefault().Query("select top 10 * from loginfo");
                    String ss = dt.toJson();
                    LogHelper.info("ss:"+ss);
                    count++;
                }
            }catch(Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

}
