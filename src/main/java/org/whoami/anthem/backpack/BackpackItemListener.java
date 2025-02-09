package org.whoami.anthem.backpack;

import com.saicone.rtag.RtagItem;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.EventExecutor;
import org.jetbrains.annotations.NotNull;

public class BackpackItemListener implements Listener{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction()== Action.RIGHT_CLICK_AIR){
            ItemStack item = event.getItem();
            RtagItem tag = new RtagItem(item);
            if(!(tag.get("customID")instanceof String)){
                return;
            }
            if(tag.get("customID").equals("backpack_v1")){
                event.getPlayer().sendMessage("isBackpack");
            }else{
                event.getPlayer().sendMessage("not");

            }
        }
    }
}
