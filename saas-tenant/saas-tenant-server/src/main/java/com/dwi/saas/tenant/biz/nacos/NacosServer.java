package com.dwi.saas.tenant.biz.nacos;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.ListView;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties({NacosDiscoveryProperties.class})
public class NacosServer {
	
    private NacosDiscoveryProperties nacosProperties;
    
    private static final int DEFAULT_PAGENO = 1;
    
    private static final int DEFAULT_PAGESIZE = 100;
	
	public List<String> getAllRuningServices() throws NacosException {
		Properties properties = new Properties();
    	BeanUtil.beanToMap(nacosProperties).forEach((k, v) -> properties.putIfAbsent(k,
				String.valueOf(v)));
    	NamingService namingService = NacosFactory.createNamingService(properties);  	
    	ListView<String> serviceListView = namingService.getServicesOfServer(DEFAULT_PAGENO, DEFAULT_PAGESIZE, nacosProperties.getGroup());
    	int multiple = serviceListView.getCount()/DEFAULT_PAGESIZE;
    	int remainder = serviceListView.getCount()%DEFAULT_PAGESIZE;
    	if( multiple < 1) {
    		return serviceListView.getData();
    	}else {
    		List<String> allServices = new ArrayList<>();
    		allServices.addAll(serviceListView.getData());
    		while(multiple >= 1 && remainder != 0) {
    			ListView<String> serviceListViewTemp = namingService.getServicesOfServer(DEFAULT_PAGENO + 1, DEFAULT_PAGESIZE, nacosProperties.getGroup());
    			allServices.addAll(serviceListViewTemp.getData());
    			multiple--;
    		}
    		return allServices;
    	}
	}


}
