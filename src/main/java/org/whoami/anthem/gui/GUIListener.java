package org.whoami.anthem.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {
    private final BackpackGUIManager guiManager;

    public GUIListener(BackpackGUIManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        guiManager.handleClose(event);
    }
}
