package com.nameworkstation.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditModeManager {

    private static final Map<UUID, EditMode> playerModes = new HashMap<>();

    public static void setMode(UUID playerId, EditMode mode) {
        playerModes.put(playerId, mode);
    }

    public static EditMode getMode(UUID playerId) {
        return playerModes.getOrDefault(playerId, EditMode.NAME);
    }

    public static void clear(UUID playerId) {
        playerModes.remove(playerId);
    }

    public static void init() {
        System.out.println("EditModeManager initialized");
    }

    public static void clearAll() {
        playerModes.clear();
        System.out.println("EditModeManager cleared");
    }
}