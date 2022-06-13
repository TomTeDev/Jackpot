package jackpot.lotteries.AANew;

import jackpot.lotteries.Lotteries;
import jackpot.lotteries.MuchoData.MuchoDebuger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LotteryMaps {
    private final String hashMapConfig = "hashMapConfig";
    private final String basePathMadman = "data.madman";
    private final String basePathDisco = "data.disco";
    private final String basePathSupernova = "data.supernova";
    private HashMap<UUID,Integer> madmanMap = new HashMap<>();

    private HashMap<UUID,Integer> discoMap = new HashMap<>();
    private HashMap<UUID,Integer> supernovaMap = new HashMap<>();
    private static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    public void loadMaps(){
        FileConfiguration cfg = getCfg(getCfgFile(hashMapConfig));

        if(cfg.getConfigurationSection(basePathMadman)!=null&&!cfg.getConfigurationSection(basePathMadman).getKeys(false).isEmpty()){
            for(String s: cfg.getConfigurationSection(basePathMadman).getKeys(false)){
                try{
                    UUID id = UUID.fromString(s);
                    String pathNew = basePathMadman+"."+s;
                    int val = cfg.getInt(pathNew);
                    madmanMap.put(id,val);
                }catch (Exception e){
                    MuchoDebuger.error("Corrupted player value inside hashMapConfig "+s);
                }
            }
        }
        if(cfg.getConfigurationSection(basePathDisco)!=null&&!cfg.getConfigurationSection(basePathDisco).getKeys(false).isEmpty()){
            for(String s: cfg.getConfigurationSection(basePathDisco).getKeys(false)){
                try{
                    UUID id = UUID.fromString(s);
                    String pathNew = basePathDisco+"."+s;
                    int val = cfg.getInt(pathNew);
                    discoMap.put(id,val);
                }catch (Exception e){
                    MuchoDebuger.error("Corrupted player value inside hashMapConfig "+s);
                }
            }
        }
        if(cfg.getConfigurationSection(basePathSupernova)!=null&&!cfg.getConfigurationSection(basePathSupernova).getKeys(false).isEmpty()){
            for(String s: cfg.getConfigurationSection(basePathSupernova).getKeys(false)){
                try{
                    UUID id = UUID.fromString(s);
                    String pathNew = basePathSupernova+"."+s;
                    int val = cfg.getInt(pathNew);
                    supernovaMap.put(id,val);
                }catch (Exception e){
                    MuchoDebuger.error("Corrupted player value inside hashMapConfig "+s);
                }
            }
        }

    }
    public void saveMaps(){
        saveCfg(getCfgWithDataInIt(),getCfgFile(hashMapConfig));
    }
    private FileConfiguration getCfgWithDataInIt(){
        FileConfiguration cfg = getCfg(getCfgFile(hashMapConfig));
        //clearing existing data;
        cfg.set(basePathMadman,null);
        cfg.set(basePathDisco,null);
        cfg.set(basePathSupernova,null);


        for(Map.Entry<UUID,Integer> entry: madmanMap.entrySet()){
            String id = entry.getKey().toString();
            int val = entry.getValue();
            String path = basePathMadman+"."+id;
            cfg.set(path,val);
        }
        for(Map.Entry<UUID,Integer> entry: discoMap.entrySet()){
            String id = entry.getKey().toString();
            int val = entry.getValue();
            String path = basePathDisco+"."+id;
            cfg.set(path,val);
        }
        for(Map.Entry<UUID,Integer> entry: supernovaMap.entrySet()){
            String id = entry.getKey().toString();
            int val = entry.getValue();
            String path = basePathSupernova+"."+id;
            cfg.set(path,val);
        }
        return cfg;
    }
    private void saveCfg(FileConfiguration cfg,File file){
        try {
            cfg.save(file);
        }catch (Exception e){
            MuchoDebuger.error("Failed on saving data inside hashMapCfg");
        }
    }
    private void saveCfgAsync(){
        FileConfiguration cfg = getCfgWithDataInIt();
        File file = getCfgFile(hashMapConfig);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    cfg.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0);
    }
    public HashMap<UUID,Integer> getMadmanMap(){return this.madmanMap;}
    public HashMap<UUID,Integer> getDiscoMap(){return this.discoMap;}
    public HashMap<UUID,Integer> getSupernovaMap(){return this.supernovaMap;}
    public int madmanMapGet(UUID id,int defValue){return madmanMap.getOrDefault(id,defValue);}
    public int discoMapGet(UUID id,int defValue){return discoMap.getOrDefault(id,defValue);}
    public int supernovaMapGet(UUID id,int defValue){return supernovaMap.getOrDefault(id,defValue);}
    public void madmanMapPut(UUID id,int value){madmanMap.put(id,value);saveCfgAsync();}
    public void discoMapPut(UUID id,int value){discoMap.put(id,value);saveCfgAsync();}
    public void supernovaMapPut(UUID id,int value){supernovaMap.put(id,value);saveCfgAsync();}

    private File getCfgFile(String fileName) {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        File file = new File(plugin.getDataFolder().getAbsolutePath(), fileName + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                MuchoDebuger.error("Error while creating file: "+fileName);
            }
        }
        return file;
    }
    private FileConfiguration getCfg(File file){
        return YamlConfiguration.loadConfiguration(file);
    }
    public void clearMadmanMap(){
        this.madmanMap = new HashMap<>();
    }
    public void clearDiscoMap(){
        this.discoMap = new HashMap<>();
    }
    public void clearSupernovaMap(){
        this.supernovaMap = new HashMap<>();
    }
}
