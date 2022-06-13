package jackpot.lotteries.AAConfigs;

import jackpot.lotteries.Lotteries;

public class SupernovaConfig extends ConfigAbstract{
    private static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    public SupernovaConfig() {
        super(plugin.getSupernovaYaml());
    }
    public void reloadCfg(){
        plugin.reloadSupernovaCfg();
    }
}
