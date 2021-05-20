package com.dwi.saas.msg.api.domain;

import com.alibaba.fastjson.JSONObject;
import com.dwi.saas.msg.api.domain.enumeration.TemplateCodeType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 短信发送任务
 *
 * @author dwi
 * @date 2018/12/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SmsSendTask", description = "短信发送DTO")
public class SmsSendTaskDTO {

    /**
     * 短信模板id #sms_template
     */
    private TemplateCodeType customCode;

    /**
     * 接收者手机号 群发用英文逗号分割.
     * 支持2种格式:
     * 1: 手机号,手机号
     * 2: 姓名<手机号>,姓名<手机号>
     */
    private String receiver;

    /**
     * 短信主题
     */
    private String topic;

    /**
     * 短信模板参数
     * 需要封装为{"key":"value", ...}格式，且key必须有序
     * <p>
     * java代码中请使用 JSONObject.parseObject(xxx, Feature.OrderedField); 进行解析
     * java代码中请使用 JSONObject obj = new JSONObject(true); 来设置有序的key
     *
     */
    private JSONObject templateParam;

    /**
     * 短信发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送内容
     */
    private String context;
    /**
     * 是否为草稿
     * #BooleanStatus
     */
    private Boolean draft;

    /**
     * 最少传递的参数
     *
     * @param customCode    自定义编码
     * @param receiver      接收人
     * @param templateParam 模版参数
     * @return 短信任务
     */
    public SmsSendTaskDTO build(TemplateCodeType customCode, String receiver, JSONObject templateParam) {
        return SmsSendTaskDTO.builder()
                .customCode(customCode)
                .receiver(receiver)
                .templateParam(templateParam)
                .build();
    }

}
