package de.lastrix.tntrain;

import de.lastrix.tntrain.Commands.tntrain;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static boolean isActive;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().addDefault("seconds", 10);

        getCommand("tntrain").setExecutor(new tntrain());

        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static de.lastrix.tntrain.Main getPlugin() {
        return plugin;
    }

    
}
