package com.arcturusmc.profiles.user;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    private HashMap<UUID, User> user = new HashMap<>();

    public User getUser(UUID uuid) {
        return user.get(uuid);
    }

    public void addUser(UUID uuid, User customUser) {
        user.put(uuid, customUser);
    }

    public void removeUser(UUID uuid) {
        user.remove(uuid);
    }
}
