package com.dwi.saas.msg.api;


import com.dwi.basic.base.R;
import com.dwi.basic.base.entity.SuperEntity;
import com.dwi.saas.msg.api.domain.SmsSendTaskDTO;
import com.dwi.saas.msg.api.domain.SmsTask;
import com.dwi.saas.msg.api.domain.VerificationCodeDTO;
import com.dwi.saas.msg.api.fallback.SmsApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件接口
 *
 * @author dwi
 * @date 2020/06/21
 */
@FeignClient(name = "${saas.feign.msg-server:saas-msg-server}", fallback = SmsApiFallback.class)
public interface SmsApi {
    /**
     * 短信发送
     *
     * @param smsTaskDTO 短信参数
     * @return 短信任务
     */
    @RequestMapping(value = "/smsTask/send", method = RequestMethod.POST)
    R<SmsTask> send(@RequestBody SmsSendTaskDTO smsTaskDTO);

    /**
     * 发送验证码
     *
     * @param data 验证码参数
     * @return 是否执行发送
     */
    @PostMapping(value = "/verification/send")
    R<Boolean> sendCode(@Validated @RequestBody VerificationCodeDTO data);

    /**
     * 验证
     *
     * @param data 验证码参数
     * @return 是否验证成功
     */
    @PostMapping("/verification")
    R<Boolean> verification(@Validated(SuperEntity.Update.class) @RequestBody VerificationCodeDTO data);
}
