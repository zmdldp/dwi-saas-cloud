package com.dwi.saas.authority.domain.dto.test;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 日期测试类 DTO
 *
 * @author dwi
 * @date 2020/07/24
 */

@Data
@ToString
public class DateDTO implements Serializable {
    private Date date;
    private LocalDateTime dt;
    private LocalDate d;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime t;
}
