package com.dwi.saas.msg.api.domain;


import com.dwi.basic.base.entity.Entity;
import com.dwi.saas.msg.api.domain.enumeration.SourceType;
import com.dwi.saas.msg.api.domain.enumeration.TaskStatus;

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

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.dwi.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * <p>
 * 实体类
 * 发送任务
 * </p>
 *
 * @author dwi
 * @since 2020-11-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SmsTask", description = "发送任务")
@AllArgsConstructor
public class SmsTask extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 模板ID
     * #e_sms_template
     */
    private Long templateId;

    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    private TaskStatus status;

    /**
     * 来源类型
     * #SourceType{APP:应用;SERVICE:服务}
     */
    private SourceType sourceType;

    /**
     * 接收者手机号
     * 群发用英文逗号分割.
     * 支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>
     */
    private String receiver;

    /**
     * 主题
     */
    private String topic;

    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    private String templateParams;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送内容
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    private String content;

    /**
     * 是否草稿
     */
    private Boolean draft;


    @Builder
    public SmsTask(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                   Long templateId, TaskStatus status, SourceType sourceType, String receiver, String topic,
                   String templateParams, LocalDateTime sendTime, String content, Boolean draft) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.templateId = templateId;
        this.status = status;
        this.sourceType = sourceType;
        this.receiver = receiver;
        this.topic = topic;
        this.templateParams = templateParams;
        this.sendTime = sendTime;
        this.content = content;
        this.draft = draft;
    }

}
