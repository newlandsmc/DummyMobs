package com.semivanilla.dummymobs;

import org.jetbrains.annotations.NotNull;

public enum ConfigPath {
    HOLO_OFFSET_X("hologram-offset.x"),
    HOLO_OFFSET_Y("hologram-offset.y"),
    HOLO_OFFSET_Z("hologram-offset.z")
    ;

    private String path;

    ConfigPath(String path) {
        this.path = path;
    }

    public String path(){
        return path;
    }

    public static String getConfigPathOf(@NotNull ConfigPath configPath){
        return configPath.path;
    }
}
