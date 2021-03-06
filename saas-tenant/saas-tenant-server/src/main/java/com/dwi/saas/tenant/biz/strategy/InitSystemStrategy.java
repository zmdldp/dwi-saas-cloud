package com.dwi.saas.tenant.biz.strategy;

import java.util.List;

import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;

/**
 * 初始化系统
 * <p>
 *
 * @author dwi
 * @date 2019/10/25
 */
public interface InitSystemStrategy {

    /**
     * 初始化 服务链接
     *
     * @param tenantConnect 链接信息
     * @return 是否成功
     */
    boolean initConnect(TenantConnectDTO tenantConnect);

    /**
     * 重置系统
     *
     * @param tenant 租户编码
     * @return 是否成功
     */
    boolean reset(String tenant);

    /**
     * 删除租户数据
     *
     * @param ids            租户id
     * @param tenantCodeList 租户编码
     * @return 是否成功
     */
    boolean delete(List<Long> ids, List<String> tenantCodeList);
}
