package jackpot.lotteries.AAConfigs;

import jackpot.lotteries.Colors;
import jackpot.lotteries.Lotteries;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public abstract class ConfigMessages {
    public static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    private final FileConfiguration cfg;
    private final Colors color;
    public ConfigMessages(FileConfiguration cfg) {
        this.cfg =cfg;
        this.color = new Colors();
    }

    private final String basePath = "data";
    private String craftPath(String path){
        return basePath+"."+path;
    }
    private void setInt(int value,String path){
        cfg.set(craftPath(path),value);
    }
    private int getInt(String path,int reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getInt(craftPath(path)):reserveValue;
    }
    private void setString(String value,String path){
        cfg.set(craftPath(path),value);
    }
    public String getString(String path,String reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getString(craftPath(path)):reserveValue;
    }
    private void setBoolean(boolean value,String path){
        cfg.set(craftPath(path),value);
    }
    private boolean getBoolean(String path,boolean reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getBoolean(craftPath(path)):reserveValue;
    }
    private void setListString(List<String> value, String path){
        cfg.set(craftPath(path),value);
    }
    public List<String> getListString(String path,List<String> reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getStringList(craftPath(path)):reserveValue;
    }
}
