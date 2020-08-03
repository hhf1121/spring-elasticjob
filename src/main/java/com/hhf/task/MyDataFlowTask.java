package com.hhf.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.hhf.entity.User;
import com.hhf.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 流式任务
 * 如果有未处理数据，会一直抓取数据。
 * 如果没有数据，根据cron表达式，周期性执行抓取数据。
 */
@Component
@Slf4j
public class MyDataFlowTask implements DataflowJob<User> {

    @Autowired
    private IUserService userService;


    /**
     * 抓取数据
     * @param shardingContext
     * @return
     */
    @Override
    public List<User> fetchData(ShardingContext shardingContext) {
        log.info("分片:"+shardingContext.getShardingItem());
        log.info("此分片参数:"+shardingContext.getShardingParameter());
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(shardingContext.getShardingParameter())){
            queryWrapper.eq("yes",shardingContext.getShardingParameter());
        }
        return userService.list(queryWrapper);
    }

    /**
     * 处理数据
     * @param shardingContext
     * @param list
     */
    @Override
    public void processData(ShardingContext shardingContext, List<User> list) {
        list.forEach(o->{
            log.info("分片:"+shardingContext.getShardingItem()+"->"+o.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
        });
    }
}
