package com.dwi.saas.example.controller.cloud;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.DemoFeign2Api;
import com.dwi.saas.demo.DemoFeign3Api;
import com.dwi.saas.demo.DemoFeignApi;
import com.dwi.saas.demo.domain.dto.RestTestDTO;
import com.dwi.saas.demo.domain.entity.Order;

import cn.hutool.core.io.IoUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2. feignClient 日志
 * 3. feignClient 传递日期参数
 * 4. feignClient 传递文件
 * 5. feignClient 传递请求头和线程变量
 * 6. feignClient & restTemplate 负载均衡
 *
 * @author dwi
 * @date 2020/6/8 下午11:01
 */
@Slf4j
@RestController
@RequestMapping("/feign")
@Api(value = "feign", tags = "feign演示")
@RequiredArgsConstructor
public class FeignController {
    private final DemoFeignApi demoFeignApi;
    private final DemoFeign2Api demoFeign2Api;
    private final DemoFeign3Api demoFeign3Api;

    /**
     * @param file     文件
     * @param fileName 文件名
     * @return MultipartFile 文件包装类
     * @method
     * @description 二进制 流转换为 文件包装类
     * @date: 2020/6/24 0024 11:18
     * @author: mdengb
     */
    @SneakyThrows
    public static MultipartFile getMulFileByFile(File file, String fileName) {
        InputStream fis = new FileInputStream(file);
        FileItem fileItem = createFileItem(fis, fileName);
        return new CommonsMultipartFile(fileItem);
    }

    public static FileItem createFileItem(InputStream fis, String fileName) {

        String fieldName = "file";
        /**
         * 设置存储临界值 当文件大小大于 50kb 时，将创建临时文件 (默认 10kb)
         * repository：临时文件存储位置
         * */
        int sizeThreshold = 50 * 1024;
        FileItemFactory factory = new DiskFileItemFactory(sizeThreshold, null);
        FileItem item = factory.createItem(fieldName, "text/plain", false, fileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];

        try (OutputStream os = item.getOutputStream()) {
            int length = 8192;
            while ((bytesRead = fis.read(buffer, 0, length)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            log.error("createFileItem error", e);
        } finally {
            IoUtil.close(fis);
        }
        return item;
    }

    @GetMapping("/get")
    public R<Order> get(@RequestParam("key") String key) {
        return demoFeignApi.get(key, new Date(), LocalDateTime.now());
    }

    @PostMapping("/post")
    public R<RestTestDTO> post(@RequestParam("key") String key) {
        return demoFeignApi.post(key);
    }

    @PostMapping("/post2")
    public R<RestTestDTO> post2(@RequestBody RestTestDTO order) {
        return demoFeignApi.post2(order);
    }

    @PostMapping(value = "/upload")
    public R<String> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {
        return demoFeignApi.upload(file, isSingle, id, bizId, bizType);
    }

    @PostMapping(value = "/upload2")
    public R<String> upload2(
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {

        File file = new File("/Users/dwi/Downloads/uploadFiles/2.jpg");


        MultipartFile multipartFile = getMulFileByFile(file, "测试本地文件.jpg");
        return demoFeignApi.upload(multipartFile, isSingle, id, bizId, bizType);
    }


    @PostMapping("/fallback")
    public R<RestTestDTO> fallback(@RequestParam("key") String key) {
        return demoFeignApi.fallback(key);
    }

    @PostMapping("/fallback2")
    public RestTestDTO fallback2(@RequestParam("key") String key) {
        return demoFeignApi.fallback2(key);
    }

    @PostMapping("/fallback3")
    public R<RestTestDTO> fallback3(@RequestParam("key") String key) {
        return demoFeign2Api.fallback(key);
    }

    @PostMapping("/fallback4")
    public R<RestTestDTO> fallback4(@RequestParam("key") String key) {
        return demoFeign3Api.fallback(key);
    }

    @PostMapping("/fallback5")
    public RestTestDTO fallback5(@RequestParam("key") String key) {
        return demoFeign3Api.fallback2(key);
    }

}
