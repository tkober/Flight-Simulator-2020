package me.kobair.msfs2020.fcu.lib.hardware;

public interface Device {

    final String NEW_LINE_MESSAGE_DELIMITER = "\n";

    boolean isConnected();

    String getName();

    void setCommandHandler(final CommandHandler commandHandler);

    void purgeCommandHandler();

    String getMessageDelimiterString();

    void setMessageDelimiterString(final String messageDelimiter);

    void startReceivingMessages();

    void stopReceivingMessages();
}
