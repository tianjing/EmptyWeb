package com.test;

import tgtools.service.BaseService;
import tgtools.tasks.TaskContext;
import tgtools.tasks.TaskRunner;
import tgtools.util.DateUtil;

import java.util.Date;

/**
 * 名  称：
 * 编写者：田径
 * 功  能：
 * 时  间：14:52
 */
public class TestServices2 extends BaseService {
    /**
     * 服务名称，部署时用（唯一） 必须
     */
    @Override
    public String getName() {
        return "TestServices2";
    }

    /**
     * 执行间隔 （毫秒） 必须
     */
    @Override
    protected int getInterval() {
        // TODO Auto-generated method stub
        return 30000;
    }

    /**
     * 结束时间  DateUtil.getMaxDate() 为永不结束 必须
     */
    @Override
    protected Date getEndTime() {
        // TODO Auto-generated method stub
        return DateUtil.getMaxDate();
    }
    @Override
    public boolean isConcurrency() {
        return false;
    }
    /**
     * 服务的主要执行代码 必须
     */
    @Override
    public void run(TaskContext p_Param) {

        tgtools.util.LogHelper.info("", "测试任务开始", "TestServices");
        TaskRunner<MyTask> runer = new TaskRunner<MyTask>();
        for (int i = 0; i < 1000; i++) {
            runer.add(new MyTask());
        }

        runer.runThreanTillEnd();
        tgtools.util.LogHelper.info("", "测试任务结束", "TestServices");
    }

    public static void main(String[] args)
    {
        System.out.println(System.currentTimeMillis());
    }
}
