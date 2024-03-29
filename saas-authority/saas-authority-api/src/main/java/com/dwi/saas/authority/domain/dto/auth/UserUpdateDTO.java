package com.dwi.saas.authority.domain.dto.auth;

import com.dwi.basic.base.entity.SuperEntity;
import com.dwi.saas.authority.domain.enumeration.auth.Sex;
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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author dwi
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserUpdateDTO", description = "用户")
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String account;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    private String name;
    /**
     * 组织ID
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS, method = FIND_BY_IDS, beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;
    /**
     * 岗位ID
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS, method = FIND_NAME_BY_IDS)
     */
    @ApiModelProperty(value = "岗位ID")
    private Long stationId;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    private Sex sex;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Size(max = 255, message = "头像长度不能超过255")
    private String avatar;
    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 2, message = "民族长度不能超过2")
    private String nation;
    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 2, message = "学历长度不能超过2")
    private String education;
    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    private String positionStatus;
    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
}
