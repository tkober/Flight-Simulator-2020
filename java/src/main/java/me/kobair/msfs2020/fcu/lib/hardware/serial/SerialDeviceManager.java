package me.kobair.msfs2020.fcu.lib.hardware.serial;

import com.fazecast.jSerialComm.SerialPort;
import me.kobair.msfs2020.fcu.lib.hardware.Device;
import me.kobair.msfs2020.fcu.lib.hardware.DeviceManagementException;
import me.kobair.msfs2020.fcu.lib.hardware.DeviceManager;

import java.util.*;

public class SerialDeviceManager implements DeviceManager {

    public static final String DEVICE_NAME_ARDUINO_MEGA_2560 = "Arduino Mega 2560";

    private final Map<String, SerialDevice> managedDevices = new HashMap<>();

    @Override
    public Device discoverDevice(String name) throws DeviceManagementException.DuplicateDeviceException {
        if (this.managedDevices.containsKey(name)) {
            throw new DeviceManagementException.DuplicateDeviceException(name);
        }

        Optional<SerialPort> serialPort = findSerialPort(name);
        if (serialPort.isPresent()) {
            SerialDevice serialDevice = new SerialDevice(serialPort.get());
            this.managedDevices.put(name, serialDevice);
            return serialDevice;
        } else {
            return null;
        }
    }

    @Override
    public List<Device> getManagedDevices() {
        return new ArrayList<>(this.managedDevices.values());
    }

    @Override
    public Device getManagedDevice(String name) {
        return this.managedDevices.get(name);
    }

    @Override
    public boolean connectDevice(Device device) throws DeviceManagementException.UnmanagedDeviceException {
        if (!this.managedDevices.containsValue(device)) {
            throw new DeviceManagementException.UnmanagedDeviceException(device);
        }

        SerialDevice serialDevice = (SerialDevice) device;
        return serialDevice.getSerialPort().openPort();
    }

    @Override
    public boolean disconnectDevice(Device device) throws DeviceManagementException.UnmanagedDeviceException {
        if (!this.managedDevices.containsValue(device)) {
            throw new DeviceManagementException.UnmanagedDeviceException(device);
        }

        SerialDevice serialDevice = (SerialDevice) device;
        return serialDevice.getSerialPort().openPort();
    }

    @Override
    public void disconnectAllDevices() {
        for (Device device : this.managedDevices.values()) {
            try {
                this.disconnectDevice(device);
            } catch (DeviceManagementException.UnmanagedDeviceException e) {
                throw new RuntimeException("This should never happen", e);
            }
        }
    }

    private Optional<SerialPort> findSerialPort(final String portDesciption) {
        final List<SerialPort> serialPorts = Arrays.asList(SerialPort.getCommPorts());
        final Optional<SerialPort> result = serialPorts
                .stream()
                .filter(s -> s.getPortDescription().equals(portDesciption))
                .findFirst();
        return result;
    }
}
