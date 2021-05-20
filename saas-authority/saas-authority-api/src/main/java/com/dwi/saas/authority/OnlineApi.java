//package com.dwi.saas.authority;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.dwi.basic.base.R;
//import com.dwi.saas.authority.domain.dto.auth.Online;
//import com.dwi.saas.authority.hystrix.OnlineApiFallback;
//
///**
// * 用户
// *
// * @author dwi
// * @date 2020/12/16
// */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = OnlineApiFallback.class
//        , path = "/online", qualifier = "onlineBizApi")
//public interface OnlineApi {
//
//    /**
//     * 保存在线用户信息 ADD 2020-12-16
//     * 
//     * @param model
//     * @return
//     */
//    @PutMapping()
//    public R<Boolean> saveOnlineInfo(@RequestBody Online online);
//}
