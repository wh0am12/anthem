package org.whoami.anthem.gui;

import com.saicone.rtag.RtagItem;
import com.saicone.rtag.item.ItemTagStream;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class BackpackGUI implements InventoryCloseHandler , InventoryOpenHandler, InventoryDragHandler, InventoryClickHandler{
    private final String[] BannedItem = {
            "backpack_v1"
    };
    private ItemTagStream streamInstance = ItemTagStream.INSTANCE;
    private final BackpackGUIManager manager;
    private final String uuid;
    private Inventory inventory;
    private List<String> activePlayer;
    public BackpackGUI(BackpackGUIManager manager,String uuid){
        this.uuid = uuid;
        this.manager = manager;
        this.activePlayer = new ArrayList<>();
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
        activePlayer.remove(event.getPlayer().getUniqueId().toString());
        if(activePlayer.isEmpty()){
            manager.unregisterInventoryCloseHandler(this.inventory);
            manager.unregisterInventoryOpenHandler(this.inventory);
            manager.unregisterInventoryDragHandler(this.inventory);
            manager.unregisterInventoryClickHandler(this.inventory);
            manager.removeGUI(uuid);
        }
    }
    public void onOpen(InventoryOpenEvent event) {
        activePlayer.add(event.getPlayer().getUniqueId().toString());
    }
    public void onDrag(InventoryDragEvent event) {
        RtagItem item = new RtagItem(event.getOldCursor());
        String ID = item.getOptional("customID").or("nullbb");
        manager.getPlugin().getLogger().log(Level.SEVERE,ID);
        if(Arrays.stream(BannedItem).anyMatch(ID::equals)){
            event.setCancelled(true);
        }
    }
    public void onClick(InventoryClickEvent event) {
        if(event.getCurrentItem().getData().getItemType()== Material.AIR){
            return;
        }
        if (event.getClick()== ClickType.NUMBER_KEY) {
            event.setCancelled(true);
        }
        RtagItem item = new RtagItem(event.getCurrentItem());
        String ID = item.getOptional("customID").or("null");
        manager.getPlugin().getLogger().log(Level.SEVERE, ID);
        if (Arrays.stream(BannedItem).anyMatch(ID::equals)) {
            event.setCancelled(true);
        }
    }
    private ItemStack[] prepareItemStacks(String base64){
        if(base64==null){
            return null;
        }
        String[] base64Array = base64.split(",");
        ItemStack[] stacks = new ItemStack[27];
        for (int i = 0; i < base64Array.length; i++) {
            int finalI = i;
            streamInstance.fromBase64(base64Array[i],(itemStack ->{
                stacks[finalI] = itemStack;
            }));
        }
        return stacks;
    }

}
