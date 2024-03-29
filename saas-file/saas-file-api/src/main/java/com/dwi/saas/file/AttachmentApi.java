package com.dwi.saas.file;


import com.dwi.basic.base.R;
import com.dwi.saas.file.domain.dto.AttachmentDTO;
import com.dwi.saas.file.fallback.AttachmentApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 *
 * @author dwi
 * @date 2020/06/21
 */
@FeignClient(name = "${saas.feign.file-server:saas-file-server}", path = "/attachment", fallback = AttachmentApiFallback.class)
public interface AttachmentApi {

    /**
     * 通过feign-form 实现文件 跨服务上传
     *
     * @param file     文件
     * @param id       文件id
     * @param bizId    业务id
     * @param bizType  业务类型
     * @param isSingle 是否单个文件
     * @return 文件信息
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R<AttachmentDTO> upload(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType);

}
