//package com.dwi.saas.authority.hystrix;
//
//import org.springframework.stereotype.Component;
//
//import com.dwi.basic.base.R;
//import com.dwi.saas.authority.OnlineApi;
//import com.dwi.saas.authority.domain.dto.auth.Online;
//
///**
// * 用户API熔断
// *
// * @author dwi
// * @date 2020/12/16
// */
//@Component
//public class OnlineApiFallback implements OnlineApi {
//
//	@Override
//	public R<Boolean> saveOnlineInfo(Online model) {
//		return R.timeout();
//	}
//}
