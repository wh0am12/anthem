package org.whoami.anthem.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class CustomInventory {
    //deprecated may have some use
    public enum CustomInventoryType{
        backpack
    }

    private final Inventory inventory;
    private final CustomInventoryType type;
    public CustomInventory(Inventory inventory, CustomInventoryType type){
        this.inventory = inventory;
        this.type = type;
    }

    public CustomInventory(int size, String name, CustomInventoryType type){
        this.inventory = Bukkit.createInventory(null, size, name);
        this.type = type;
    }
}
