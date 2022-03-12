package com.semivanilla.dummymobs.abstracts;

import com.semivanilla.dummymobs.DummyMobs;
import de.leonhard.storage.internal.FlatFile;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public abstract class AbstractConfig {

    protected final DummyMobs plugin;
    protected FlatFile file;

    public AbstractConfig(DummyMobs plugin) {
        this.plugin = plugin;
    }

    public abstract boolean initConfig();

    public FlatFile getFile() {
        return file;
    }

    public boolean isFileInitialized(){
        return file != null;
    }

    public InputStream getResource(@NotNull String fileName){
        return plugin.getResource(fileName);
    }

    public void flushData(){
        this.file.getFileData().clear();
    }
}
