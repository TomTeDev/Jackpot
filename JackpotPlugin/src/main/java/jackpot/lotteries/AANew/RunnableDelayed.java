package jackpot.lotteries.AANew;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class RunnableDelayed implements Runnable {

    private int taskId;

    public RunnableDelayed(JavaPlugin plugin, int delay) {
        taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, delay);
    }

    public void canncel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public int getTaskId() {
        return this.taskId;


    }
}