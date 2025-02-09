package org.whoami.anthem.utils;

import com.saicone.rtag.RtagItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItemBuilder {
    RtagItem result;
    String name;
    List<String> lore;
    public CustomItemBuilder(){
        result = null;
        name = null;
        lore = null;
    }
    public CustomItemBuilder base(Material material,String customID){
        this.result = new RtagItem(new ItemStack(material));
        result.set(customID,"customID");
        return this;
    }
    public CustomItemBuilder base(ItemStack itemStack,String customID){
        this.result = new RtagItem(itemStack);
        result.set(customID,"customID");
        return this;
    }
    public CustomItemBuilder withName(String name){
        this.name = name;
        return this;
    }
    public CustomItemBuilder withLore(List<String> lore){
        this.lore = lore;
        return this;
    }
    public ItemStack build(){
        ItemStack out = result.load();
        ItemMeta meta = out.getItemMeta();
        if(name!=null){
            meta.setDisplayName(name);
        }
        if(lore!=null){
            meta.setLore(lore);
        }
        out.setItemMeta(meta);
        reset();
        return out;
    }
    public void reset(){
        result = null;
        name = null;
        lore = null;
    }
}
