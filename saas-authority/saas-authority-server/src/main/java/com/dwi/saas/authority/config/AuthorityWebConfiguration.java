package com.dwi.saas.authority.config;

import com.dwi.basic.boot.config.BaseConfig;
import com.dwi.basic.log.event.SysLogListener;
import com.dwi.saas.authority.service.common.OptLogService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dwi
 * @date 2017-12-15 14:42
 */
@Configuration
public class AuthorityWebConfiguration extends BaseConfig {

    /**
     * saas.log.enabled = true 并且 saas.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${saas.log.enabled:true} && 'DB'.equals('${saas.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(optLogService::save);
    }
}
