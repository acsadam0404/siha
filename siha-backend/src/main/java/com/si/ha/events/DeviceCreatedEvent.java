package com.si.ha.events;

import com.si.ha.device.Device;

public final class DeviceCreatedEvent {
	private Device device;

	public DeviceCreatedEvent(Device device) {
		this.device = device;
	}

	public Device getDevice() {
		return device;
	}

}
