package com.dwi.saas.authority.api;

import com.dwi.saas.authority.api.domain.Application;
import com.dwi.saas.authority.api.domain.User;
import com.dwi.saas.authority.api.hystrix.ApplicationBizApiFallback;
import com.dwi.saas.authority.api.hystrix.ParameterBizApiFallback;

import io.swagger.annotations.ApiOperation;

import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = ParameterBizApiFallback.class
        , path = "/parameter", qualifier = "parameterBizApi")
public interface ParameterBizApi {

    /**
     * 根据key查询参数值
     *
     */
    @GetMapping("/getValue")
    public R<String> getValue(@RequestParam("key") String key, @RequestParam("defVal") String defVal); 

}
