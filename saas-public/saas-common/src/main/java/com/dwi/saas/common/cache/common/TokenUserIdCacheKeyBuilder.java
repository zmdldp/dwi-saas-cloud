package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * #c_application
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class TokenUserIdCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TOKEN_USER_ID;
    }

//    @Override
//    public Duration getExpire() {
//        return Duration.ofMinutes(15);
//    }
}
