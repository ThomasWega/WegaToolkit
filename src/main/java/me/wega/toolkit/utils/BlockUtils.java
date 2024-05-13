package me.wega.toolkit.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class BlockUtils {

    public static Set<Block> getConnected(@NotNull final Block index, @NotNull Predicate<Block> filter) {
        return Arrays.stream(BlockFace.values())
                .map(index::getRelative)
                .filter(filter)
                .collect(Collectors.toSet());
    }
}