package com.dwi.saas.tenant.biz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.tenant.biz.dao.DatasourceConfigMapper;
import com.dwi.saas.tenant.biz.service.DatasourceConfigService;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author dwi
 * @date 2020-08-21
 */
@Slf4j
@Service
@DS("master")
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigService {

    //private final DataSourceService dataSourceService;
	
//    private final DataSourceCreator druidDataSourceCreator;
//    private final DynamicDataSourceProperties properties;
//
    @Override
    public Boolean testConnection(DataSourceProperty dataSourceProperty) {
    	return true;
        //return dataSourceService.testConnection(dataSourceProperty);
    }
//    
//    private boolean getTestConnection(DataSourceProperty dataSourceProperty) {
//        dataSourceProperty.setSeata(false);
//        dataSourceProperty.setDruid(BeanUtil.toBean(properties.getDruid(), DruidConfig.class));
//        // 配置获取链接等待超时的时间
//        dataSourceProperty.getDruid().setMaxWait(3000);
//        // 配置初始化大小、最小、最大
//        dataSourceProperty.getDruid().setInitialSize(1);
//        dataSourceProperty.getDruid().setMinIdle(1);
//        dataSourceProperty.getDruid().setMaxActive(1);
//        // 链接错误重试次数
//        dataSourceProperty.getDruid().setConnectionErrorRetryAttempts(0);
//        // 获取失败后中断
//        dataSourceProperty.getDruid().setBreakAfterAcquireFailure(true);
//
//        DataSource testDataSource = null;
//        Connection connection = null;
//        boolean flag;
//        try {
//            testDataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
//            connection = testDataSource.getConnection();
//            int timeOut = 5;
//            if (connection == null || connection.isClosed() || !connection.isValid(timeOut)) {
//                log.info("链接已关闭或无效，请重试获取链接！");
//                connection = testDataSource.getConnection();
//            }
//            flag = connection != null;
//        } catch (ErrorCreateDataSourceException e) {
//            log.error("数据源初始化期间出现异常", e);
//            flag = false;
//            //throw new BizException("数据源初始化期间出现异常", e);
//        } catch (Exception e) {
//            log.error("创建测试链接错误 {}", dataSourceProperty.getUrl());
//            flag = false;
//            //throw new BizException("创建测试链接错误 " + dataSourceProperty.getUrl(), e);
//        } finally {
//            if (testDataSource instanceof ItemDataSource) {
//                ((ItemDataSource) testDataSource).close();
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    log.warn("关闭测试数据源链接异常", e);
//                }
//            }
//        }
//        return flag;
//    }

}
