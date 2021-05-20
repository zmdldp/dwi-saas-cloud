package com.dwi.saas.file.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.file.AttachmentApi;
import com.dwi.saas.file.domain.dto.AttachmentDTO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 熔断
 *
 * @author dwi
 * @date 2020/07/25
 */
@Component
public class AttachmentApiFallback implements AttachmentApi {
    @Override
    public R<AttachmentDTO> upload(MultipartFile file, Boolean isSingle, Long id, String bizId, String bizType) {
        return R.timeout();
    }
}
