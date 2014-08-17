package com.si.ha.events;

public class EventBus {

	private static com.google.common.eventbus.EventBus bus;

	static {
		bus = new com.google.common.eventbus.EventBus();
	}

	public static void register(Object subscriber) {
		bus.register(subscriber);
	}

	public static void post(Object event) {
		bus.post(event);
	}

	public static void unregister(Object subscriber) {
		bus.unregister(subscriber);
	}
}
