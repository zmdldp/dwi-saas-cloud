package com.dwi.saas.authority;


import com.dwi.basic.base.R;
import com.dwi.basic.log.entity.OptLogDTO;
import com.dwi.saas.authority.domain.entity.common.OptLog;
import com.dwi.saas.authority.hystrix.LogApiHystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志保存 API
 *
 * @author dwi
 * @date 2019/07/02
 */
@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}",
		path = "/optLog", fallback = LogApiHystrix.class, qualifier = "logApi")
public interface LogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @PostMapping
    R<OptLog> save(@RequestBody OptLogDTO log);

}
