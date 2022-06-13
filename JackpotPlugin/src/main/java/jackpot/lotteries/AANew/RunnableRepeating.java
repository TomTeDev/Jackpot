package jackpot.lotteries.AANew;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class RunnableRepeating implements Runnable {

    private int taskId;

    public RunnableRepeating(JavaPlugin plugin, int cd, int loopTimeTicks) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, cd, loopTimeTicks);
    }

    public void canncel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
    public int getTaskId(){
        return this.taskId;
    }

}