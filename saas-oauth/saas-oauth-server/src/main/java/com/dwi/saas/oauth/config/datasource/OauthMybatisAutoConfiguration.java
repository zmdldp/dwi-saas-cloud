package com.dwi.saas.oauth.config.datasource;


//import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.dwi.basic.base.R;
import com.dwi.basic.database.datasource.BaseMybatisConfiguration;
import com.dwi.basic.database.mybatis.auth.DataScopeInnerInterceptor;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.authority.api.UserBizApi;
//import com.dwi.saas.authority.biz.service.auth.UserService;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 配置一些拦截器
 *
 * @author dwi
 * @date 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class OauthMybatisAutoConfiguration extends BaseMybatisConfiguration {

	private final UserBizApi userBizApi;

    public OauthMybatisAutoConfiguration(DatabaseProperties databaseProperties, UserBizApi userBizApi) {
        super(databaseProperties);
        this.userBizApi = userBizApi;
    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(userId -> {
            	Map<String, Object> data = userBizApi.getDataScopeById(userId);
            	return data;      	
            }));
          }
        return list;
    }

}
