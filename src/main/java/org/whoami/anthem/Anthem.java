package org.whoami.anthem;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.whoami.anthem.backpack.BackpackItemListener;
import org.whoami.anthem.commands.BackpackCommand;
import org.whoami.anthem.database.BackpackDatabase;
import org.whoami.anthem.gui.BackpackGUIManager;
import org.whoami.anthem.gui.GUIListener;

import java.util.logging.Logger;

public final class Anthem extends JavaPlugin {
    private BackpackDatabase backpackDatabase;
    private BackpackGUIManager backpackGUIManager;
    @NotNull
    @Override
    public Logger getLogger() {
        return super.getLogger();
    }

    @Override
    public void onEnable() {
        this.getDataFolder().mkdirs();
        this.backpackDatabase = new BackpackDatabase(this);
        this.backpackDatabase.load();
        this.backpackGUIManager = new BackpackGUIManager(this);
        this.getCommand("backpack").setExecutor(new BackpackCommand(this));
        this.getServer().getPluginManager().registerEvents(new BackpackItemListener(),this);
        this.getServer().getPluginManager().registerEvents(new GUIListener(backpackGUIManager),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public BackpackDatabase getBackpackDatabase() {
        return this.backpackDatabase;
    }

    public BackpackGUIManager getBackpackGUIManager() {
        return backpackGUIManager;
    }
}