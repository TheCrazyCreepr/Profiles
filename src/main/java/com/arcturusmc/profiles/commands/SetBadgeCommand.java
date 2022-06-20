package com.arcturusmc.profiles.commands;

import com.arcturusmc.profiles.Profiles;
import com.arcturusmc.profiles.ui.Badges;
import com.arcturusmc.profiles.user.UserManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class SetBadgeCommand implements CommandExecutor {

    private Profiles profiles;
    public SetBadgeCommand(Profiles profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            UserManager userMan = profiles.getUserManager();
            Player player = (Player) sender;

            if(args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please define a badge!");
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("none")) {
                    userMan.getUser(player.getUniqueId()).clearBadge();
                    player.sendMessage(ChatColor.GREEN + "Badge removed.");
                    return true;
                } try {
                    if(player.hasPermission(Badges.valueOf(args[0].toUpperCase()).getPermission())) {
                        userMan.getUser(player.getUniqueId()).setBadge(args[0].toUpperCase());
                        player.sendMessage(ChatColor.GREEN + "Badge set to " + userMan.getUser(player.getUniqueId()).getBadge());
                    } else {
                        player.sendMessage(ChatColor.RED + "Insufficient Permission.");
                    }
                } catch (Exception x) {
                    player.sendMessage(ChatColor.RED + "Error applying new badge. Does this badge exist?");
                }
            }
        }

        return true;
    }
}
