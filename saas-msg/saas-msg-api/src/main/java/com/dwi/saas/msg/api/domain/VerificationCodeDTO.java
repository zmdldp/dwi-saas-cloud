package com.dwi.saas.msg.api.domain;

import com.dwi.basic.base.entity.SuperEntity;
import com.dwi.saas.msg.api.domain.enumeration.VerificationCodeType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 验证码发送验证DTO
 *
 * @author dwi
 * @date 2020/08/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VerificationCodeDTO", description = "验证码发送验证DTO")
public class VerificationCodeDTO implements Serializable {
	
    private String mobile;
   
    private VerificationCodeType type;

    private String code;
}
