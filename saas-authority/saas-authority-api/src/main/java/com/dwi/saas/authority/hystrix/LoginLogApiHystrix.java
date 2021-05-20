package com.dwi.saas.authority.hystrix;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.LoginLogApi;
import com.dwi.saas.authority.domain.entity.common.LoginLog;
import org.springframework.stereotype.Component;

/**
 * 日志操作 熔断
 *
 * @author dwi
 * @date 2020/07/02
 */
@Component
public class LoginLogApiHystrix implements LoginLogApi {

	@Override
	public R<LoginLog> save(LoginLog loginLog) {
	       return R.timeout();
	}

}
