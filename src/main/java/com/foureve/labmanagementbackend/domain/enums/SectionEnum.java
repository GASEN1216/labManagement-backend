package com.foureve.labmanagementbackend.domain.enums;

import lombok.Getter;

@Getter
public enum SectionEnum {

    FIRST_SECTION(1,"1-2", "第一节课"),
    SECOND_SECTION(2,"3-5", "第二节课"),
    THIRD_SECTION(3,"6-7", "第三节课"),
    FOURTH_SECTION(4,"8-9", "第四节课"),
    FIFTH_SECTION(5,"10-12", "第五节课"),
    SIXTH_SECTION(6,"13-15", "第六节课");

    private Integer code;
    private String section;

    private String desc;

    SectionEnum(Integer code,String section, String desc) {
        this.code = code;
        this.section = section;
        this.desc = desc;
    }

    public static Integer getCodeBySection(String section) {
        for (SectionEnum sectionEnum : SectionEnum.values()) {
            if (sectionEnum.getSection().equals(section)) {
                return sectionEnum.getCode();
            }
        }
        return null;
    }

    public static String getSectionByCode(Integer code) {
        for (SectionEnum sectionEnum : SectionEnum.values()) {
            if (sectionEnum.getCode().equals(code)) {
                return sectionEnum.getSection();
            }
        }
        return null;
    }
}
