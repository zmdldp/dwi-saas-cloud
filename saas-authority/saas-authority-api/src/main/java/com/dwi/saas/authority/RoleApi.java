package com.dwi.saas.authority;


import com.dwi.basic.base.R;
import com.dwi.saas.authority.hystrix.RoleApiFallback;

//import com.dwi.saas.oauth.api.hystrix.RoleApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色API
 *
 * @author dwi
 * @date 2020/08/02
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "/role", fallback = RoleApiFallback.class)
public interface RoleApi {
    /**
     * 根据角色编码，查找用户id
     *
     * @param codes 角色编码
     * @return 用户id
     */
    @GetMapping("/codes")
    R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes);
}
