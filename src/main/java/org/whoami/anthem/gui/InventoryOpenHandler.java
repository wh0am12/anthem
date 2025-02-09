package org.whoami.anthem.gui;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface InventoryOpenHandler {
    void onOpen(InventoryOpenEvent event);

}
