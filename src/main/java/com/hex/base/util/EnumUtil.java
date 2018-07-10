package com.hex.base.util;

import com.hex.base.enums.CodeEnum;

/**
 * User: hexuan
 * Date: 2018/7/9
 * Time: 下午1:23
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
