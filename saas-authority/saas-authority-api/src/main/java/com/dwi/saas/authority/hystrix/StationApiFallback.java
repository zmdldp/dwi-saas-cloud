package com.dwi.saas.authority.hystrix;


//import com.dwi.saas.oauth.api.StationApi;
import org.springframework.stereotype.Component;

import com.dwi.saas.authority.StationApi;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 熔断类
 *
 * @author dwi
 * @date 2020年02月09日11:24:23
 */
@Component
public class StationApiFallback implements StationApi {
    @Override
    public Map<Serializable, Object> findStationByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }

    @Override
    public Map<Serializable, Object> findStationNameByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }
}
