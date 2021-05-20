package com.dwi.saas.authority.hystrix;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.authority.domain.entity.auth.User;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 用户API熔断
 *
 * @author dwi
 * @date 2020/07/23
 */
@Component
public class UserApiFallback implements UserApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }

    @Override
    public R<List<User>> findUserById(List<Long> ids) {
        return R.timeout();
    }

	@Override
	public R<User> getByAccount(String account) {
		return R.timeout();
	}

	@Override
	public R<User> getByIdCache(Long userId) {
		return R.timeout();
	}

	
	@Override 
	public Map<String, Object> getDataScopeById(Long userId) {
		return Collections.emptyMap(); 
	}

	@Override
	public R<Boolean> resetPassErrorNum(Long userId) {
		return R.timeout();
	}

	@Override
	public R<Boolean> incrPasswordErrorNumById(Long userId) {
		return R.timeout();
	}
	 
}
