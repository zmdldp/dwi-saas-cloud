package com.dwi.saas.demo;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.domain.dto.RestTestDTO;
import com.dwi.saas.demo.fallback.DemoFeign3ApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试日期类型API接口
 *
 * @author dwi
 * @date 2020/07/24
 */
@FeignClient(name = "${saas.feign.demo-server:saas-demo-server}", path = "/restTemplate", fallbackFactory = DemoFeign3ApiFallback.class)
public interface DemoFeign3Api {

    @PostMapping("/fallback")
    R<RestTestDTO> fallback(@RequestParam("key") String key);

    @PostMapping("/fallback2")
    RestTestDTO fallback2(@RequestParam("key") String key);
}
