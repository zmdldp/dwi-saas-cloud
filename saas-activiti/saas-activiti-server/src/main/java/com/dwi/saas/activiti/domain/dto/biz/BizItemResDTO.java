package com.dwi.saas.activiti.domain.dto.biz;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dwi.saas.activiti.domain.entity.biz.BizItem;
import com.dwi.saas.authority.api.domain.User;
//import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.basic.base.entity.Entity;
import com.dwi.saas.common.constant.InjectionFieldConstants;
import com.dwi.basic.annotation.injection.InjectionField;
import com.dwi.basic.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author wz
 * @since 2020-08-19
 */
@Data
public class BizItemResDTO extends BizItem{

    /**
     * 实体项公共信息-用户
     */
    private User cUser;
}
