package com.arcturusmc.profiles.commands;

import com.arcturusmc.profiles.Profiles;
import com.arcturusmc.profiles.user.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {
    private Profiles profiles;
    public ProfileCommand(Profiles profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        UserManager userMan = profiles.getUserManager();

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(userMan.getUser(player.getUniqueId()).getBio());

                if(userMan.getUser(player.getUniqueId()).getBadge() == null) {
                    player.sendMessage(userMan.getUser(player.getUniqueId()).getBadgeDisplay());
                    player.sendMessage(userMan.getUser(player.getUniqueId()).getBadge());
                }
            }
        }

        return true;
    }
}
