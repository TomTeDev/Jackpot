package jackpot.lotteries.AANew;

import jackpot.lotteries.AAConfigs.DiscoConfig;
import jackpot.lotteries.AAConfigs.MadmanConfig;
import jackpot.lotteries.AAConfigs.SupernovaConfig;
import jackpot.lotteries.MuchoData.MuchoTimeManager;
import jackpot.lotteries.PlayerPersistent;
import org.bukkit.entity.Player;

public class Placeholders {
    public String replacePlaceHolders(String s, Player p){
        if(s==null)return "";

        MadmanLottery ml = new MadmanLottery();
        MadmanConfig madmanConfig = ml.getMadmanConfig();
        DiscoLottery dl = new DiscoLottery();
        DiscoConfig discoConfig = dl.getDiscoConfig();
        SupernovaLottery sl = new SupernovaLottery();
        SupernovaConfig supernovaConfig = sl.getSupernovaConfig();
        String x = "%lottery_madman_time%";

        if(s.contains(x)){s = replace(s,x, MuchoTimeManager.getTime(madmanConfig.getLotteryEndTime()));}
        x = "%lottery_disco_time%";
        if(s.contains(x)){s = replace(s,x, MuchoTimeManager.getTime(discoConfig.getLotteryEndTime()));}
        x = "%lottery_supernova_time%";
        if(s.contains(x)){s = replace(s,x, MuchoTimeManager.getTime(supernovaConfig.getLotteryEndTime()));}

        x = "%lottery_madman_top1name%";
        if(s.contains(x)){s = replace(s,x, madmanConfig.getPastTop1Name());}
        x = "%lottery_disco_top1name%";
        if(s.contains(x)){s = replace(s,x, discoConfig.getPastTop1Name());}
        x = "%lottery_supernova_top1name%";
        if(s.contains(x)){s = replace(s,x, supernovaConfig.getPastTop1Name());}

        x = "%lottery_madman_top1cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop1CashWon()));}
        x = "%lottery_disco_top1cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop1CashWon()));}
        x = "%lottery_supernova_top1cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop1CashWon()));}

        x = "%lottery_madman_top1tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop1TicketsBought()));}
        x = "%lottery_disco_top1tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop1TicketsBought()));}
        x = "%lottery_supernova_top1tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop1TicketsBought()));}



        x = "%lottery_madman_top2name%";
        if(s.contains(x)){s = replace(s,x, madmanConfig.getPastTop2Name());}
        x = "%lottery_disco_top2name%";
        if(s.contains(x)){s = replace(s,x, discoConfig.getPastTop2Name());}
        x = "%lottery_supernova_top2name%";
        if(s.contains(x)){s = replace(s,x, supernovaConfig.getPastTop2Name());}

        x = "%lottery_madman_top2cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop2CashWon()));}
        x = "%lottery_disco_top2cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop2CashWon()));}
        x = "%lottery_supernova_top2cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop2CashWon()));}

        x = "%lottery_madman_top2tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop2TicketsBought()));}
        x = "%lottery_disco_top2tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop2TicketsBought()));}
        x = "%lottery_supernova_top2tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop2TicketsBought()));}


        x = "%lottery_madman_top3name%";
        if(s.contains(x)){s = replace(s,x, madmanConfig.getPastTop3Name());}
        x = "%lottery_disco_top3name%";
        if(s.contains(x)){s = replace(s,x, discoConfig.getPastTop3Name());}
        x = "%lottery_supernova_top3name%";
        if(s.contains(x)){s = replace(s,x, supernovaConfig.getPastTop3Name());}

        x = "%lottery_madman_top3cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop3CashWon()));}
        x = "%lottery_disco_top3cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop3CashWon()));}
        x = "%lottery_supernova_top3cash%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop3CashWon()));}

        x = "%lottery_madman_top3tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getPastTop3TicketsBought()));}
        x = "%lottery_disco_top3tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getPastTop3TicketsBought()));}
        x = "%lottery_supernova_top3tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getPastTop3TicketsBought()));}


        if(p!=null){
            PlayerPersistent pp = new PlayerPersistent(p);
            x = "%lottery_madman_current_player_tickets%";
            if(s.contains(x)){s = replace(s,x, String.valueOf(ml.getPlayerTicketBoughtAmount(p.getUniqueId())));}
            x = "%lottery_disco_current_player_tickets%";
            if(s.contains(x)){s = replace(s,x, String.valueOf(dl.getPlayerTicketBoughtAmount(p.getUniqueId())));}
            x = "%lottery_supernova_current_player_tickets%";
            if(s.contains(x)){s = replace(s,x, String.valueOf(sl.getPlayerTicketBoughtAmount(p.getUniqueId())));}
            x = "%lottery_current_type%";
            if(s.contains(x)){s = replace(s,x, String.valueOf(pp.getLotteryType()));}
            x = "%lottery_current_amount%";
            if(s.contains(x)){s = replace(s,x, String.valueOf(pp.getLotteryTicketAmount()));}
            x = "%lottery_current_ticket_prize%";
            if(s.contains(x)){
                String type = pp.getLotteryType();
                if(type.equalsIgnoreCase(pp.type_madman)){
                    s = replace(s,x,String.valueOf(ml.getMadmanConfig().getTicketPrize()));
                }else if(type.equalsIgnoreCase(pp.type_disco)){
                    s = replace(s,x,String.valueOf(dl.getDiscoConfig().getTicketPrize()));
                }else{
                    s = replace(s,x,String.valueOf(sl.getSupernovaConfig().getTicketPrize()));
                }

            }
        }
        x = "%lottery_madman_current_all_tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(ml.getAmountOfTicketsSum()));}
        x = "%lottery_disco_current_all_tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(dl.getAmountOfTicketsSum()));}
        x = "%lottery_supernova_current_all_tickets%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(sl.getAmountOfTicketsSum()));}
        x = "%lottery_madman_ticket_prize%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(madmanConfig.getTicketPrize()));}
        x = "%lottery_disco_ticket_prize%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(discoConfig.getTicketPrize()));}
        x = "%lottery_supernova_ticket_prize%";
        if(s.contains(x)){s = replace(s,x, String.valueOf(supernovaConfig.getTicketPrize()));}
        return s;
    }
    public String replace(String s, String tobeReplaced,String replacement){
       if(s==null)return s;
       if(replacement==null)return s;
       return s.replace(tobeReplaced,replacement);
    }
}
