package jackpot.lotteries.AANew;

import jackpot.lotteries.AAConfigs.DiscoConfig;
import jackpot.lotteries.Colors;
import jackpot.lotteries.Lotteries;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class DiscoLottery implements jackpot.lotteries.AANew.Lotteries {
    private final DiscoConfig cfg;
    private final LotteryMaps maps;
    private final Colors color;
    private final static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    private final Economy economy = Lotteries.getEcon();
    public DiscoLottery(){
        this.cfg = new DiscoConfig();
        this.color = new Colors();
        this.maps = plugin.getLotteryMaps();
    }
    public DiscoConfig getDiscoConfig(){
        return this.cfg;
    }
    @Override
    public List<UUID> getLotteryParticipantsUUIDs() {
        return new ArrayList<>(getLotteryParticipantTicketPair().keySet());
    }
    @Override
    public HashMap<UUID, Integer> getLotteryParticipantTicketPair() {
        return maps.getDiscoMap();
    }
    public void clearMap(){
        maps.clearDiscoMap();
    }
    @Override
    public void stopLottery(Player p) {
        if(!cfg.isLotteryOngoing()){
            p.sendMessage(ChatColor.RED+"Lottery is not active");
            return;
        }
        int ticketPrize = cfg.getTicketPrize();
        for(Map.Entry<UUID,Integer> entry: getLotteryParticipantTicketPair().entrySet()){
            UUID id = entry.getKey();
            int amount = entry.getValue();
            double cashToGiveBack = amount*ticketPrize;
            OfflinePlayer ofp = Bukkit.getOfflinePlayer(id);
            if(ofp==null)continue;
            economy.depositPlayer(ofp,cashToGiveBack);
            if(ofp.isOnline()){
                p.sendMessage(ChatColor.GREEN+"One of lotteries were shut down, u received back "+cashToGiveBack+" $");
            }
        }
        cfg.setLotteryOngoing(false);
        cfg.setLotteryEndTime(null);
        clearMap();
        p.sendMessage(ChatColor.RED+"Lottery turned off");
    }
    @Override
    public void startLottery(Player p) {
        if(cfg.isLotteryOngoing()){
            p.sendMessage(ChatColor.RED+"Lottery is already running");
            return;
        }
        LocalDateTime endTime = cfg.getLotteryEndTime();
        if(endTime==null){
            endTime = LocalDateTime.now().plusDays(7);
            cfg.setLotteryEndTime(endTime);
        }
        cfg.setLotteryOngoing(true);
        startCancelationRunnable(endTime);
        sendStartingMessage();
        startSendingRepetableMessage();
    }
    public void startLotteryOnEnable(){
        LocalDateTime endTime = cfg.getLotteryEndTime();
        if(endTime==null){
            if(!cfg.getShouldLotteryStartOverAgainAfterEnd()){
                return;
            }
            endTime = LocalDateTime.now().plusDays(7);
            cfg.setLotteryEndTime(endTime);
        }
        cfg.setLotteryOngoing(true);
        startCancelationRunnable(endTime);
        sendStartingMessage();
        startSendingRepetableMessage();
    }
    @Override
    public void finishLottery(Player p) {
        cfg.setLotteryOngoing(false);
        cfg.setLotteryEndTime(null);
        int random = 0;
        UUID firstWin = null;
        UUID secondWin = null;
        UUID thirdWin = null;
        int z = 3;
        for(int x = 0;x<z;x++){
            if(z==15){
                break;
            }
            random = cfg.getRandomNumber(getAmountOfTicketsSum());
            int ticket = 0;
            for (Map.Entry<UUID,Integer> map : getLotteryParticipantTicketPair().entrySet()){
                ticket = ticket+map.getValue();
                if(ticket>=random){

                    if(thirdWin!=null){
                        break;
                    }
                    if(firstWin==null){
                        firstWin = map.getKey();
                        break;
                    }
                    if(secondWin==null){
                        if(firstWin.equals(map.getKey())){
                            z++;
                            break;
                        }
                        secondWin = map.getKey();
                        continue;
                    }
                    if(thirdWin==null){
                        if(firstWin.equals(map.getKey())){
                            z++;
                            break;
                        }
                        if(secondWin.equals(map.getKey())){
                            z++;
                            break;
                        }
                        thirdWin = map.getKey();
                        break;
                    }


                }
            }
        }
        int firstAmount = getLotteryParticipantTicketPair().getOrDefault(firstWin,0);
        int secondAmount = getLotteryParticipantTicketPair().getOrDefault(secondWin,0);
        int thirdAmount = getLotteryParticipantTicketPair().getOrDefault(thirdWin,0);
        int totalMoney = getAmountOfTicketsSum()* cfg.getTicketPrize();
        totalMoney = totalMoney-totalMoney*cfg.getServerPlaceCut()/100;
        int firstPlaceCut = totalMoney*cfg.getFirstPlaceCut()/100;
        int secondPlaceCut = totalMoney*cfg.getSecondPlaceCut()/100;
        int thirdPlaceCut = totalMoney*cfg.getThirdPlaceCut()/100;
        String firstName = null;
        String secondName = null;
        String thirdName = null;
        if(firstWin!=null){
            OfflinePlayer player = Bukkit.getOfflinePlayer(firstWin);
            firstName = player.getName();
            economy.depositPlayer(player,firstPlaceCut);
        }
        if(secondWin!=null){
            OfflinePlayer player = Bukkit.getOfflinePlayer(secondWin);
            secondName = player.getName();
            economy.depositPlayer(player,secondPlaceCut);
        }
        if(thirdWin!=null){
            OfflinePlayer player = Bukkit.getOfflinePlayer(thirdWin);
            thirdName = player.getName();
            economy.depositPlayer(player,thirdPlaceCut);
        }

        if(firstWin!=null){
            if(secondWin==null){
                cfg.setPastTopData(firstName,firstWin,firstAmount,firstPlaceCut,null,null,null,null,null,null,null,null);
            }
            if(thirdWin==null){
                cfg.setPastTopData(firstName,firstWin,firstAmount,firstPlaceCut,secondName,secondWin,secondAmount,secondPlaceCut,null,null,null,null);
            }else{
                cfg.setPastTopData(firstName,firstWin,firstAmount,firstPlaceCut,secondName,secondWin,secondAmount,secondPlaceCut,thirdName,thirdWin,thirdAmount,thirdPlaceCut);
            }
        }
        clearMap();
        sendFinishMessage();
        if(cfg.getShouldLotteryStartOverAgainAfterEnd()){
            startLottery(p);
        }

    }

    @Override
    public void buyTickets(Player p, int amount) {
        int has = getPlayerTicketBoughtAmount(p.getUniqueId());
        maps.discoMapPut(p.getUniqueId(),amount+has);
    }
    private final Placeholders ph = new Placeholders();
    public void startCancelationRunnable(LocalDateTime endTime){
        LocalDateTime now = LocalDateTime.now();
        if(endTime.isBefore(now))return;
        Duration dur = Duration.between(now,endTime);
        int sec = (int)dur.toSeconds();
        int seconds = sec*20;
        RunnableDelayed runnableDelayed = new RunnableDelayed(plugin,seconds) {
            @Override
            public void run() {
                if(!cfg.isLotteryOngoing())return;
                finishLottery(null);
            }
        };
    }
    public void sendStartingMessage(){
        List<String> list = cfg.getLotteryStartingMessagesList();
        if(!list.isEmpty()){
            for(String s: list){
                s = ph.replacePlaceHolders(s,null);
                s = color.translate(s);
                Bukkit.broadcastMessage(s);
            }
        }
    }
    public void startSendingRepetableMessage(){
        int value = cfg.getLotteryRepetativeMessagesTimeSeconds();
        if(value<=0)return;
        List<String> list = cfg.getLotteryRepetativeMessagesList();
        List<String> newList = new ArrayList<>();
        for(String s:list){
            s = ph.replacePlaceHolders(s,null);
            s = color.translate(s);
            newList.add(s);
        }
        if(list.isEmpty())return;
        int cooldown = 20 *value;
        RunnableRepeating runnableRepeating = new RunnableRepeating(plugin,cooldown,cooldown) {
            @Override
            public void run() {
                if(!cfg.isLotteryOngoing()){
                    canncel();
                }
                for(String s:newList){
                    Bukkit.broadcastMessage(s);
                }
            }
        };
    }
    public void sendFinishMessage(){
        List<String> messages = cfg.getLotteryFinishMessagesList();
        if(messages.isEmpty())return;
        List<String> newList = new ArrayList<>();
        for(String s: messages){
            s = ph.replacePlaceHolders(s,null);
            s = color.translate(s);
            newList.add(s);
        }
        for(String s: newList){
            Bukkit.broadcastMessage(s);
        }
    }
}
