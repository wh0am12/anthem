package org.whoami.anthem.listeners;

import com.saicone.rtag.RtagItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.whoami.anthem.Anthem;

public class ItemInteractListener implements Listener{
    private Anthem plugin;
    public ItemInteractListener(Anthem plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction()== Action.RIGHT_CLICK_AIR){
            ItemStack item = event.getItem();
            RtagItem tag = new RtagItem(item);
            if(!(tag.get("customID")instanceof String)){
                return;
            }
            if(tag.getOptional("customID").or("null").equals("backpack_v1")){
                plugin.getBackpackGUIManager().openBackpack(event.getPlayer(), tag.get("backpackUUID"));
            }else{

            }
        }
    }
}
