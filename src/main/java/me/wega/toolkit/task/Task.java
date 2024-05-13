package me.wega.toolkit.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Task {
    private @Nullable BukkitTask task;
    private @Nullable Runnable onComplete;
    protected abstract @Nullable BukkitTask runInternal();
    protected abstract void stopInternal();

    public void run() {
        this.task = runInternal();
    }

    public void stop() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
        this.stopInternal();
    }

    public boolean isRunning() {
        return this.task != null;
    }
}
