package me.kobair.msfs2020.fcu.lib.hardware;

public abstract class DeviceManagementException extends Exception {

    public DeviceManagementException(final String message) {
        super(message);
    }

    class DuplicateDeviceException extends DeviceManagementException {

        public DuplicateDeviceException(final String deviceName) {
            super(String.format("A device with the name '%s' is already being managed.", deviceName));
        }
    }

    class IllegalDeviceException extends DeviceManagementException {

        public IllegalDeviceException(final Device device) {
            super(String.format("The device %s is not managed by this DeviceManager.", device.toString()));
        }
    }
}
