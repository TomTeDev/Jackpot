package jackpot.lotteries.MuchoData;

import jackpot.lotteries.Lotteries;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MuchoInventory {

    public static boolean hasKey(ItemStack item, String key){
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        if(!nms.hasTag())return false;
        NBTTagCompound tag = nms.getOrCreateTag();
        return tag.hasKey(key);
    }
    public static void openInventory(Player p, Inventory inv){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Lotteries.getPlugin(Lotteries.class), () -> {
            p.openInventory(inv);
        },1);
    }
    public static ItemStack tlo(Material tlo){
        return createInvItem(tlo," ",null);
    }
    public static void closeInventory(Player p){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Lotteries.getPlugin(Lotteries.class), p::closeInventory,1);
    }
    public static ItemStack dodajDodatkoweLore(ItemStack item, List<String> dodatkoweLore){
        List<String> lore = null;
        if(item.hasItemMeta()&&item.getItemMeta().hasLore()){
            lore = item.getItemMeta().getLore();
        }else{
            lore = new ArrayList<>();
        }
        lore.addAll(dodatkoweLore);

        item.getItemMeta().setLore(lore);
        return item;
    }
    public static ItemStack createInvItem(Material mat, @Nullable String displayName, @Nullable List<String> lore){
        ItemStack itemStack = new ItemStack(mat);
        if(displayName!=null){
            ItemMeta meta = itemStack.getItemMeta();
            if(meta!=null){
                meta.setDisplayName(displayName);
                itemStack.setItemMeta(meta);
            }
        }
        if(lore!=null){
            ItemMeta meta = itemStack.getItemMeta();
            if(meta!=null){
                meta.setLore(lore);
                itemStack.setItemMeta(meta);
            }
        }
        return itemStack;
    }
    public static ItemStack zamknijOkno(){
        return createInvItem(Material.BARRIER, ChatColor.DARK_RED+""+ChatColor.BOLD+"ZAMKNIJ OKNO",null);
    }
    @CheckForNull
    public static String getTagString(ItemStack item, String key){
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.getOrCreateTag();
        if(!tag.hasKey(key))return null;
        return tag.getString(key);
    }

    public static int getTagInt(ItemStack item, String key){
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.getOrCreateTag();
        if(!tag.hasKey(key))return 0;
        return tag.getInt(key);
    }
    public static ItemStack removeTag(ItemStack item, String key){
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        if(!nms.hasTag())return item;
        NBTTagCompound tag = nms.getOrCreateTag();
        if(tag.hasKey(key)){
            tag.remove(key);
        }
        return CraftItemStack.asBukkitCopy(nms);
    }
    public static ItemStack addTag(ItemStack item, String key, Object tagValue){
        if(tagValue instanceof String){
            String tagFinal = (String)tagValue;
            net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag = nms.getOrCreateTag();
            tag.setString(key,tagFinal);
            return CraftItemStack.asBukkitCopy(nms);
        }
        if(tagValue instanceof Integer){
            int tagFinal = (Integer)tagValue;
            net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag = nms.getOrCreateTag();
            tag.setInt(key,tagFinal);
            return CraftItemStack.asBukkitCopy(nms);
        }

        return item;
    }
}