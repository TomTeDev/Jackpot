package jackpot.lotteries.AAConfigs;

import jackpot.lotteries.Colors;
import jackpot.lotteries.Lotteries;
import jackpot.lotteries.MuchoData.MuchoDebuger;
import jackpot.lotteries.MuchoData.MuchoTimeManager;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.CheckForNull;
import java.time.LocalDateTime;
import java.util.*;


public abstract class ConfigAbstract {
    public static Lotteries plugin = Lotteries.getPlugin(Lotteries.class);
    private final FileConfiguration cfg;
    private final Colors color;
    public ConfigAbstract(FileConfiguration cfg) {
        this.cfg =cfg;
        this.color = new Colors();
    }

    private final String basePath = "data";
    private final String path_to_lottery_name = "path_to_lottery_name";
    private final String path_to_ticket_prize = "path_to_ticket_prize";
    private final String path_to_endTime_do_not_edit = "path_to_endTime_do_not_edit";
    private final String path_to_ongoing_do_not_edit = "path_to_ongoing_do_not_edit";
    private final String path_to_max_tickets_amount = "path_to_max_tickets_amount";
    private final String path_to_first_place_cut = "path_to_first_place_cut";
    private final String path_to_second_place_cut = "path_to_second_place_cut";
    private final String path_to_third_place_cut = "path_to_third_place_cut";
    private final String path_to_server_place_cut = "path_to_server_place_cut";
    private final String path_to_past_top_do_not_edit = "path_to_past_top_do_not_edit";
    private final String path_to_should_lottery_start_over_again_after_end = "path_to_should_lottery_start_over_again_after_end";
    private final String path_to_lottery_starting_messages_list = "path_to_lottery_starting_messages_list";
    private final String path_to_lottery_repetative_messages_list = "path_to_lottery_repetative_messages_list";
    private final String path_to_lottery_repetative_messages_cycle = "path_to_lottery_repetative_messages_cycle";
    private final String path_to_lottery_finish_messages_list = "path_to_lottery_finish_messages_list";

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
    private String getString(String path,String reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getString(craftPath(path)):reserveValue;
    }
    private void setBoolean(boolean value,String path){
        cfg.set(craftPath(path),value);
    }
    private boolean getBoolean(String path,boolean reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getBoolean(craftPath(path)):reserveValue;
    }
    private void setListString(List<String> value,String path){
        cfg.set(craftPath(path),value);
    }
    private List<String> getListString(String path,List<String> reserveValue){
        return (cfg.contains(craftPath(path))&&(cfg.get(craftPath(path))!=null))?cfg.getStringList(craftPath(path)):reserveValue;
    }

    public String getLotteryName(){
        return color.translate(getString(path_to_lottery_name,null));
    }
    public LocalDateTime getLotteryEndTime() {
        String timeString = getString(path_to_endTime_do_not_edit,null);
        if(timeString==null)return null;
        return MuchoTimeManager.dataTimeFromString(timeString);
    }
    public void setLotteryEndTime(LocalDateTime endTime) {
        setString(MuchoTimeManager.dataTimeToString(endTime),path_to_endTime_do_not_edit);
    }
    public boolean isLotteryOngoing() {
        return getBoolean(path_to_ongoing_do_not_edit,false);
    }
    public void setLotteryOngoing(boolean isItGoing) {
        setBoolean(isItGoing,path_to_ongoing_do_not_edit);
    }
    public int getTicketPrize() {
        return getInt(path_to_ticket_prize,0);
    }
    public void setTicketPrize(int value){
        setInt(value,path_to_ticket_prize);
    }
    public void setMaxTicketsAmount(int value){
        setInt(value,path_to_max_tickets_amount);
    }
    public int getMaxTicketsAmount(){
        return getInt(path_to_max_tickets_amount,0);
    }
    public void setFirstPlaceCut(int cut){
        setInt(cut,path_to_first_place_cut);
    }
    public int getFirstPlaceCut(){
        return getInt(path_to_first_place_cut,50);
    }
    public void setSecondPlaceCut(int cut){
        setInt(cut,path_to_second_place_cut);
    }
    public int getSecondPlaceCut(){
        return getInt(path_to_second_place_cut,25);
    }
    public void setThirdPlaceCut(int cut){
        setInt(cut,path_to_third_place_cut);
    }
    public int getThirdPlaceCut(){
        return getInt(path_to_third_place_cut,15);
    }
    public void setServerPlaceCut(int cut){
        setInt(cut,path_to_server_place_cut);
    }
    public int getServerPlaceCut(){
        return getInt(path_to_server_place_cut,10);
    }
    private String getPastTopData(){
        return getString(path_to_past_top_do_not_edit,null);
    }
    private final String spacer = "]]";
    public void setPastTopData(String nameTop1,UUID idTop1,Integer ticketsBoughtTop1,Integer cashWonTop1,String nameTop2,UUID idTop2,Integer ticketsBoughtTop2,Integer cashWonTop2,String nameTop3,UUID idTop3,Integer ticketsBoughtTop3,Integer cashWonTop3){
        if(idTop1==null){
            setString(null,path_to_past_top_do_not_edit);
            return;
        }
        StringBuilder builder = new StringBuilder();

        builder.append(idTop1.toString()).append(spacer).append(ticketsBoughtTop1).append(spacer).append(cashWonTop1);
        if(idTop2==null){
            setString(builder.toString(),path_to_past_top_do_not_edit);
            return;
        }
        builder.append(idTop2.toString()).append(spacer).append(ticketsBoughtTop2).append(spacer).append(cashWonTop2);
        if(idTop3==null){
            setString(builder.toString(),path_to_past_top_do_not_edit);
            return;
        }
        builder.append(idTop3.toString()).append(spacer).append(ticketsBoughtTop3).append(spacer).append(cashWonTop3);
        setString(builder.toString(),path_to_past_top_do_not_edit);
    }
    @CheckForNull
    public String getPastTop1Data(){
        String data = getPastTopData();
        if(data==null)return null;
        String[] dataSplit = data.split(spacer);
        if(dataSplit.length<4)return null;
        return dataSplit[0] + spacer + dataSplit[1] + spacer + dataSplit[2]+spacer+dataSplit[3];
    }
    @CheckForNull
    public String getPastTop1Name(){
        if(getPastTop1Data()==null)return null;
        String data = getPastTop1Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return dataSplit[0];
    }
    @CheckForNull
    public UUID getPastTop1Id(){
        if(getPastTop1Data()==null)return null;
        String data = getPastTop1Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return UUID.fromString(dataSplit[1]);
    }
    public int getPastTop1TicketsBought(){
        if(getPastTop1Data()==null)return 0;
        String data = getPastTop1Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[2]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }
    public int getPastTop1CashWon(){
        if(getPastTop1Data()==null)return 0;
        String data = getPastTop1Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[3]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }

