package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BooleanUtils {

    public static String booleanToYesNo(boolean bool) {
        return bool ? "yes" : "no";
    }
}
