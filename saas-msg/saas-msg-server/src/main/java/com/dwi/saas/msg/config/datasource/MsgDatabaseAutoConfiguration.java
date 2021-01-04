package com.dwi.saas.msg.config.datasource;


import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.dwi.basic.database.datasource.defaults.BaseMasterDatabaseConfiguration;
import com.dwi.basic.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dwi.saas.common.constant.BizConstant.BUSINESS_PACKAGE;
import static com.dwi.saas.common.constant.BizConstant.UTIL_PACKAGE;

/**
 * saas.database.multiTenantType != DATASOURCE 时，该类启用.
 * 此时，项目的多租户模式切换成：${saas.database.multiTenantType}。
 * <p>
 * NONE("非租户模式"): 不存在租户的概念
 * COLUMN("字段模式"): 在sql中拼接 tenant_code 字段
 * SCHEMA("独立schema模式"): 在sql中拼接 数据库 schema
 * <p>
 * COLUMN和SCHEMA模式的实现 参考下面的 @see 中的3个类
 *
 * @author dwi
 * @date 2017-11-18 0:34
 * 断点查看原理：👇👇👇
 * @see com.dwi.basic.database.datasource.BaseMybatisConfiguration#mybatisPlusInterceptor()
 * @see com.dwi.basic.boot.interceptor.HeaderThreadLocalInterceptor
 */
@Configuration
@Slf4j
@MapperScan(
        basePackages = {UTIL_PACKAGE, BUSINESS_PACKAGE},
        annotationClass = Repository.class,
        sqlSessionFactoryRef = MsgDatabaseAutoConfiguration.DATABASE_PREFIX + "SqlSessionFactory")
@EnableConfigurationProperties({MybatisPlusProperties.class})
@ConditionalOnExpression("!'DATASOURCE'.equals('${saas.database.multiTenantType}')")
public class MsgDatabaseAutoConfiguration extends BaseMasterDatabaseConfiguration {


    public MsgDatabaseAutoConfiguration(MybatisPlusProperties properties,
                                        DatabaseProperties databaseProperties,
                                        ObjectProvider<Interceptor[]> interceptorsProvider,
                                        ObjectProvider<TypeHandler[]> typeHandlersProvider,
                                        ObjectProvider<LanguageDriver[]> languageDriversProvider,
                                        ResourceLoader resourceLoader,
                                        ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                        ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
                                        ObjectProvider<List<MybatisPlusPropertiesCustomizer>> mybatisPlusPropertiesCustomizerProvider,
                                        ApplicationContext applicationContext) {
        super(properties, databaseProperties, interceptorsProvider, typeHandlersProvider,
                languageDriversProvider, resourceLoader, databaseIdProvider,
                configurationCustomizersProvider, mybatisPlusPropertiesCustomizerProvider, applicationContext);
        log.debug("检测到 saas.database.multiTenantType!=DATASOURCE，启用了 AuthorityDatabaseAutoConfiguration");
    }

}
