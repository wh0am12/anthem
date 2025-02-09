package org.whoami.anthem.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface InventoryCloseHandler {
    void onClose(InventoryCloseEvent event);

}
