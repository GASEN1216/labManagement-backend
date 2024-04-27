package com.foureve.labmanagementbackend.domain.dtos;

import com.foureve.labmanagementbackend.Holder.RequestHolder;
import com.foureve.labmanagementbackend.dao.SemesterDao;
import com.foureve.labmanagementbackend.domain.entity.ApplyLab;
import com.foureve.labmanagementbackend.domain.enums.ApplyLabStateEnum;
import com.foureve.labmanagementbackend.domain.enums.ApplyLabTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.annotation.Resource;

/**
 * @author GASEN
 * @date 2024/4/25 19:47
 * @classType description
 */
@Data
public class StuApplyLabDto {

    @ApiModelProperty(value = "申请单id")
    private Long id;

    @ApiModelProperty(value = "实验室编号", required = true)
    private Long labNumber;

    @ApiModelProperty(value = "申请原因", required = true)
    private String message;

    @ApiModelProperty(value = "学期", required = true)
    private String semester;


    @ApiModelProperty(value = "大周", required = true)
    private Integer weeks;

    @ApiModelProperty(value = "小周", required = true)
    private Integer week;

    @ApiModelProperty(value = "节次", required = true)
    private String section;

    public ApplyLab toApplyLab() {
        ApplyLab applyLab = new ApplyLab();
        applyLab.setType(ApplyLabTypeEnum.STUDENT.getCode());
        applyLab.setApplicantId(RequestHolder.get().getUserId());
        applyLab.setLabNumber(this.labNumber);
        applyLab.setState(ApplyLabStateEnum.UNAUDITED.getCode());
        applyLab.setMessage(this.message);
        applyLab.setBeginWeeks(this.weeks);
        applyLab.setEndWeeks(this.week);
        applyLab.setSection(this.section);
        return applyLab;
    }
}
