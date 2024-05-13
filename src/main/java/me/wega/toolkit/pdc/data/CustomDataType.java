package me.wega.toolkit.pdc.data;

public class CustomDataType {
    public static final ComponentDataType COMPONENT = new ComponentDataType();

    public static <T extends Enum<T>> EnumDataType<T> enumDataType(Class<T> enumClass) {
        return new EnumDataType<>(enumClass);
    }
}
