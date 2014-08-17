package com.si.ha.events;

class EventBus {

	private static com.google.common.eventbus.EventBus bus

	static {
		bus = new com.google.common.eventbus.EventBus()
	}

	static void register(Object subscriber) {
		bus.register(subscriber);
	}

	static void post(Object event) {
		bus.post(event);
	}

	static void unregister(Object subscriber) {
		bus.unregister(subscriber);
	}
	
	private EventBus() {
		/* utility */
	}
}
