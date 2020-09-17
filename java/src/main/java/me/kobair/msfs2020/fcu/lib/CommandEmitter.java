package me.kobair.msfs2020.fcu.lib;

import me.kobair.msfs2020.fcu.lib.keyboard.Key;
import me.kobair.msfs2020.fcu.lib.keyboard.KeyMap;
import me.kobair.msfs2020.fcu.lib.keyboard.KeyMapping;
import me.kobair.msfs2020.fcu.lib.keyboard.VirtualKeyboard;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandEmitter {

    private final Map<Command, List<Key>> commandToKeyCombinationsMap;
    private final VirtualKeyboard virtualKeyboard = new VirtualKeyboard();

    public CommandEmitter(final KeyMap keyMap) throws AWTException {
        commandToKeyCombinationsMap = keyMap
                .getKeyMappings()
                .stream()
                .collect(Collectors.toMap(KeyMapping::getCommand, KeyMapping::getKeyCombination));
    }

    public void emitKeyCombinationForCommand(final Command command) {
        if (this.commandToKeyCombinationsMap.containsKey(command)) {
            final List<Key> keyCombination = this.commandToKeyCombinationsMap.get(command);
            virtualKeyboard.simulateKeyCombination(keyCombination);
        }
    }

    public boolean hasKeyMappingForCommand(final Command command) {
        return this.commandToKeyCombinationsMap.containsKey(command);
    }

    public List<Key> keyMappingForCommand(final Command command) {
        return this.commandToKeyCombinationsMap.get(command);
    }
}
