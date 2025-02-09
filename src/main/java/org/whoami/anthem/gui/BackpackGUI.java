package org.whoami.anthem.gui;

import com.saicone.rtag.item.ItemTagStream;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class BackpackGUI implements InventoryCloseHandler {
    private ItemTagStream streamInstance = ItemTagStream.INSTANCE;
    private final BackpackGUIManager manager;
    private final String uuid;
    private Inventory inventory;
    public BackpackGUI(BackpackGUIManager manager,String uuid){
        this.uuid = uuid;
        this.manager = manager;
    }
    public Inventory getInventory() {
        if(inventory==null){
            this.inventory = Bukkit.createInventory(null,27, uuid);
            ItemStack[] contents = prepareItemStacks(manager.getPlugin().getBackpackDatabase().getInventoryData(uuid));
            if(contents!=null){
                inventory.setStorageContents( contents);
            }
        }
        return inventory;
    }
    public void onClose(InventoryCloseEvent event) {
        ItemStack[] stacks= event.getInventory().getStorageContents();
        List<String> base64List = new ArrayList<>();
        manager.getPlugin().getLogger().log(Level.SEVERE, String.valueOf(stacks.length));
        for(ItemStack stack: stacks){
            base64List.add(streamInstance.toBase64(stack));
        }
        manager.getPlugin().getBackpackDatabase().putInventoryData(uuid,27,String.join(",",base64List));
    }
    private ItemStack[] prepareItemStacks(String base64){
        if(base64==null){
            return null;
        }
        String[] base64Array = base64.split(",");
        ItemStack[] stacks = new ItemStack[27];
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < base64Array.length; i++) {
            int finalI = i;
            streamInstance.fromBase64(base64Array[i],(itemStack ->{
                stacks[finalI] = itemStack;
            }));
        }
        return stacks;
    }
}
