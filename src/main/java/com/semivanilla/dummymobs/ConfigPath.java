package com.semivanilla.dummymobs;

import org.jetbrains.annotations.NotNull;

public enum ConfigPath {
    HOLO_OFFSET_X("hologram-max-offset.x"),
    HOLO_OFFSET_Y("hologram-max-offset.y"),
    HOLO_OFFSET_Z("hologram-max-offset.z")
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
