package jackpot.lotteries;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jackpot.lotteries.AANew.DiscoLottery;
import jackpot.lotteries.AANew.MadmanLottery;
import jackpot.lotteries.AANew.SupernovaLottery;
import jackpot.lotteries.MuchoData.MuchoDebuger;
import jackpot.lotteries.MuchoData.MuchoInventory;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class Inventories implements Listener {
    private final Colors c = new Colors();
    private final Economy economy = Lotteries.getEcon();

    public void openMainInventory(Player p){
        MuchoInventory.openInventory(p,mainInventory(p));
    }
    public void openChooseInventory(Player p){
        MuchoInventory.openInventory(p,chooseInventory(p));
    }

    private Inventory mainInventory(Player p) {
        Messages m = new Messages();
        Inventory inv = Bukkit.createInventory(null, 27, m.getMainInvTitle());
        for (int x = 0; x < 27; x++) {
            inv.setItem(x, MuchoInventory.tlo(Material.GRAY_STAINED_GLASS_PANE));
        }
        inv.setItem(11,MuchoInventory.createInvItem(Material.BEACON,m.getDisplayNameMadman(p),m.getLoreMadman(p)));
        inv.setItem(13,MuchoInventory.createInvItem(Material.BEACON,m.getDisplayNameDisco(p),m.getLoreDisco(p)));
        inv.setItem(15,MuchoInventory.createInvItem(Material.BEACON,m.getDisplayNameSupernova(p),m.getLoreSupernova(p)));
        return inv;
    }

    private Inventory chooseInventory(Player p) {
        Messages m = new Messages();
        Inventory inv = Bukkit.createInventory(null, 27, m.getChooseAmountGuiTitle());
        for (int x = 0; x < 27; x++) {
            inv.setItem(x,MuchoInventory.tlo(Material.GRAY_STAINED_GLASS_PANE));
        }

        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(m.getGoBackButtonDisplaName(p));
        itemMeta.setLore(m.getGoBackButtonLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(9, item);

        item = craftHead(m.getAddLargeAmountDisplaName(p),m.getAddHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getAddLargeAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(10, item);

        item = craftHead(m.getAddMediumAmountDisplaName(p),m.getAddHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getAddMediumAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(11, item);

        item = craftHead(m.getAddSmallAmountDisplaName(p),m.getAddHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getAddSmallAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(12, item);

        item = new ItemStack(Material.ENCHANTED_BOOK);
        itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(m.getBookDisplaName(p));
        itemMeta.setLore(m.getBookButtonLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(13, item);

        item = craftHead(m.getSubtractSmallAmountDisplaName(p),m.getSubtractHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getSubtractSmallAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(14, item);

        item = craftHead(m.getSubtractMediumAmountDisplaName(p),m.getSubtractHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getSubtractMediumAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(15, item);

        item = craftHead(m.getSubtractLargeAmountDisplaName(p),m.getSubtractHeadValue());
        itemMeta = item.getItemMeta();
        itemMeta.setLore(m.getSubtractLargeAmountLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(16, item);

        item = new ItemStack(Material.STONE_BUTTON);
        itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(m.getAcceptButtonDisplaName(p));
        itemMeta.setLore(m.getAcceptButtonLore(p));
        item.setItemMeta(itemMeta);
        inv.setItem(22, item);
        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Messages m = new Messages();
        String title = event.getView().getTitle();
        if(!title.equalsIgnoreCase(m.getMainInvTitle())&&!title.equalsIgnoreCase(m.getChooseAmountGuiTitle()))return;
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        if(item==null)return;
        Material ma = item.getType();
        if(ma==Material.AIR)return;
        if(ma==Material.GRAY_STAINED_GLASS_PANE)return;
        if(item.getItemMeta()==null)return;
        Player p =(Player) event.getWhoClicked();
        int slot = event.getSlot();
        MadmanLottery ml = new MadmanLottery();
        DiscoLottery dl = new DiscoLottery();
        SupernovaLottery sl = new SupernovaLottery();
        Messages messages = new Messages();
        PlayerPersistent pp = new PlayerPersistent(p);
        if (title.equalsIgnoreCase(m.getMainInvTitle())) {
            if (slot == 11) {
                if(!ml.getMadmanConfig().isLotteryOngoing()){
                    sendMessage(p,messages.getLotteryIsNotActiveNowMessage());
                    return;
                }
                pp.setLotteryType(pp.type_madman);
                openChooseInventory(p);
                return;
            }
            if (slot == 13) {
                if(!dl.getDiscoConfig().isLotteryOngoing()){
                    sendMessage(p,messages.getLotteryIsNotActiveNowMessage());
                    return;
                }
                pp.setLotteryType(pp.type_disco);
                openChooseInventory(p);
                return;
            }
            if (slot == 15) {
                if(!sl.getSupernovaConfig().isLotteryOngoing()){
                    sendMessage(p,messages.getLotteryIsNotActiveNowMessage());
                    return;
                }
                pp.setLotteryType(pp.type_supernova);
                openChooseInventory(p);
                return;
            }
            return;
        }
        if(title.equalsIgnoreCase(messages.getChooseAmountGuiTitle())){
            if (slot == 9) {
                openMainInventory(p);
                return;
            }
            String type = pp.getLotteryType();
            int currentAmount = pp.getLotteryTicketAmount();
            int madmanPlayerTickets =  ml.getPlayerTicketBoughtAmount(p.getUniqueId());
            int discoPlayerTickets =  dl.getPlayerTicketBoughtAmount(p.getUniqueId());
            int supernovaPlayerTickets =  sl.getPlayerTicketBoughtAmount(p.getUniqueId());
            int ticketsLeftMadman = ml.getMadmanConfig().getMaxTicketsAmount()-madmanPlayerTickets;
            int ticketsLeftDisco = dl.getDiscoConfig().getMaxTicketsAmount()-discoPlayerTickets;
            int ticketsLeftSupernova =sl.getSupernovaConfig().getMaxTicketsAmount()-supernovaPlayerTickets;
            int largeAmount = 50;
            int mediumAmount = 5;
            int smallAmount = 1;

            if (slot == 10||slot==11||slot==12||slot==14||slot==15||slot==16) {
                if(type.equalsIgnoreCase(pp.type_madman)){
                    if(ticketsLeftMadman==0){
                        sendMessage(p,messages.getNoTicketsLeftMessage());
                        return;
                    }
                }
                if(type.equalsIgnoreCase(pp.type_disco)){
                    if(ticketsLeftDisco==0){
                        sendMessage(p,messages.getNoTicketsLeftMessage());
                        return;
                    }
                }
                if(type.equalsIgnoreCase(pp.type_supernova)){
                    if(ticketsLeftSupernova==0){
                        sendMessage(p,messages.getNoTicketsLeftMessage());
                        return;
                    }
                }


                if (slot == 10) {
                    currentAmount = currentAmount+largeAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }

                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }
                if (slot == 11) {
                    currentAmount = currentAmount+mediumAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }
                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }
                if (slot == 12) {
                    currentAmount = currentAmount+smallAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }
                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }

                if (slot == 14) {
                    currentAmount = currentAmount-smallAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }
                    if(currentAmount<0){
                        currentAmount = 0;
                    }
                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }
                if (slot == 15) {
                    currentAmount = currentAmount-mediumAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }
                    if(currentAmount<0){
                        currentAmount = 0;
                    }
                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }
                if (slot == 16) {
                    currentAmount = currentAmount-largeAmount;
                    if(type.equalsIgnoreCase(pp.type_madman)){
                        if(currentAmount>ticketsLeftMadman){
                            currentAmount = ticketsLeftMadman;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_disco)){
                        if(currentAmount>ticketsLeftDisco){
                            currentAmount = ticketsLeftDisco;
                        }
                    }
                    if(type.equalsIgnoreCase(pp.type_supernova)){
                        if(currentAmount>ticketsLeftSupernova){
                            currentAmount = ticketsLeftSupernova;
                        }
                    }
                    if(currentAmount<0){
                        currentAmount = 0;
                    }
                    pp.setLotteryTicketAmount(currentAmount);
                    openChooseInventory(p);
                    return;
                }
                return;
            }


            if (slot == 22) {
                if (currentAmount <= 0) return;
                int ticketPrize = 0;
                if(type.equalsIgnoreCase(pp.type_madman)){
                    if(!ml.getMadmanConfig().isLotteryOngoing()){
                        sendMessage(p,m.getLotteryIsNotActiveNowMessage());
                        return;
                    }
                    ticketPrize = ml.getMadmanConfig().getTicketPrize();
                }
                if(type.equalsIgnoreCase(pp.type_disco)){
                    if(!dl.getDiscoConfig().isLotteryOngoing()){
                        sendMessage(p,m.getLotteryIsNotActiveNowMessage());
                        return;
                    }
                    ticketPrize = dl.getDiscoConfig().getTicketPrize();
                }
                if(type.equalsIgnoreCase(pp.type_supernova)){
                    if(!sl.getSupernovaConfig().isLotteryOngoing()){
                        sendMessage(p,m.getLotteryIsNotActiveNowMessage());
                        return;
                    }
                    ticketPrize = sl.getSupernovaConfig().getTicketPrize();
                }
                double toBePayed = currentAmount*ticketPrize;
                if(!economy.has(p,toBePayed)){
                    sendMessage(p,messages.getNotEnoughMoney());
                    return;
                }

                economy.withdrawPlayer(p,toBePayed);

                if(type.equalsIgnoreCase(pp.type_madman)){
                    ml.buyTickets(p,currentAmount);
                }
                if(type.equalsIgnoreCase(pp.type_disco)){
                    dl.buyTickets(p,currentAmount);
                }
                if(type.equalsIgnoreCase(pp.type_supernova)){
                    sl.buyTickets(p,currentAmount);
                }



                sendMessage(p,messages.getYouBoughtTicketsMessage());
                MuchoInventory.closeInventory(p);
                pp.setLotteryTicketAmount(0);
                return;
            }
        }
    }


    private ItemStack craftHead(String displayName,String value){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
        if(itemmeta==null)return item;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures",value));
        Field field;
        try {
            field = itemmeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(itemmeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }
        Objects.requireNonNull(itemmeta).setDisplayName(displayName);
        itemmeta.setLore(null);
        item.setItemMeta(itemmeta);
        return item;
    }


    public  void sendMessage(Player p, String message){
        p.sendMessage(message);
    }
}
