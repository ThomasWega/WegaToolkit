package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.math.Range;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@UtilityClass
public class RandomRange {

    private static final Random RANDOM = new Random();


    public static int getRandomInt(@NotNull Range range) {
        return RANDOM.nextInt(range.getMinimumInteger(), range.getMaximumInteger());
    }

    public static double getRandomDouble(@NotNull Range range) {
        return RANDOM.nextDouble(range.getMinimumDouble(), range.getMaximumDouble());
    }
}
