package com.daxt.factories.core.content;

import java.util.HashMap;

import com.daxt.core.content.Message;
import com.daxt.core.content.Priority;


public class FakeMessage extends Message {
    private int intField;
    private String stringField;

    public FakeMessage(String topic, int intField, String stringField, boolean isDomainKnowledge, boolean alwaysConvey) {
        super(topic, isDomainKnowledge, alwaysConvey);

        this.intField = intField;
        this.stringField = stringField;
    }

    public Priority getPriority() {
        return Priority.LOW;
    }

    public HashMap<String, Object> export() {
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("topic", this.getTopic());
        hmap.put("intField", this.intField);
        hmap.put("stringField", this.stringField);

        return hmap;
    }
}
