package com.semivanilla.dummymobs.interfaces;

import de.leonhard.storage.internal.FlatFile;

public interface PluginFiles {

    long reload();

    void loadConfiguration();

    default boolean validateConfiguration(){
        return true;
    }

}
