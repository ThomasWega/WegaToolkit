package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class NumberUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Number> T roundBy(@NotNull T toRound, int roundNum) {
        double result = Math.round(toRound.doubleValue() * Math.pow(10, roundNum)) / Math.pow(10, roundNum);

        if (toRound instanceof Float) {
            return (T) Float.valueOf((float) result);
        } else if (toRound instanceof Double) {
            return (T) Double.valueOf(result);
        } else if (toRound instanceof Integer) {
            return (T) Integer.valueOf((int) result);
        }
        // Add more cases for other number types if needed

        throw new IllegalArgumentException("Unsupported number type");
    }
}
