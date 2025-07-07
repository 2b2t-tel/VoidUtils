package net.utils.voidutils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UptimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        long current = System.currentTimeMillis();
        long uptimeMillis = current - VoidUtils.serverStartTime;

        long seconds = (uptimeMillis / 1000) % 60;
        long minutes = (uptimeMillis / (1000 * 60)) % 60;
        long hours = (uptimeMillis / (1000 * 60 * 60)) % 24;
        long days = (uptimeMillis / (1000 * 60 * 60 * 24));

        sender.sendMessage(ChatColor.GOLD + "Uptime: "
                + ChatColor.DARK_AQUA + days + " days, "
                + ChatColor.DARK_AQUA + hours + " hours, "
                + ChatColor.DARK_AQUA + minutes + " minutes, and "
                + ChatColor.DARK_AQUA + seconds + " seconds");

        return true;
    }
}