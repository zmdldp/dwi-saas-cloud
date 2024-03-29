package com.dwi.saas.example.controller.cloud;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.TestDateApi;
import com.dwi.saas.demo.domain.dto.DateDTO;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 日期测试类
 *
 * @author dwi
 * @date 2020/07/24
 */
@Slf4j
@RestController
@RequestMapping("/date")
@Api(value = "Date", tags = "时间类型验证器")
@RequiredArgsConstructor
public class DateController {

    private final TestDateApi testDateApi;

    @PostMapping("/post1")
    public R<DateDTO> bodyPos1(@RequestBody DateDTO data) {
        log.info("post1={}", data);
//        return testDateApi.bodyPos1(data);
        return R.success(data);
    }


    /***
     * 调用这个接口会报错
     * @param data
     * @return
     */
    @GetMapping("/get1")
    public R<DateDTO> get1(DateDTO data) {
        log.info("get1={}", data);
        return testDateApi.get(data);
    }

    @GetMapping("/get2")
    public R<DateDTO> get2(@RequestParam(required = false, value = "date") Date date,
                           @RequestParam(required = false, value = "dt") LocalDateTime dt,
                           @RequestParam(required = false, value = "d") LocalDate d,
                           @RequestParam(required = false, value = "t") LocalTime t) {
        return testDateApi.get2(date, dt, d, t);
    }


}
