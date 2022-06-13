package jackpot.lotteries.AAConfigs;

import jackpot.lotteries.Lotteries;

public class DiscoConfig extends ConfigAbstract{
    private static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    public DiscoConfig() {
        super(plugin.getDiscoYaml());
    }
    public void reloadCfg(){
        plugin.reloadDiscoCfg();
    }
}
