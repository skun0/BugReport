package net.skuno.managers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.skuno.Main;

public class BugManager {

    private final Main plugin;

    private File file;
    private FileConfiguration config;


    public BugManager(Main plugin) {

        this.plugin = plugin;

        file = new File(plugin.getDataFolder(), "bugs.yml");

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        config = YamlConfiguration.loadConfiguration(file);
    }


    public int getNextID() {

        return config.getInt("last-id", 0) + 1;

    }


    public int saveBug(String player, String message) {

        int id = getNextID();


        config.set("last-id", id);

        config.set("bugs." + id + ".player", player);
        config.set("bugs." + id + ".message", message);
        config.set("bugs." + id + ".status", "OPEN");
        config.set("bugs." + id + ".date", 
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new Date()));


        save();


        return id;
    }


    public String getBugStatus(int id) {

        return config.getString(
                "bugs." + id + ".status",
                "UNKNOWN"
        );

    }


    public String getBugPlayer(int id) {

        return config.getString(
                "bugs." + id + ".player",
                "Unknown"
        );

    }


    public String getBugMessage(int id) {

        return config.getString(
                "bugs." + id + ".message",
                "Unknown"
        );

    }


    public void setStatus(int id, String status) {

        config.set(
                "bugs." + id + ".status",
                status
        );

        save();

    }


    private void save() {

        try {

            config.save(file);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}