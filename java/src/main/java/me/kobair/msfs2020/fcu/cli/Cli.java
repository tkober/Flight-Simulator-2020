package me.kobair.msfs2020.fcu.cli;

import me.kobair.msfs2020.fcu.lib.Command;
import me.kobair.msfs2020.fcu.lib.CommandEmitter;
import me.kobair.msfs2020.fcu.lib.hardware.CommandHandler;
import me.kobair.msfs2020.fcu.lib.hardware.Device;
import me.kobair.msfs2020.fcu.lib.hardware.DeviceManager;
import me.kobair.msfs2020.fcu.lib.hardware.serial.SerialDeviceManager;
import me.kobair.msfs2020.fcu.lib.keyboard.Key;
import me.kobair.msfs2020.fcu.lib.keyboard.KeyMap;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Cli {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing parameter: Key map file");
            System.exit(1);
        }

        final File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println(String.format("No such file or directory: '%s'", file.getAbsolutePath()));
            System.exit(2);
        }

        if (file.isDirectory()) {
            System.err.println(String.format("Expected file but found a directory instead: '%s'", file.getAbsolutePath()));
            System.exit(3);
        }

        System.out.println(String.format("Loading key map file '%s'", file));
        try {
            final KeyMap keyMap = KeyMap.load(file);
            final CommandEmitter commandEmitter = new CommandEmitter(keyMap);

            final String deviceName = SerialDeviceManager.DEVICE_NAME_ARDUINO_MEGA_2560;
            System.out.println(String.format("Discovering device '%s'", deviceName));
            final DeviceManager deviceManager = new SerialDeviceManager();
            final Device device = deviceManager.discoverDevice(deviceName);

            if (device == null) {
                System.err.println(String.format("Device not found: '%s'", deviceName));
                System.exit(4);
            }

            System.out.println(String.format("Connecting device <%s>", device.getName()));
            if (!deviceManager.connectDevice(device)) {
                System.err.println(String.format("Failed to connect device <%s>", device.getName()));
                System.exit(5);
            }
            System.out.println(String.format("Device <%s> connected", device.getName()));

            device.setCommandHandler(new CommandHandler() {
                @Override
                public void handleCommand(Command command) {
                    if (!commandEmitter.hasKeyMappingForCommand(command)) {
                        System.err.println(String.format("No key mapping defined for command '%s'", command));
                        return;
                    }
                    List<Key> keys = commandEmitter.keyMappingForCommand(command);
                    if (keys.size() == 0) {
                        System.err.println(String.format("Empty key mapping defined for command '%s'", command));
                        return;
                    }
                    final String combinationText = keys.size() > 1 ? " combination" : "";
                    final String keysText = keys.stream()
                            .map(key -> "'"+key+"'")
                            .collect(Collectors.joining("+"));
                    System.out.println(String.format("Received command '%s'. Emitting key%s %s", command, combinationText, keysText));
                    commandEmitter.emitKeyCombinationForCommand(command);
                }
            });
            System.out.println(String.format("Start reception of messages for device <%s>", device.getName()));
            device.startReceivingMessages();
            System.out.println("Listening...");

            AtomicBoolean shutdown = new AtomicBoolean(false);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown.set(true)));
            while(!shutdown.get()) {}
            System.out.println("Shutting down...");

            System.out.println("Stopping reception of messages");
            device.stopReceivingMessages();

            System.out.println("Disconnecting all devices");
            deviceManager.disconnectAllDevices();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
