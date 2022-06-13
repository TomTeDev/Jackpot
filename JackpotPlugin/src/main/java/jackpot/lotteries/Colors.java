package jackpot.lotteries;

import net.md_5.bungee.api.ChatColor;

public class Colors {
    final ChatColor g = ChatColor.GREEN;
    final ChatColor r = ChatColor.RED;
    final ChatColor gold = ChatColor.of("#FFD700");
    final ChatColor silver = ChatColor.of("#c0c0c0");
    final ChatColor bronze = ChatColor.of("#cd7f32");
    final ChatColor e = ChatColor.DARK_RED;
    final ChatColor y = ChatColor.YELLOW;
    public String translate(String msg) {
        if(msg==null)return null;
        String s = ChatColor.translateAlternateColorCodes('&', msg);
        return s;
    }
}
