package jackpot.lotteries.AANew;

import org.bukkit.entity.Player;

import java.util.*;

public interface Lotteries {
    void stopLottery(Player p);
    void startLottery(Player p);
    void finishLottery(Player p);
    void buyTickets(Player p,int amount);
    List<UUID> getLotteryParticipantsUUIDs();
    HashMap<UUID, Integer> getLotteryParticipantTicketPair();
    default HashMap<UUID,Integer> getSortedMap(HashMap<UUID,Integer>map){

        List<Map.Entry<UUID, Integer> > list =
                new LinkedList<Map.Entry<UUID, Integer> >(map.entrySet());
        list.sort(new Comparator<Map.Entry<UUID, Integer>>() {
            public int compare(Map.Entry<UUID, Integer> o1,
                               Map.Entry<UUID, Integer> o2) {


                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<UUID, Integer> temp = new LinkedHashMap<>();
        for(Map.Entry<UUID,Integer> entry:list){
            UUID id = entry.getKey();
            int  value = entry.getValue();
            temp.put(id,value);
        }
        return temp;
    }
    default List<UUID> getListOfPlayersThatBoughtTheMostOfTickets(int topX){
        HashMap<UUID,Integer> map = getSortedMap(getLotteryParticipantTicketPair());
        int x = 0;
        List<UUID> list = new ArrayList<>();
        for(Map.Entry<UUID,Integer> entry: map.entrySet()){
            if(x==topX)break;
            list.add(entry.getKey());
            x++;
        }
        return list;
    }
    default int getAmountOfTicketsSum(){
        int x = 0;
        for(int value : getLotteryParticipantTicketPair().values()){
            x+=value;
        }
        return x;
    }
    default int getPlayerTicketBoughtAmount(UUID playerId){
        return getLotteryParticipantTicketPair().getOrDefault(playerId,0);
    }
}
