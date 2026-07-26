package net.skuno.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skuno.Main;

public class BugCommand implements CommandExecutor {

    private final Main plugin;

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    public BugCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;


        if (args.length == 0) {

            p.sendMessage(
                    plugin.getMessages()
                    .get("bug.usage")
            );

            return true;
        }
        
        if(args[0].equalsIgnoreCase("credits")) {
        	p.sendMessage("§bDeveloped by Skuno\n§7- §8https://github.com/skun0");
        }


        if (!p.hasPermission("bug.cooldownbypass") && cooldown.containsKey(p.getUniqueId())) {

            long expire = cooldown.get(p.getUniqueId());

            long seconds = (expire - System.currentTimeMillis()) / 1000;

            if (seconds > 0) {

                long minutes = seconds / 60;
                long remainingSeconds = seconds % 60;

                String time = minutes + "m " + remainingSeconds + "s";

                p.sendMessage(
                        plugin.getMessages()
                        .get("bug.cooldown")
                        .replace("{time}", time)
                );

                return true;
            }

            cooldown.remove(p.getUniqueId());
        }

        String message = String.join(" ", args);

        if(message.equalsIgnoreCase("credits")) {
        	return true;
        }

        int id = plugin.getBugManager().saveBug(p.getName(), message);


        cooldown.put(
                p.getUniqueId(),
                System.currentTimeMillis() + (5 * 60 * 1000)
        );


        for (String msg : plugin.getMessages().getList("bug.sent")) {

            p.sendMessage(
                    msg.replace("{prefix}", plugin.getMessages().getPrefix())
                    .replace("{id}", String.valueOf(id))
            );
        }


        for (Player staff : Bukkit.getOnlinePlayers()) {

            if (staff.hasPermission("bug.notify")) {

            	staff.playSound(staff.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2.0f, 1.5f);

                for (String msg : plugin.getMessages().getList("staff.notify")) {

                    staff.sendMessage(
                            msg.replace("{prefix}", plugin.getMessages().getPrefix())
                            .replace("{player}", p.getName())
                            .replace("{message}", message)
                            .replace("{id}", String.valueOf(id))
                    );
                }
            }
        }


        return true;
    }
}