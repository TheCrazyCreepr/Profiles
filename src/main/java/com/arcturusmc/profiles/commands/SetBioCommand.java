package com.arcturusmc.profiles.commands;

import com.arcturusmc.profiles.Profiles;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetBioCommand implements CommandExecutor {
    private Profiles profiles;
    public SetBioCommand(Profiles profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length >= 1) {
                StringBuilder messageMaker = new StringBuilder();
                for(int i = 0; i < args.length; i++) {
                    messageMaker.append(args[i]).append(" ");
                }

                String bio = messageMaker.toString();
                String strippedBio = ChatColor.stripColor(bio);

                if(strippedBio.length() > 35) {
                    player.sendMessage(ChatColor.RED + "Message cannot be longer than 35 characters.");
                    return true;
                }

                if(player.hasPermission("profile.colorbio")) {

                    String finalBio = ChatColor.translateAlternateColorCodes('&', bio);
                    profiles.getUserManager().getUser(player.getUniqueId()).setBio(finalBio);
                } else {
                    String finalBio = ChatColor.GOLD + strippedBio;
                    profiles.getUserManager().getUser(player.getUniqueId()).setBio(finalBio);
                }

                player.sendMessage(ChatColor.GREEN + "Bio updated! New bio: " + profiles.getUserManager().getUser(player.getUniqueId()).getBio());
            }
        }
        return true;
    }
}