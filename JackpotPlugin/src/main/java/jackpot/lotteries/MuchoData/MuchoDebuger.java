package jackpot.lotteries.MuchoData;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MuchoDebuger {

    public static void error(String message){
        System.out.println(ChatColor.DARK_RED+"[MuchoERROR]: "+message);
    }
    public static void s(String debugMessage){
        System.out.println("Debug message: "+debugMessage);
        Player p = Bukkit.getPlayer("LaFlaure");
        if(p!=null&&p.isOnline()){
            p.sendMessage("Debug: "+debugMessage);
        }
    }
}
