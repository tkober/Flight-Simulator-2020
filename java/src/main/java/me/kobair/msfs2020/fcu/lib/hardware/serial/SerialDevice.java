package me.kobair.msfs2020.fcu.lib.hardware.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;
import me.kobair.msfs2020.fcu.lib.Command;
import me.kobair.msfs2020.fcu.lib.hardware.CommandHandler;
import me.kobair.msfs2020.fcu.lib.hardware.Device;

public class SerialDevice implements Device, SerialPortMessageListener {

    private final SerialPort serialPort;
    private CommandHandler commandHandler;
    private String messageDelimiter = NEW_LINE_MESSAGE_DELIMITER;

    protected SerialDevice(final SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    protected SerialPort getSerialPort() {
        return this.serialPort;
    }

    @Override
    public String getMessageDelimiterString() {
        return this.messageDelimiter;
    }

    @Override
    public void setMessageDelimiterString(String messageDelimiter) {
        this.messageDelimiter = messageDelimiter;
    }

    @Override
    public boolean isConnected() {
        return this.serialPort.isOpen();
    }

    @Override
    public String getName() {
        return this.serialPort.getPortDescription();
    }

    @Override
    public void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void purgeCommandHandler() {
        this.commandHandler = null;
    }

    @Override
    public void startReceivingMessages() {
        this.serialPort.addDataListener(this);
    }

    @Override
    public void stopReceivingMessages() {
        this.serialPort.removeDataListener();
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public byte[] getMessageDelimiter() {
        return this.messageDelimiter.getBytes();
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        final byte[] data = event.getReceivedData();
        final String text = new String(data);
        final String commandString = text.trim();
        final Command command = Command.valueOf(commandString);

        if (this.commandHandler != null) {
            this.commandHandler.handleCommand(command);
        }
    }

    @Override
    public String toString() {
        return String.format("<SerialDevice@%s connected=%s serialPort=<SerialPort@%s portDescription='%s'>>",
                Integer.toHexString(hashCode()),
                this.isConnected(),
                Integer.toHexString(this.serialPort.hashCode()),
                this.serialPort.getPortDescription());
    }
}
