package com.dwi.saas.authority.domain.dto.common;

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
import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author dwi
 * @since 2020-02-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AreaPageQuery", description = "地区表")
public class AreaPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    private String label;
    @ApiModelProperty(value = "父ID")
    private Long parentId;
    @ApiModelProperty(value = "排序号")
    private Integer sortValue;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    private String code;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Size(max = 255, message = "全名长度不能超过255")
    private String fullName;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Size(max = 255, message = "经度长度不能超过255")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Size(max = 255, message = "维度长度不能超过255")
    private String latitude;
    /**
     * 行政区级
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "行政区级")
    @Size(max = 10, message = "行政区级长度不能超过10")
    private RemoteData<String, String> level;
    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Size(max = 255, message = "数据来源长度不能超过255")
    private String source;
}
