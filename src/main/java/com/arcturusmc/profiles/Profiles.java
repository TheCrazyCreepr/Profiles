package com.arcturusmc.profiles;

import com.arcturusmc.profiles.commands.ProfileCommand;
import com.arcturusmc.profiles.commands.SetBadgeCommand;
import com.arcturusmc.profiles.commands.SetBioCommand;
import com.arcturusmc.profiles.listeners.ConnectionListener;
import com.arcturusmc.profiles.sql.SQLManager;
import com.arcturusmc.profiles.ui.Badges;
import com.arcturusmc.profiles.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class Profiles extends JavaPlugin {

    private SQLManager sqlManager;
    private UserManager userManager;

    @Override
    public void onEnable() {

        sqlManager = new SQLManager();

        userManager = new UserManager();

        try {
            sqlManager.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    private void init() {
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);

        getCommand("profile").setExecutor(new ProfileCommand(this));
        getCommand("setbio").setExecutor(new SetBioCommand(this));
        getCommand("setbadge").setExecutor(new SetBadgeCommand(this));
    }
}
