package me.wega.toolkit.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
public abstract class PlayerTask extends Task {
    private final @NotNull Player player;
}
