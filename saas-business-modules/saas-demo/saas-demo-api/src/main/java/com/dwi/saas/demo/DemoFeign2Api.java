package com.dwi.saas.demo;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.domain.dto.RestTestDTO;
import com.dwi.saas.demo.fallback.DemoFeign2ApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试日期类型API接口
 *
 * @author dwi
 * @date 2020/07/24
 */
@FeignClient(name = "${saas.feign.demo-server:saas-demo-server}", path = "/restTemplate", fallback = DemoFeign2ApiFallback.class)
public interface DemoFeign2Api {

    @PostMapping("/fallback")
    R<RestTestDTO> fallback(@RequestParam("key") String key);

}
