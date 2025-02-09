package org.whoami.anthem.commands;

import com.saicone.rtag.RtagItem;
import com.saicone.rtag.item.ItemTagStream;
import com.saicone.rtag.util.SkullTexture;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.whoami.anthem.Anthem;
import org.whoami.anthem.gui.BackpackGUIManager;
import org.whoami.anthem.utils.BackpackBuilder;

import java.util.*;
import java.util.logging.Level;

public class BackpackCommand implements CommandExecutor, TabCompleter {
    private final Anthem plugin;
    public BackpackCommand(Anthem plugin){
        this.plugin = plugin;
    }
    public static List<String> arg1 = Arrays.asList("new","open");
    private final BackpackBuilder backpackBuilder = new BackpackBuilder();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings[0].equals("new")){
                if(strings.length==1) {
                    player.sendMessage(ChatColor.GREEN+"Generated with random UUID");
                    UUID backpackUUID = UUID.randomUUID();
                    ItemStack item = backpackBuilder.backpackWithTexture("backpack_v1",
                            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE1MGZkOGJmNzc4ZjA1OWZiOTkwYzk3Y2U3YzA2ZTE5NWJhYWI5MzU5YTc5MjU0Zjc0MTRiYTI0M2Y0Y2MwYSJ9fX0=")
                            .withUUID(backpackUUID)
                            .withName(ChatColor.GOLD + "Backpack")
                            .withLore(List.of(ChatColor.GRAY + backpackUUID.toString()))
                            .build();
                    player.getInventory().addItem(item);
                    return true;
                }else {
                    try{
                        UUID backpackUUID = UUID.fromString(strings[1]);
                        player.sendMessage(ChatColor.GREEN+"Generated with given UUID");
                        ItemStack item = backpackBuilder.backpackWithTexture("backpack_v1",
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE1MGZkOGJmNzc4ZjA1OWZiOTkwYzk3Y2U3YzA2ZTE5NWJhYWI5MzU5YTc5MjU0Zjc0MTRiYTI0M2Y0Y2MwYSJ9fX0=")
                                .withUUID(strings[1])
                                .withName(ChatColor.GOLD + "Backpack")
                                .withLore(List.of(ChatColor.GRAY + strings[1]))
                                .build();
                        player.getInventory().addItem(item);
                        return true;
                    }catch (IllegalArgumentException e){
                        player.sendMessage(ChatColor.RED+"Invalid UUID, generated with random UUID");
                        UUID backpackUUID = UUID.randomUUID();
                        ItemStack item = backpackBuilder.backpackWithTexture("backpack_v1",
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE1MGZkOGJmNzc4ZjA1OWZiOTkwYzk3Y2U3YzA2ZTE5NWJhYWI5MzU5YTc5MjU0Zjc0MTRiYTI0M2Y0Y2MwYSJ9fX0=")
                                .withUUID(backpackUUID)
                                .withName(ChatColor.GOLD + "Backpack")
                                .withLore(List.of(ChatColor.GRAY + backpackUUID.toString()))
                                .build();
                        player.getInventory().addItem(item);
                        return true;
                    }
                }
            }else if (strings[0].equals("open")){
                if(strings.length==1){
                    player.sendMessage(ChatColor.RED+"Please input uuid");
                    return false;
                }else if(strings.length==2){
                    try{
                        UUID backpackUUID = UUID.fromString(strings[1]);
                        player.sendMessage(ChatColor.GREEN+"Opened backpack with given UUID");
                        plugin.getBackpackGUIManager().openBackpack(player,strings[1]);
                        return true;
                    }catch (IllegalArgumentException e){
                        player.sendMessage(ChatColor.RED+"Invalid UUID");
                        return true;
                    }
                }else{
                    return false;
                }

            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length==1){
            return arg1;
        }
        return List.of();
    }
    static {

    }
}