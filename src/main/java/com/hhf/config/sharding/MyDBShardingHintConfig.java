package com.hhf.config.sharding;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * hint、库分片策略
 * 自定义
 */

public class MyDBShardingHintConfig implements HintShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        Object[] objects = hintShardingValue.getValues().toArray();
        String key="sharding"+objects[0];
        if(collection.contains(key)){
            return Arrays.asList(key);
        }
        throw new UnsupportedOperationException("不支持"+key+"请检查配置");
    }
}
