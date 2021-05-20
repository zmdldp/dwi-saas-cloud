package com.dwi.saas.demo.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.DemoFeign2Api;
import com.dwi.saas.demo.domain.dto.RestTestDTO;

import org.springframework.stereotype.Component;

/**
 * @author dwi
 * @date 2020/6/10 下午10:46
 */
@Component
public class DemoFeign2ApiFallback implements DemoFeign2Api {
    @Override
    public R<RestTestDTO> fallback(String key) {
        return R.timeout();
    }
}
