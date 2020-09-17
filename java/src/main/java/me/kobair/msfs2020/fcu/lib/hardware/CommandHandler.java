package me.kobair.msfs2020.fcu.lib.hardware;

import me.kobair.msfs2020.fcu.lib.Command;

@FunctionalInterface
public interface CommandHandler {

    void handleCommand(final Command command);

}
