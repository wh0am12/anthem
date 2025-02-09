package org.whoami.anthem.utils;

import com.saicone.rtag.util.SkullTexture;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BackpackBuilder extends CustomItemBuilder{
    String uuid;
    public BackpackBuilder(){
        super();
        uuid =null;
    }
    public BackpackBuilder backpackWithTexture(String backpackID, String base64){
        return (BackpackBuilder) super.base(SkullTexture.getTexturedHead(base64),backpackID);
    }
    public BackpackBuilder withUUID(String UUID){
        result.set(UUID,"backpackUUID");
        uuid = UUID;
        return this;
    }
    public BackpackBuilder withUUID(UUID UUID){
        result.set(UUID.toString(),"backpackUUID");
        uuid = UUID.toString();
        return this;
    }

    @Override
    public ItemStack build(){
        if(uuid ==null){
            this.withUUID(UUID.randomUUID().toString());
        }
        ItemStack out = super.build();
        reset();
        return out;
    }
    @Override
    public void reset(){
        uuid =null;
        super.reset();
    }

}
