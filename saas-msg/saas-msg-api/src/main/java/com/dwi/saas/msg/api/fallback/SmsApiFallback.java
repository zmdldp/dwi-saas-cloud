package com.dwi.saas.msg.api.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.msg.api.SmsApi;
import com.dwi.saas.msg.api.domain.SmsSendTaskDTO;
import com.dwi.saas.msg.api.domain.SmsTask;
import com.dwi.saas.msg.api.domain.VerificationCodeDTO;

import org.springframework.stereotype.Component;

/**
 * 熔断
 *
 * @author dwi
 * @date 2020/07/25
 */
@Component
public class SmsApiFallback implements SmsApi {
    @Override
    public R<SmsTask> send(SmsSendTaskDTO smsTaskDTO) {
        return R.timeout();
    }

    @Override
    public R<Boolean> sendCode(VerificationCodeDTO data) {
        return R.timeout();
    }

    @Override
    public R<Boolean> verification(VerificationCodeDTO data) {
        return R.timeout();
    }
}
