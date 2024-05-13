package me.wega.toolkit.task;

import me.wega.toolkit.config.impl.ConfigValue;
import me.wega.toolkit.utils.ColorUtils;
import me.wega.toolkit.utils.SchedulerUtils;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class SecCountdownTask extends PlayerTask {
    private final int startSeconds;
    private int currentSeconds;
    private final int[] notifySeconds;

    public SecCountdownTask(@NotNull Player player, int startSeconds, int... notifySeconds) {
        super(player);
        this.startSeconds = startSeconds;
        this.notifySeconds = notifySeconds;
    }

    @Override
    protected @Nullable BukkitTask runInternal() {
        this.currentSeconds = this.startSeconds;
        if (notifySeconds.length == 0) return null;
        return SchedulerUtils.runTaskTimer(() -> {
            if (this.currentSeconds == 0) {
                if (this.getOnComplete() != null) this.getOnComplete().run();
                this.stop();
                return;
            }

            for (int notifySecond : this.notifySeconds) {
                if (this.currentSeconds == notifySecond) {
                    me.wega.toolkit.WegaToolkit.adventure.player(this.getPlayer()).sendMessage(ColorUtils.color(
                            ConfigValue.Tasks.SEC_COUNTDOWN.MSG.getValue(),
                            Placeholder.parsed("seconds", String.valueOf(this.currentSeconds))
                    ));
                }
            }

            this.currentSeconds--;
        }, 0, 20);
    }

    @Override
    protected void stopInternal() {

    }
}
