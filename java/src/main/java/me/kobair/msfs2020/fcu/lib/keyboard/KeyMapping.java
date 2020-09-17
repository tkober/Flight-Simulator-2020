package me.kobair.msfs2020.fcu.lib.keyboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.kobair.msfs2020.fcu.lib.Command;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class KeyMapping {

    private Command command;
    private List<Key> keyCombination;

    public KeyMapping() {
        super();
    }

    public KeyMapping(final Command command, final List<Key> keyCombination) {
        this.command = command;
        this.keyCombination = keyCombination;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<Key> getKeyCombination() {
        return keyCombination;
    }

    public void setKeyCombination(final Key... keys) {
        this.setKeyCombination(Arrays.asList(keys));
    }

    public void setKeyCombination(List<Key> keyCombination) {
        this.keyCombination = keyCombination;
    }
}
