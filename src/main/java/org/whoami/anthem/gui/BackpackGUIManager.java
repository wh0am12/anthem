package org.whoami.anthem.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.whoami.anthem.Anthem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class BackpackGUIManager {
    private final Anthem plugin;
    private final Map<String, BackpackGUI> guiMap = new HashMap<>();
    private final Map<Inventory,InventoryCloseHandler> closeHandlerMap = new HashMap<>();
    private final Map<Inventory,InventoryOpenHandler> openHandlerMap = new HashMap<>();
    private final Map<Inventory, InventoryDragHandler> dragHandlerMap = new HashMap<>();
    private final Map<Inventory, InventoryClickHandler> clickHandlerMap = new HashMap<>();
    public BackpackGUIManager(Anthem plugin){
        this.plugin = plugin;
    }
    public BackpackGUI createBackpackGUI(String UUID){
        BackpackGUI backpackGUI = new BackpackGUI(this,UUID);
        guiMap.put(UUID,backpackGUI);
        this.registerInventoryCloseHandler(backpackGUI.getInventory(), backpackGUI);
        this.registerInventoryOpenHandler(backpackGUI.getInventory(), backpackGUI);
        this.registerInventoryDragHandler(backpackGUI.getInventory(), backpackGUI);
        this.registerInventoryClickHandler(backpackGUI.getInventory(), backpackGUI);
        return backpackGUI;
    }
    public void registerInventoryCloseHandler(Inventory inventory, InventoryCloseHandler handler){
        this.closeHandlerMap.put(inventory,handler);
        plugin.getLogger().log(Level.SEVERE,"register");
    }
    public void unregisterInventoryCloseHandler(Inventory inventory){
        this.closeHandlerMap.remove(inventory);
        plugin.getLogger().log(Level.SEVERE,"unregister");
    }
    public void registerInventoryOpenHandler(Inventory inventory, InventoryOpenHandler handler){
        this.openHandlerMap.put(inventory,handler);
        plugin.getLogger().log(Level.SEVERE,"register");
    }
    public void unregisterInventoryOpenHandler(Inventory inventory){
        this.openHandlerMap.remove(inventory);
        plugin.getLogger().log(Level.SEVERE,"unregister");
    }
    public void registerInventoryDragHandler(Inventory inventory, InventoryDragHandler handler){
        this.dragHandlerMap.put(inventory,handler);
        plugin.getLogger().log(Level.SEVERE,"register");
    }
    public void unregisterInventoryDragHandler(Inventory inventory){
        this.dragHandlerMap.remove(inventory);
        plugin.getLogger().log(Level.SEVERE,"unregister");
    }
    public void registerInventoryClickHandler(Inventory inventory, InventoryClickHandler handler){
        this.clickHandlerMap.put(inventory,handler);
        plugin.getLogger().log(Level.SEVERE,"register");
    }
    public void unregisterInventoryClickHandler(Inventory inventory){
        this.clickHandlerMap.remove(inventory);
        plugin.getLogger().log(Level.SEVERE,"unregister");
    }
    public void removeGUI(String UUID){
        guiMap.remove(UUID);
    }
    public void handleClose(InventoryCloseEvent inventoryCloseEvent){
        InventoryCloseHandler handler = this.closeHandlerMap.get(inventoryCloseEvent.getInventory());
        if(handler!=null){
            handler.onClose(inventoryCloseEvent);
        }
    }
    public void handleOpen(InventoryOpenEvent inventoryOpenEvent){
        InventoryOpenHandler handler = this.openHandlerMap.get(inventoryOpenEvent.getInventory());
        if(handler!=null){
            handler.onOpen(inventoryOpenEvent);
        }
        plugin.getLogger().log(Level.SEVERE,"open");
    }
    public void handleDrag(InventoryDragEvent inventoryDragEvent){
        InventoryDragHandler handler = this.dragHandlerMap.get(inventoryDragEvent.getInventory());
        if(handler!=null){
            handler.onDrag(inventoryDragEvent);
        }
        plugin.getLogger().log(Level.SEVERE,"move");
    }
    public void handleClick(InventoryClickEvent inventoryClickEvent){
        InventoryClickHandler handler = this.clickHandlerMap.get(inventoryClickEvent.getInventory());
        if(handler!=null){
            handler.onClick(inventoryClickEvent);
        }
        plugin.getLogger().log(Level.SEVERE,"click");
    }
    public void openBackpack(Player player, String UUID){
        if(guiMap.containsKey(UUID)){
            player.openInventory(guiMap.get(UUID).getInventory());
        }else{
            BackpackGUI gui = createBackpackGUI(UUID);
            player.openInventory(gui.getInventory());
        }
    }
    public Anthem getPlugin(){
        return plugin;
    }
}
