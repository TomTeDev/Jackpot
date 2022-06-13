package jackpot.lotteries.MuchoData;

import net.md_5.bungee.api.ChatColor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MuchoTimeManager {
    public static LocalDateTime dataTimeFromString(String time){
        if(time==null)return null;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(time, formatter);
    }
    public static String dataTimeToString(LocalDateTime time){
        if(time==null)return null;
        return time.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    public static String getLadnaData(LocalDateTime data){
        if(data==null)return "";
        return data.getDayOfMonth()+":"+data.getHour()+":"+data.getMinute();
    }
    public static String getCzasByDuration(Duration duration){
        long minutes = duration.toMinutes();
        int days = (int)(minutes/60/24);
        minutes = (minutes- (long) days *60*24);
        long hours = minutes/60;
        minutes = (minutes- (long) hours *60);
        return ChatColor.YELLOW+"Dni: "+ChatColor.GREEN+days+ChatColor.YELLOW+", godziny"+ChatColor.GREEN+hours+ChatColor.YELLOW+", minuty"+ChatColor.GREEN+minutes;
    }
    public static String getTime(LocalDateTime timeTillEnd){
        if(timeTillEnd==null)return "";
        LocalDateTime timeNow =LocalDateTime.now();
        if(timeNow.isAfter(timeTillEnd))return "";
        Duration duration = Duration.between(timeNow,timeTillEnd);
        long minutes = duration.toMinutes();
        int days = (int)(minutes/60/24);
        minutes = (minutes- (long) days *60*24);
        long hours = minutes/60;
        minutes = (minutes- (long) hours *60);
        return days+":"+hours+":"+minutes;
    }
}