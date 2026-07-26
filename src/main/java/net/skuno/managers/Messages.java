package net.skuno.managers;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.skuno.Main;

public class Messages {

    private final Main plugin;
    private File file;
    private FileConfiguration config;


    public Messages(Main plugin) {

        this.plugin = plugin;

        file = new File(plugin.getDataFolder(), "messages.yml");

        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        reload();
    }


    public void reload() {

        config = YamlConfiguration.loadConfiguration(file);

    }


    public String get(String path) {

        String message = config.getString(path);

        if (message == null) {
            return "§cMissing message: " + path;
        }

        return replace(color(message));

    }


    public List<String> getList(String path) {

        return config.getStringList(path)
                .stream()
                .map(this::color)
                .map(this::replace)
                .toList();

    }


    public String getPrefix() {

        return color(config.getString("prefix", ""));

    }


    public String replace(String message) {

        return message.replace("{prefix}", getPrefix());

    }


    private String color(String text) {

        return ChatColor.translateAlternateColorCodes('&', text);

    }
}