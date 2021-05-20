package com.dwi.saas.authority;


//import com.dwi.saas.oauth.api.hystrix.StationApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.saas.authority.hystrix.StationApiFallback;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 岗位API
 *
 * @author dwi
 * @date 2020/08/02
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "/station",
        qualifier = "stationApi", fallback = StationApiFallback.class)
public interface StationApi {

    /**
     * 根据id查询 岗位
     *
     * @param ids id
     * @return id-station
     */
    @GetMapping("/findStationByIds")
    Map<Serializable, Object> findStationByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询 岗位名称
     *
     * @param ids id
     * @return id-name
     */
    @GetMapping("/findStationNameByIds")
    Map<Serializable, Object> findStationNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}
