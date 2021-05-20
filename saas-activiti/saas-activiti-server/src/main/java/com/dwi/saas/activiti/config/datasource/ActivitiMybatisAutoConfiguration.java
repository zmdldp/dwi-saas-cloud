package com.dwi.saas.activiti.config.datasource;


import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.dwi.basic.database.datasource.BaseMybatisConfiguration;
import com.dwi.basic.database.mybatis.auth.DataScopeInnerInterceptor;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.authority.UserApi;

//import com.dwi.saas.oauth.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author dwi
 * @date 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class ActivitiMybatisAutoConfiguration extends BaseMybatisConfiguration {

    public ActivitiMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(userId -> SpringUtils.getBean(UserApi.class).getDataScopeById(userId)));
        }
        return list;
    }
}
