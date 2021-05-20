package com.dwi.saas.authority;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.hystrix.ParameterApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = ParameterApiFallback.class
        , path = "/parameter", qualifier = "parameterBizApi")
public interface ParameterApi {

    /**
     * 根据key查询参数值
     *
     */
    @GetMapping("/getValue")
    public R<String> getValue(@RequestParam("key") String key, @RequestParam("defVal") String defVal); 

}
