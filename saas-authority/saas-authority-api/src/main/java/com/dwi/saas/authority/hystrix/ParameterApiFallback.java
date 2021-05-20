package com.dwi.saas.authority.hystrix;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.ParameterApi;
import org.springframework.stereotype.Component;

/**
 * 用户API熔断
 *
 * @author dwi
 * @date 2020/12/16
 */
@Component
public class ParameterApiFallback implements ParameterApi {


	@Override
	public R<String> getValue(String key, String defVal) {
		return R.timeout();
	}
}
