package com.dwi.saas.tenant.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.tenant.TenantApi;
import com.dwi.saas.tenant.domain.entity.Tenant;

import org.springframework.stereotype.Component;

/**
 * 熔断
 *
 * @author dwi
 * @date 2020/12/17
 */

@Component
public class TenantApiFallback implements TenantApi {
    

	@Override
	public R<Tenant> getByCode(String tenantCode) {
		return R.timeout();
	}
}
