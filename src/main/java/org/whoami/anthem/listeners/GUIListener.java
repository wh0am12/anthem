package org.whoami.anthem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.whoami.anthem.gui.BackpackGUIManager;

public class GUIListener implements Listener {
    private final BackpackGUIManager guiManager;

    public GUIListener(BackpackGUIManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        guiManager.handleClose(event);
    }
    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        guiManager.handleOpen(event);
    }
}
