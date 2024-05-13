package me.wega.toolkit.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@ApiStatus.Experimental
public class InitializeStatic {

    public static void initializeAll(@NotNull Class<?> clazz) {
        initialize(clazz);

        // Initialize static subclasses
        Class<?>[] subclasses = clazz.getDeclaredClasses();
        for (Class<?> subclass : subclasses) {
            initializeAll(subclass);
        }
    }

    @SneakyThrows
    public static void initialize(@NotNull Class<?> clazz) {
        Class.forName(clazz.getName());
    }
}