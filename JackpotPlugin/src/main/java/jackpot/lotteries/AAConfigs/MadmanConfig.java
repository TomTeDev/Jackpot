package jackpot.lotteries.AAConfigs;

import jackpot.lotteries.Lotteries;

public class MadmanConfig extends ConfigAbstract{
    private static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    public MadmanConfig() {
        super(plugin.getMadmanYaml());
    }
    public void reloadCfg(){
        plugin.reloadMadmanCfg();
    }
}
