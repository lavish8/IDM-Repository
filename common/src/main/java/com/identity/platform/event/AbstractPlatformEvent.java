package com.identity.platform.event;

import com.couchbase.client.deps.com.lmax.disruptor.EventFactory;

public abstract class AbstractPlatformEvent {

    private Object value;
    public abstract AbstractPlatformEvent newInstanceEvent();
    
    public final EventFactory<AbstractPlatformEvent> eventFactory =
            new EventFactory<AbstractPlatformEvent>() {
                @Override
                public AbstractPlatformEvent newInstance() {
                    return AbstractPlatformEvent.this.newInstanceEvent();
                }
    };

    public Object getValue() {
        return this.value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }    
}
