package com.dwi.saas.authority.hystrix;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.ApplicationApi;
import com.dwi.saas.authority.domain.entity.auth.Application;

import org.springframework.stereotype.Component;

/**
 * 用户API熔断
 *
 * @author dwi
 * @date 2020/12/16
 */
@Component
public class ApplicationApiFallback implements ApplicationApi {

	@Override
	public R<Application> getApplicationByClient(String clientId, String clientSecret) {
		return R.timeout();
	}
}
