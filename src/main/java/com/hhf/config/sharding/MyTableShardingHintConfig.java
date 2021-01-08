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
 * hint、表分片策略
 * 自定义
 */

public class MyTableShardingHintConfig implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        String logicTableName = hintShardingValue.getLogicTableName();
        Collection<Long> values = hintShardingValue.getValues();
        Long s=(Long) values.toArray()[0];
        String key=logicTableName+"_"+s;
        if(collection.contains(key)){
            return Arrays.asList(key);
        }
        throw new UnsupportedOperationException("不支持"+key+"请检查配置");
    }
}
