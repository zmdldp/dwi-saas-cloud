package com.dwi.saas.gateway.biz.config.datasource;


import com.dwi.basic.database.datasource.BaseMybatisConfiguration;
import com.dwi.basic.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author dwi
 * @date 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class GatewayMybatisAutoConfiguration extends BaseMybatisConfiguration {

    public GatewayMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }


}
