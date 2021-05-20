package com.dwi.saas.tenant.rc.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.dwi.basic.base.R;
import com.dwi.basic.database.properties.MultiTenantType;
import com.dwi.basic.utils.StrPool;
import com.dwi.saas.common.constant.BizConstant;
import com.dwi.saas.tenant.domain.vo.ServerServiceVO;
import com.dwi.saas.tenant.rc.ServerService;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.dwi.basic.utils.CommonConstants;

/**
 * 注册中心服务查询
 * 
 * @author admin
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ServerServiceImpl implements ServerService{
	
	private final DiscoveryClient discoveryClient;
	
	@Resource(name = "lbRestTemplate")
	private RestTemplate lbRestTemplate;
	
	/**
	 * 获取服务信息列表
	 * 
	 * @return
	 */
	@Override
	public List<ServerServiceVO> list() {
		return getServices().stream().map(s -> {
			Map<String, String>  envMap = getApplicationEnvironment(s);
			if(CollUtil.isNotEmpty(envMap)) {
				return ServerServiceVO.builder().serviceName(s)
						.multiTenantType(MultiTenantType.get(envMap.get(CommonConstants.MULTI_TENANT_TYPE_VAR)))
						.build();
			}else {
				return null;
			}
		}).collect(Collectors.toList());
		
	}
	
	/**
	 * 获取当前租户服务同源的所有其它服务名称列表
	 * 
	 * @return
	 */
	@Override
	public List<String> getServices(){
		return discoveryClient.getServices().stream()
				.filter(s -> !CommonConstants.TENANT_SERVER.equals(s))
				.collect(Collectors.toList());
	}

	
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getApplicationEnvironment(String serviceName){
		
		ServiceInstance instance = getInstances(serviceName).get(0);
		String contextPath = instance.getMetadata().get(CommonConstants.SERVER_METADATA_CONTEXT_PATH_NAME);
		String url = new StringBuilder("http://").append(serviceName).append(contextPath==null?"":contextPath)
						.append(CommonConstants.APPLICATION_ENVIRONMENT_URI).toString();
		R<Map<String, String>> r = lbRestTemplate.getForObject(url, R.class);
		if(r!=null && r.isSuccess()) {
			return r.getData();
		}else {
			return null;
		}
		
	}
	
	/**
	 * 根据服务名称和操作数据源方法获取请求映射列表
	 * 
	 * @param service
	 * @param method
	 * @return
	 */
	@Override
	public List<String> getDsReqUrls(String serviceName, String method){
		return getInstances(serviceName).stream().map(i -> {
			String contextPath = i.getMetadata().get(CommonConstants.SERVER_METADATA_CONTEXT_PATH_NAME);
			if(CommonConstants.INIT_DS_PARAM_METHOD_INIT.equals(method)) {		
				return i.getUri().toString().concat(StrPool.SLASH)
						.concat(StringUtils.isEmpty(contextPath)? "":contextPath)
						.concat(CommonConstants.INIT_DS_REQUESTMAPPING_INIT);
			}else if(CommonConstants.INIT_DS_PARAM_METHOD_REMOVE.equals(method)) {
				return i.getUri().toString().concat(StrPool.SLASH)
						.concat(StringUtils.isEmpty(contextPath)? "":contextPath)
						.concat(CommonConstants.INIT_DS_REQUESTMAPPING_REMOVE);
			}else {
				log.debug("操作数据源的方法:{}不存在!", method);
				return null;
			}
		}).collect(Collectors.toList());
	}
	
	/**
	 * 根据服务名称获取服务实例列表
	 * @param service
	 * @return
	 */
	public List<ServiceInstance> getInstances(String serviceName){
		return discoveryClient.getInstances(serviceName);
		
	}


}
