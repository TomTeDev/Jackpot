package jackpot.lotteries;

import jackpot.lotteries.AANew.DiscoLottery;
import jackpot.lotteries.AANew.LotteryMaps;
import jackpot.lotteries.AANew.MadmanLottery;
import jackpot.lotteries.AANew.SupernovaLottery;
import jackpot.lotteries.MuchoData.MuchoDebuger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public final class Lotteries extends JavaPlugin implements Listener {
    private static Plugin plugin;
    private FileConfiguration madmanCfg;
    private FileConfiguration discoCfg;
    private FileConfiguration supernovaCfg;
    private FileConfiguration messagesCfg;
    private LotteryMaps lotteryMaps;

    public static Economy econ = null;
    private static final Logger log = Logger.getLogger("Jackpot-Vault");

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    @Override
    public void onEnable() {
        plugin = this;
        //VAUlT ECONOMY CONNECTION
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        /**
        * Loads semi-static config, which are being keept in main class memory
        * */
        this.madmanCfg = YamlConfiguration.loadConfiguration(getMadmanCfgFile());

        this.discoCfg = YamlConfiguration.loadConfiguration(getDiscoCfgFile());

        this.supernovaCfg = YamlConfiguration.loadConfiguration(getSupernovaCfgFile());

        this.messagesCfg = YamlConfiguration.loadConfiguration(getMessagesCfgFile());

        this.lotteryMaps = new LotteryMaps();
        this.lotteryMaps.loadMaps();
        new MadmanLottery().startLotteryOnEnable();
        new DiscoLottery().startLotteryOnEnable();
        new SupernovaLottery().startLotteryOnEnable();

        getCommand("lottery").setExecutor(new Commands());
        registerEvents(this,new Listeners(),new Inventories());
        System.out.println(ChatColor.GOLD +"[Lotteries]:" +ChatColor.BLUE+"Jackpot plugin enabled!");

    }
    public void saveDefaults(FileConfiguration cfg, String fileName){


      /*  InputStream defaultStream = plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            cfg.setDefaults(yamlConfiguration);
        }*/
    }
    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);

        }
    }

    @Override
    public void onDisable() {
        this.lotteryMaps.saveMaps();
        try {
            this.madmanCfg.save(getMadmanCfgFile());
            this.discoCfg.save(getDiscoCfgFile());
            this.supernovaCfg.save(getSupernovaCfgFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        plugin = null;
    }








    public static String madmanFileName = "madman";
    public static String discoFileName = "disco";
    public static String supernovaFileName = "supernova";
    public static String messagesFileName = "messages";
    private File getCfgFile(String fileName) {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        File file = new File(plugin.getDataFolder().getAbsolutePath(), fileName + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
                plugin.saveResource(fileName+".yml", true);
            } catch (IOException e) {
                MuchoDebuger.error("Error while creating file: "+fileName);
            }
        }
        return file;
    }
    public File getMadmanCfgFile(){return getCfgFile(madmanFileName);}
    public File getDiscoCfgFile(){return getCfgFile(discoFileName);}
    public File getSupernovaCfgFile(){return getCfgFile(supernovaFileName);}
    public File getMessagesCfgFile(){return getCfgFile(messagesFileName);}
    public FileConfiguration getMadmanYaml(){return this.madmanCfg;}
    public FileConfiguration getDiscoYaml(){return this.discoCfg;}
    public FileConfiguration getSupernovaYaml(){return this.supernovaCfg;}
    public FileConfiguration getMessagesCfg(){
        return this.messagesCfg;
    }
    public void reloadMadmanCfg(){
        madmanCfg = YamlConfiguration.loadConfiguration(getMadmanCfgFile());
    }
    public void reloadDiscoCfg(){
        discoCfg = YamlConfiguration.loadConfiguration(getDiscoCfgFile());
    }
    public void reloadSupernovaCfg(){
        supernovaCfg = YamlConfiguration.loadConfiguration(getSupernovaCfgFile());
    }
    public void reloadMessagesCfg(){
        this.messagesCfg = YamlConfiguration.loadConfiguration(getMessagesCfgFile());
    }
    public void reloadConfigs(){
        reloadMadmanCfg();
        reloadDiscoCfg();
        reloadSupernovaCfg();
        reloadMessagesCfg();
    }

    public LotteryMaps getLotteryMaps() {
        return this.lotteryMaps;
    }
}
