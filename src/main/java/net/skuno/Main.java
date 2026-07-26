package net.skuno;

import org.bukkit.plugin.java.JavaPlugin;

import net.skuno.commands.BugCommand;
import net.skuno.managers.BugManager;
import net.skuno.managers.Messages;

public class Main extends JavaPlugin {

    private static Main instance;
    private BugManager bugManager;

    private Messages messages;
    
    
    @Override
    public void onEnable() {

        instance = this;

        bugManager = new BugManager(this);

        messages = new Messages(this);
        getCommand("bug").setExecutor(new BugCommand(this));

        getLogger().info("Enabled!");
    }


    public static Main getInstance() {
        return instance;
    }


    public BugManager getBugManager() {
        return bugManager;
    }
    
    public Messages getMessages() {
        return messages;
    }
}