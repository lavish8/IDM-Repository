package com.identity.platform.event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.couchbase.client.deps.com.lmax.disruptor.RingBuffer;
import com.couchbase.client.deps.com.lmax.disruptor.dsl.Disruptor;

@Component
public class PlarformEventProducer {

	private final Map<String, Disruptor> distruptor = new HashMap<>();

	public PlarformEventProducer() {
		super();
	}

	public void publishEvent(final Object value, final Class clazz) {

		final RingBuffer<AbstractPlatformEvent> ringBuffer = this.distruptor.get(clazz.getCanonicalName())
				.getRingBuffer();

		final long sequence = ringBuffer.next();
		try {
			final AbstractPlatformEvent event = ringBuffer.get(sequence);
			event.setValue(value);
		} finally {
			ringBuffer.publish(sequence);
		}
	}

	public void addDisruptor(String type, Disruptor disruptor) {
		this.distruptor.put(type, disruptor);
	}

}
