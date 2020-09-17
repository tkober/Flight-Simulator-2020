package me.kobair.msfs2020.fcu.lib.hardware;

import java.util.List;

public interface DeviceManager {

    Device discoverDevice(final String name) throws DeviceManagementException.DuplicateDeviceException;

    List<Device> getManagedDevices();

    Device getManagedDevice(final String name);

    void connectDevice(final Device device) throws DeviceManagementException.IllegalDeviceException;

    void disconnectDevice(final Device device) throws DeviceManagementException.IllegalDeviceException;

    void disconnectAllDevices();

}
