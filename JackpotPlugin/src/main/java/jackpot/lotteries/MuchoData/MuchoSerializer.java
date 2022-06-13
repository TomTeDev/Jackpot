package jackpot.lotteries.MuchoData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MuchoSerializer {



    public static String itemStackListToString(List<ItemStack> items) {
        if(items==null)return null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(items.size());
            for (int i=0; i<items.size(); i++) {
                dataOutput.writeObject(items.get(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    public static List<ItemStack> itemStackListFromString(String data) {
        if(data==null)return new ArrayList<>();
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            int amount =dataInput.readInt();
            List<ItemStack> items = new ArrayList<>();
            for (int i=0; i<amount; i++) {
                ItemStack item = (ItemStack) dataInput.readObject();
                items.add(item);
            }
            dataInput.close();
            return items;
        } catch (Exception e) {
            return null;
        }
    }

    public static String itemStackToString(ItemStack item) {
        if(item==null)return null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(1);
            dataOutput.writeObject(item);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }
    public static ItemStack itemStackFromString(String data) {
        if(data==null)return null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack item = (ItemStack) dataInput.readObject();
            dataInput.close();
            return item;
        } catch (Exception e) {
            return null;
        }
    }

    public static String serializeLocation(Location loc){
        if(loc==null)return null;
        String world = loc.getWorld().getName();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        return world+"_"+x+"_"+y+"_"+z+"_"+yaw+"_"+pitch;
    }
    public static Location deserializeLocation(String locString){
        if(locString==null)return null;
        String [] locparts = locString.split("_");
        if(locparts.length!=6)return null;
        double x,y,z;
        float yaw,pitch;
        World world;
        String worldString = locparts[0];

        try{
            world = Bukkit.getWorld(worldString);
            if(world==null)return null;
            x = Double.parseDouble(locparts[1]);
            y = Double.parseDouble(locparts[2]);
            z = Double.parseDouble(locparts[3]);
            yaw = Float.parseFloat(locparts[4]);
            pitch = Float.parseFloat(locparts[5]);
        }catch (NumberFormatException e){
            System.out.println("|WARN| Corrupted location value inside LuckyBlocks yaml file!");
            return null;
        }

        return new Location(world,x,y,z,yaw,pitch);
    }


}
