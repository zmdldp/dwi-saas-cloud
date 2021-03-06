package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class OnlineCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ONLINE;
    }

//    @Override
//    public Duration getExpire() {
//        return Duration.ofMinutes(15);
//    }
}
