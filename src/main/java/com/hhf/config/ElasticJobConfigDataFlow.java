package com.hhf.config;


import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.hhf.task.MyDataFlowTask;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 流式任务
 */

//@Configuration
public class ElasticJobConfigDataFlow {

    @Autowired
    private MyDataFlowTask myDataFlowTask;

    @Autowired
    private CoordinatorRegistryCenter coordinatorRegistryCenter;

    /**
     * 流式任务
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param params
     * @return
     */
    private LiteJobConfiguration createDataflowLiteJobConfiguration(Class<? extends DataflowJob> jobClass,
                                                            String cron,
                                                            int shardingTotalCount,
                                                            String params){
        //创建
        JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
        //设置params
        if(!StringUtils.isEmpty(params)){
            builder.shardingItemParameters(params);
        }
        //创建 DataflowJobConfiguration
        DataflowJobConfiguration dataflowJobConfiguration=new DataflowJobConfiguration(builder.build(),jobClass.getCanonicalName(),true);
        //创建 LiteJobConfiguration
        LiteJobConfiguration build = LiteJobConfiguration.newBuilder(dataflowJobConfiguration).overwrite(true).build();
        return  build;
    }



    @Bean(initMethod = "init")
    public SpringJobScheduler initDataflowElasticJob(){
        //创建SpringJobScheduler
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(myDataFlowTask,
                coordinatorRegistryCenter,
                createDataflowLiteJobConfiguration(myDataFlowTask.getClass(), "0/10 * * * * ?", 1, ""));
        return springJobScheduler;
    }


}
