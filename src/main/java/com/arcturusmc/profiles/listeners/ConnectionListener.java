package com.arcturusmc.profiles.listeners;

import com.arcturusmc.profiles.Profiles;
import com.arcturusmc.profiles.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private Profiles profiles;
    public ConnectionListener(Profiles profiles) {
        this.profiles = profiles;
    }

    @EventHandler
    public void playerConnect(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        profiles.getSqlManager().reopenConnection();

        try {
            User user = new User(profiles, player.getUniqueId());
            profiles.getUserManager().addUser(player.getUniqueId(), user);
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        profiles.getUserManager().removeUser(player.getUniqueId());
    }
}
