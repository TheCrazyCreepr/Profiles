package com.arcturusmc.profiles.ui;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Badges {
    VILLAIN(ChatColor.translateAlternateColorCodes('&', "&c&lVILLAIN"),
            "profiles.villain", Material.NETHER_STAR),
    DEV(ChatColor.translateAlternateColorCodes('&', "&2&lDEV"),
            "profiles.dev", Material.REDSTONE),
    ARCTURUS(ChatColor.translateAlternateColorCodes('&', "&c&lARCTURUS STUDIOS"),
            "profiles.arcturus", Material.NETHER_STAR);

    private String display;
    private String permission;
    private Material material;

    Badges(String display, String permission, Material material) {
        this.display = display;
        this.permission = permission;
        this.material = material;
    }

    public String getDisplay() { return display;}
    public String getPermission() { return permission; }
    public Material getMaterial() { return material; }
}
