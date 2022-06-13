package jackpot.lotteries;

import jackpot.lotteries.AANew.DiscoLottery;
import jackpot.lotteries.AANew.MadmanLottery;
import jackpot.lotteries.AANew.SupernovaLottery;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor {

    private final Messages m = new Messages();
    private final Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] strings) {
            if(!(sender instanceof Player) ){
                System.out.println(ChatColor.RED+"Only humans can use that command (not via console)");
                return false;
            }

            Player p = (Player) sender;
            Economy economy = Lotteries.getEcon();
            int length = strings.length;
            MadmanLottery ml = new MadmanLottery();
            DiscoLottery dl = new DiscoLottery();
            SupernovaLottery sl = new SupernovaLottery();

            switch (length){
                case 0:{
                    //Open main gui
                    new Inventories().openMainInventory(p);
                    break;}
                case 1:{
                    String s1 = strings[0];
                    switch (s1){
                        case "mybets":{
                            //done
                            sendMybetsMessage(p);
                            break;}
                        case "reload":{
                            //done
                            if(!p.isOp())break;
                            plugin.reloadConfigs();
                            p.sendMessage(ChatColor.GREEN+"Lottery configs reloaded!");
                            break;}
                        case "help":{
                            //done
                            if(p.isOp()){
                                sendHelpPlayer(p);
                                sendHelpOp(p);
                            }else{
                                sendHelpPlayer(p);
                            }
                            break;}
                        default:{
                            sendHelpMessage(p);
                            break;
                        }
                    }
                    break;
                }
                case 2:{
                    //done
                    if(!p.isOp())break;

                    String s1 = strings[0];
                    String s2 = strings[1];
                    switch (s1){
                        case "madman":{
                            switch (s2){
                                case "stop":{
                                    if(!ml.getMadmanConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    ml.stopLottery(p);
                                    break;}
                                case "start":{
                                    if(ml.getMadmanConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is already active");
                                        break;
                                    }
                                    ml.startLottery(p);
                                    break;}
                                case "finish":{
                                    if(!ml.getMadmanConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    ml.finishLottery(p);
                                    break;}
                                default:{
                                    sendHelpMessage(p);
                                    break;
                                }
                            }
                            break;
                        }
                        case "disco":{
                            switch (s2){
                                case "stop":{
                                    if(!dl.getDiscoConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    dl.stopLottery(p);
                                    break;}
                                case "start":{
                                    if(dl.getDiscoConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is already active");
                                        break;
                                    }
                                    dl.startLottery(p);
                                    break;}
                                case "finish":{
                                    if(!dl.getDiscoConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    dl.finishLottery(p);
                                    break;}
                                default:{
                                    sendHelpMessage(p);
                                    break;
                                }
                            }
                            break;
                        }
                        case "supernova":{
                            switch (s2){
                                case "stop":{
                                    if(!sl.getSupernovaConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    sl.stopLottery(p);
                                    break;}
                                case "start":{
                                    if(sl.getSupernovaConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is already active");
                                        break;
                                    }
                                    sl.startLottery(p);
                                    break;}
                                case "finish":{
                                    if(!sl.getSupernovaConfig().isLotteryOngoing()){
                                        p.sendMessage(ChatColor.GREEN+"Lottery is not active");
                                        break;
                                    }
                                    sl.finishLottery(p);
                                    break;}
                                default:{
                                    sendHelpMessage(p);
                                    break;
                                }
                            }
                            break;
                        }
                        default:{
                            sendHelpMessage(p);
                            break;
                        }
                    }
                    break;
                }
                case 3:{
                    int madmanPlayerTickets =  ml.getPlayerTicketBoughtAmount(p.getUniqueId());
                    int discoPlayerTickets =  dl.getPlayerTicketBoughtAmount(p.getUniqueId());
                    int supernovaPlayerTickets =  sl.getPlayerTicketBoughtAmount(p.getUniqueId());
                    int ticketsLeftMadman = ml.getMadmanConfig().getMaxTicketsAmount()-madmanPlayerTickets;
                    int ticketsLeftDisco = dl.getDiscoConfig().getMaxTicketsAmount()-discoPlayerTickets;
                    int ticketsLeftSupernova =sl.getSupernovaConfig().getMaxTicketsAmount()-supernovaPlayerTickets;

                    String s1 = strings[0];
                    switch (s1){
                        case "madman":{
                            if(!strings[1].equalsIgnoreCase("join")){
                                sendHelpMessage(p);
                                break;
                            }
                            if(!ml.getMadmanConfig().isLotteryOngoing()){
                                sendLotteryIsNotActiveNowMessage(p);
                                break;
                            }
                            int amount = 0;
                            try{
                                amount = Integer.parseInt(strings[2]);
                            }catch (NumberFormatException e){
                                sendWrongNumberMessage(p);
                                break;
                            }

                            if(amount>ticketsLeftMadman){
                                amount = ticketsLeftMadman;
                            }
                            if(amount<=0){
                                sendWrongNumberMessage(p);
                                break;
                            }
                            double toBePayed = ml.getMadmanConfig().getTicketPrize()*amount;
                            if(!economy.has(p,toBePayed)){
                                sendNotEnoughMoneyMessage(p);
                                break;
                            }
                            economy.withdrawPlayer(p,toBePayed);
                            ml.buyTickets(p,amount);
                            break;
                        }
                        case "disco":{
                            if(!strings[1].equalsIgnoreCase("join")){
                                sendHelpMessage(p);
                                break;
                            }
                            if(!dl.getDiscoConfig().isLotteryOngoing()){
                                sendLotteryIsNotActiveNowMessage(p);
                                break;
                            }
                            int amount = 0;
                            try{
                                amount = Integer.parseInt(strings[2]);
                            }catch (NumberFormatException e){
                                sendWrongNumberMessage(p);
                                break;
                            }
                            if(amount>ticketsLeftDisco){
                                amount = ticketsLeftDisco;
                            }
                            if(amount<=0){
                                sendWrongNumberMessage(p);
                                break;
                            }
                            double toBePayed = dl.getDiscoConfig().getTicketPrize()*amount;
                            if(!economy.has(p,toBePayed)){
                                sendNotEnoughMoneyMessage(p);
                                break;
                            }
                            economy.withdrawPlayer(p,toBePayed);
                            dl.buyTickets(p,amount);
                            break;
                        }
                        case "supernova":{
                            if(!strings[1].equalsIgnoreCase("join")){
                                sendHelpMessage(p);
                                break;
                            }
                            if(!sl.getSupernovaConfig().isLotteryOngoing()){
                                sendLotteryIsNotActiveNowMessage(p);
                                break;
                            }
                            int amount = 0;
                            try{
                                amount = Integer.parseInt(strings[2]);
                            }catch (NumberFormatException e){
                                sendWrongNumberMessage(p);
                                break;
                            }
                            if(amount>ticketsLeftSupernova){
                                amount = ticketsLeftSupernova;
                            }
                            if(amount<=0){
                                sendWrongNumberMessage(p);
                                break;
                            }
                            double toBePayed = sl.getSupernovaConfig().getTicketPrize()*amount;
                            if(!economy.has(p,toBePayed)){
                                sendNotEnoughMoneyMessage(p);
                                break;
                            }
                            economy.withdrawPlayer(p,toBePayed);
                            sl.buyTickets(p,amount);
                            break;
                        }
                        default:{
                            sendHelpMessage(p);
                            break;
                        }
                    }
                    break;
                }
                default:{
                    sendHelpMessage(p);
                    break;
                }
            }
            return true;
        }
    public void sendHelpMessage(Player p){
        String message = m.getWrongCommandMessage();
        if(message!=null){
            p.sendMessage(message);
        }
    }
    public void sendWrongNumberMessage(Player p){
        String message = m.getWrongNumberMessage();
        if(message!=null){
            p.sendMessage(message);
        }
    }
    public void sendHelpOp(Player p){
        for(String s :m.getOpHelpMessages()){
            p.sendMessage(s);
        }

    }
    public void sendHelpPlayer(Player p){
        for(String s :m.getPlayerHelpMessages()){
            p.sendMessage(s);
        }
    }
    public void sendMybetsMessage(Player p){
        List<String> list  = m.getMybetsMessage();
        if(list.isEmpty()){
            return;
        }
       for(String s: list){
           p.sendMessage(s);
       }
    }
    public void sendNotEnoughMoneyMessage(Player p){
        String message = m.getNotEnoughMoney();
        if(message!=null){
            p.sendMessage(message);
        }
    }
    public void sendLotteryIsNotActiveNowMessage(Player p){
        String message = m.getLotteryIsNotActiveNowMessage();
        if(message!=null){
            p.sendMessage(message);
        }
    }
}
