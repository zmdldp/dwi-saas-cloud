package com.dwi.saas.tenant.service.impl;
//package com.dwi.saas.tenant.biz.service.impl;
//
//import com.baomidou.dynamic.datasource.annotation.DS;
//import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
//import com.baomidou.dynamic.datasource.ds.ItemDataSource;
//import com.baomidou.dynamic.datasource.exception.ErrorCreateDataSourceException;
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
//import com.dwi.basic.base.service.SuperServiceImpl;
//import com.dwi.basic.exception.BizException;
//import com.dwi.saas.tenant.biz.dao.DatasourceConfigMapper;
//import com.dwi.saas.tenant.biz.service.DatasourceConfigService;
//import com.dwi.saas.tenant.domain.entity.DatasourceConfig;
//
//import cn.hutool.core.bean.BeanUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.stereotype.Service;
//
///**
// * <p>
// * 业务实现类
// * 数据源
// * </p>
// *
// * @author dwi
// * @date 2020-08-21
// */
//@Slf4j
//@Service
//@DS("master")
//@RequiredArgsConstructor
//public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigService {
//
//	@Override
//    public Boolean testConnection(DataSourceProperty dataSourceProperty) {
//    	DataSource dataSource = DataSourceBuilder.create()
//    		.driverClassName(dataSourceProperty.getDriverClassName())
//    		.username(dataSourceProperty.getUsername())
//    		.password(dataSourceProperty.getPassword())
//    		.url(dataSourceProperty.getUrl())
//    		.build(); 	
//    	 Connection connection = null;
//    	 boolean flag;
//    	try {
//    		connection = dataSource.getConnection();
//            int timeOut = 5;
//            if (connection == null || connection.isClosed() || !connection.isValid(timeOut)) {
//                log.info("链接已关闭或无效，重试获取链接！");
//                connection = dataSource.getConnection();
//            }
//            flag = connection != null;
//		} catch (Exception e) {
//			 log.error("创建测试链接错误 {}", dataSourceProperty.getUrl());
//	         throw new BizException("创建测试链接异常 .", e);
//		} finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    log.warn("关闭测试数据源链接异常", e);
//                }
//            }
//		}
//    	return flag;
//    }
//
//}
