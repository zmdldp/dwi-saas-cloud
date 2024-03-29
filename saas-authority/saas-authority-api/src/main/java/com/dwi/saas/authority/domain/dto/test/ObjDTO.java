package com.dwi.saas.authority.domain.dto.test;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 日期测试类 DTO
 *
 * @author dwi
 * @date 2020/07/24
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(value = "ObjDTO", description = "测试戏")
public class ObjDTO implements Serializable {
    private List<String> xx;
    private List<Object> yy;

}
