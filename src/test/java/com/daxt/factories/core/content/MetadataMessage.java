package com.daxt.factories.core.content;

import com.daxt.core.content.Message;
import com.daxt.core.content.Priority;

import java.util.HashMap;


public class MetadataMessage extends Message {
    private int counter;

    public MetadataMessage(String topic, int counter, boolean isDomainKnowledge, boolean alwaysConvey) {
        super(topic, isDomainKnowledge, alwaysConvey);
        this.counter = counter;
    }

    public Priority getPriority() {
        return Priority.LOW;
    }

    public HashMap<String, Object> export() {
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("topic", this.getTopic());
        hmap.put("counter", this.counter);

        return hmap;
    }
}
