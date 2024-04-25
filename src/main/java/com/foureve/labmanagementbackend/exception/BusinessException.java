package com.foureve.labmanagementbackend.exception;


import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;

/**
 * 自定义异常类
 * */
public class BusinessException extends RuntimeException{
        private final int code;

        private final String description;

        public BusinessException(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public BusinessException(ErrorEnum errorEnum) {
            super(errorEnum.getMsg());
            this.code = errorEnum.getCode();
            this.description = errorEnum.getMsg();
        }

        public BusinessException(ErrorEnum errorCode, String description) {
            super(description);
            this.code = errorCode.getCode();
            this.description = description;
        }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
