package jackpot.lotteries;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerPersistent {
    private final PersistentDataContainer pdc;
    private final Player p;
    private final Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    public PlayerPersistent(Player p){
        this.p = p;
        this.pdc =  p.getPersistentDataContainer();
    }

    private final String path_amount = "path_amount";
    private final String path_type = "path_type";
    public void setLotteryTicketAmount(int amount){
        pdc.set(new NamespacedKey(plugin,path_amount), PersistentDataType.INTEGER,amount);
    }
    public int getLotteryTicketAmount(){
        if(pdc.has(new NamespacedKey(plugin,path_amount),PersistentDataType.INTEGER)){
            return  pdc.get(new NamespacedKey(plugin,path_amount),PersistentDataType.INTEGER);
        }else{
            return 0;
        }
    }
    public void setLotteryType(String type){
        pdc.set(new NamespacedKey(plugin,path_type), PersistentDataType.STRING,type);
    }
    public String getLotteryType(){
        if(pdc.has(new NamespacedKey(plugin,path_type),PersistentDataType.STRING)){
            return  pdc.get(new NamespacedKey(plugin,path_type),PersistentDataType.STRING);
        }else{
            return type_madman;
        }
    }
    public String type_madman = "madman";
    public String type_disco = "disco";
    public String type_supernova = "supernova";


}
