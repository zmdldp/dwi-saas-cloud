package com.dwi.saas.tenant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.basic.base.R;
import com.dwi.saas.tenant.domain.entity.Tenant;
import com.dwi.saas.tenant.fallback.TenantApiFallback;

import io.swagger.annotations.ApiOperation;

/**
 * 租户接口
 *
 * @author dwi
 * @date 2020/06/21
 */
@FeignClient(name = "${saas.feign.tenant-server:saas-tenant-server}", path = "/tenant", fallback = TenantApiFallback.class)
public interface TenantApi {

	/**
	 * 根据租户编码查询租户信息 ADD 2020-12-17
	 * 
	 * @param tenantCode
	 * @return
	 */
	@ApiOperation(value = "根据租户编码查询租户信息", notes = "根据租户编码查询租户信息")
	@GetMapping("/getByCode")
	public R<Tenant> getByCode(@RequestParam("tenantCode") String tenantCode);
}
