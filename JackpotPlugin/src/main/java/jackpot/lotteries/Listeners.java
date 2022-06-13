package jackpot.lotteries;

import jackpot.lotteries.AANew.Placeholders;
import org.bukkit.Bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class Listeners implements Listener {
    private final Placeholders ph = new Placeholders();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        String difMessage = ph.replacePlaceHolders(message,e.getPlayer());
        if(message.equalsIgnoreCase(difMessage))return;
        e.setCancelled(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Lotteries.getPlugin(Lotteries.class), new Runnable() {
            @Override
            public void run() {
                e.getPlayer().chat(difMessage);
            }
        }, 0);
    }

    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent e) {
        String message = e.getMessage();
        String difMessage = ph.replacePlaceHolders(message,e.getPlayer());
        if(message.equalsIgnoreCase(difMessage))return;
        e.setCancelled(true);
        String finalMessage = difMessage.replaceFirst("/", "");
        Bukkit.getServer().dispatchCommand(e.getPlayer(), finalMessage);
    }

}
