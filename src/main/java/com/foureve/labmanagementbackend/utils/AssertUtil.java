package com.foureve.labmanagementbackend.utils;

import cn.hutool.core.util.ObjectUtil;
import com.foureve.labmanagementbackend.domain.enums.BusinessErrorEnum;
import com.foureve.labmanagementbackend.domain.enums.ErrorEnum;
import com.foureve.labmanagementbackend.exception.BusinessException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.MessageFormat;
import java.util.*;

/**
 * 工具类
 * @author GASEN
 * @date 2024/4/25 14:59
 * @classType description
 */
public class AssertUtil {

    /**
     * 校验到失败就结束
     */
    private static Validator failFastValidator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory().getValidator();

    /**
     * 全部校验
     */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();





    //如果不是true，则抛异常
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throwException(msg);
        }
    }

    public static void isTrue(boolean expression, ErrorEnum errorEnum, String msg) {
        if (!expression) {
            throwException(errorEnum, msg);
        }
    }

    //如果是true，则抛异常
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throwException(msg);
        }
    }

    //如果是true，则抛异常
    public static void isFalse(boolean expression, ErrorEnum errorEnum, String msg) {
        if (expression) {
            throwException(errorEnum, msg);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, String msg) {
        if (isEmpty(obj)) {
            throw new BusinessException(ErrorEnum.SYSTEM_ERROR.getCode(), msg);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, ErrorEnum errorEnum, String msg) {
        if (isEmpty(obj)) {
            throwException(errorEnum, msg);
        }
    }

    public static void isNotEmpty(Object obj,Object obj2 , String msg) {
        if (isEmpty(obj) && isEmpty(obj2)) {
            throwException(msg);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isEmpty(Object obj, String msg) {
        if (!isEmpty(obj)) {
            throwException(msg);
        }
    }

    /**
     * 不相等抛出异常
     * @param o1
     * @param o2
     * @param msg
     */
    public static void equal(Object o1, Object o2, String msg) {
        if (!ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    public static void notEqual(Object o1, Object o2, String msg) {
        if (ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    private static boolean isEmpty(Object obj) {
        return ObjectUtil.isEmpty(obj);
    }



    private static void throwException(String msg) {
        throwException(null, msg);
    }

    private static void throwException(ErrorEnum errorEnum, String msg) {
        if (Objects.isNull(errorEnum)) {
            errorEnum = ErrorEnum.SYSTEM_ERROR;
        }
        throw new BusinessException(errorEnum.getCode(), msg);
    }
}
