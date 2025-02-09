package org.whoami.anthem.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.whoami.anthem.Anthem;

import java.util.HashMap;
import java.util.Map;

public class BackpackGUIManager {
    private final Anthem plugin;
    public BackpackGUIManager(Anthem plugin){
        this.plugin = plugin;
    }
    private final Map<Inventory,InventoryCloseHandler> closeHandlerMap = new HashMap<>();
    public void registerInventoryCloseHandler(Inventory inventory, InventoryCloseHandler handler){
        this.closeHandlerMap.put(inventory,handler);
    }
    public void unregisterInventoryCloseHandler(Inventory inventory, InventoryCloseHandler handler){
        this.closeHandlerMap.remove(inventory);
    }
    public void handleClose(InventoryCloseEvent inventoryCloseEvent){
        InventoryCloseHandler handler = this.closeHandlerMap.get(inventoryCloseEvent.getInventory());
        if(handler!=null){
            handler.onClose(inventoryCloseEvent);
        }
    }
    public void openBackpack(Player player, String UUID){
        BackpackGUI backpackGUI = new BackpackGUI(this,UUID);
        this.registerInventoryCloseHandler(backpackGUI.getInventory(), backpackGUI);
        player.openInventory(backpackGUI.getInventory());
    }
    public Anthem getPlugin(){
        return plugin;
    }
}
