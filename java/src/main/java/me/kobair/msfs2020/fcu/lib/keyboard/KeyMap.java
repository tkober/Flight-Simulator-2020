package me.kobair.msfs2020.fcu.lib.keyboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.kobair.msfs2020.fcu.lib.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyMap {

    private String name;
    private String description;
    private List<KeyMapping> keyMappings = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<KeyMapping> getKeyMappings() {
        return keyMappings;
    }

    public void addKeyMapping(final Command command, final Key... keys) {
        this.addKeyMapping(command, Arrays.asList(keys));
    }

    public void addKeyMapping(final Command command, final List<Key> keys) {
        final KeyMapping keyMapping = new KeyMapping(command, keys);
        this.keyMappings.add(keyMapping);
    }

    public void save(final File file) throws IOException {
        Writer writer = new FileWriter(file.getAbsolutePath());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(this, writer);
        writer.close();
    }

    public static KeyMap load(final File file) throws FileNotFoundException {
        final JsonReader reader = new JsonReader(new FileReader(file.getAbsolutePath()));
        final KeyMap result = new Gson().fromJson(reader, KeyMap.class);
        return result;
    }
}