    @CheckForNull
    public String getPastTop2Data(){
        String data = getPastTopData();
        if(data==null)return null;
        String[] dataSplit = data.split(spacer);
        if(dataSplit.length<8)return null;
        return dataSplit[4] + spacer + dataSplit[5] + spacer + dataSplit[6]+spacer+dataSplit[7];
    }
    @CheckForNull
    public String getPastTop2Name(){
        if(getPastTop2Data()==null)return null;
        String data = getPastTop2Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return dataSplit[0];
    }
    @CheckForNull
    public UUID getPastTop2Id(){
        if(getPastTop2Data()==null)return null;
        String data = getPastTop2Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return UUID.fromString(dataSplit[1]);
    }
    public int getPastTop2TicketsBought(){
        if(getPastTop2Data()==null)return 0;
        String data = getPastTop2Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[2]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }
    public int getPastTop2CashWon(){
        if(getPastTop2Data()==null)return 0;
        String data = getPastTop2Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[3]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }

    @CheckForNull
    public String getPastTop3Data(){
        String data = getPastTopData();
        if(data==null)return null;
        String[] dataSplit = data.split(spacer);
        if(dataSplit.length<12)return null;
        return dataSplit[8] + spacer + dataSplit[9] + spacer + dataSplit[10]+spacer+dataSplit[11];
    }
    @CheckForNull
    public String getPastTop3Name(){
        if(getPastTop3Data()==null)return null;
        String data = getPastTop3Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return dataSplit[0];
    }
    @CheckForNull
    public UUID getPastTop3Id(){
        if(getPastTop3Data()==null)return null;
        String data = getPastTop3Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return null;
        }
        return UUID.fromString(dataSplit[1]);
    }
    public int getPastTop3TicketsBought(){
        if(getPastTop3Data()==null)return 0;
        String data = getPastTop3Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[2]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }
    public int getPastTop3CashWon(){
        if(getPastTop3Data()==null)return 0;
        String data = getPastTop3Data();
        String[]dataSplit = data.split(spacer);
        if(dataSplit.length!=4){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
            return 0;
        }
        int value = 0;
        try {
            value = Integer.parseInt(dataSplit[3]);
        }catch (NumberFormatException e){
            MuchoDebuger.error("Corrupted data for past winners inside config!(They do not exist)");
        }
        return value;
    }

    public boolean getShouldLotteryStartOverAgainAfterEnd(){
        return getBoolean(path_to_should_lottery_start_over_again_after_end,false);
    }
    public void setShouldLotteryStartOverAgainAfterEnd(boolean value){
        setBoolean(value,path_to_should_lottery_start_over_again_after_end);
    }
    public List<String> getLotteryStartingMessagesList(){
        return getListString(path_to_lottery_starting_messages_list,new ArrayList<>());
    }
    public List<String> getLotteryRepetativeMessagesList(){
        return getListString(path_to_lottery_repetative_messages_list,new ArrayList<>());
    }
    public int getLotteryRepetativeMessagesTimeSeconds(){
        return getInt(path_to_lottery_repetative_messages_cycle,0);
    }
    public List<String> getLotteryFinishMessagesList(){
        return getListString(path_to_lottery_finish_messages_list,new ArrayList<>());
    }


    public int getRandomNumber(int participants) {
        if (participants == 0) return 1;
        Random rand = new Random();
        int n = rand.nextInt(participants);
        n = n + 1;
        return n;
    }


}
