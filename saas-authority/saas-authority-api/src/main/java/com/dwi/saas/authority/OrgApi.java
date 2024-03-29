package com.dwi.saas.authority;


//import com.dwi.saas.oauth.api.hystrix.OrgApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.saas.authority.hystrix.OrgApiFallback;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 岗位API
 *
 * @author dwi
 * @date 2020/08/02
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "/org",
        qualifier = "orgApi", fallback = OrgApiFallback.class)
public interface OrgApi {

    /**
     * 根据 id 查询组织，并转换成Map结构
     *
     * @param ids id
     * @return id-org
     */
    @GetMapping("/findOrgByIds")
    Map<Serializable, Object> findOrgByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据 id 查询组织名称，并转换成Map结构
     *
     * @param ids id
     * @return id-name
     */
    @GetMapping("/findOrgNameByIds")
    Map<Serializable, Object> findOrgNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);

}
