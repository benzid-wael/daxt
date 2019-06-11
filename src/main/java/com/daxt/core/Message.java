package com.daxt.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;


public abstract class Message {

    protected String[] internalFields = new String[] { "internalFields", "logger", "uid", "isKnowledgeBase", "conveyAlways" };
    final static Logger logger = Logger.getLogger(Message.class);
    private String uid;
    private boolean isKnowledgeBase = false;
    private boolean conveyAlways = false;
    private String topic;

    /* Public Constructor */
    public Message(String uid, String topic) {
        this(uid, topic, false, false);
    }

    public Message(String uid, String topic, boolean isKnowledgeBase) {
        this(uid, topic, isKnowledgeBase, false);
    }

    public Message(String uid, String topic, boolean isKnowledgeBase, boolean conveyAlways) {
        this.uid = uid;
        this.topic = topic;
        this.isKnowledgeBase = isKnowledgeBase;
        this.conveyAlways = conveyAlways;
    }

    /* Methods */
    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String getUID() {
        return uid;
    }

    public boolean isDomainKnowledgeMessage() {
        return isKnowledgeBase;
    }

    public boolean isRelevant() {
        return true;
    }

    /* Define whether the message should be conveyed or not. */
    public boolean isConveyable() {
        return (!isKnowledgeBase && this.isRelevant()) || conveyAlways;
    }

    public HashMap<String, Object> export() {
        final HashMap<String, Object> fields = new HashMap<String, Object>();
        Class klass = this.getClass();

        while(klass != null) {
            for (Field field: klass.getDeclaredFields()) {
                String fieldName = field.getName();

                if ( Arrays.stream(this.internalFields).anyMatch(fieldName::equals) )
                    continue;

                field.setAccessible(true);
                try {
                    fields.put(field.getName(), field.get(this));
                } catch (final IllegalAccessException e) {
                    // Log errors here
                }
            }

            klass = klass.getSuperclass();
        }

        return fields;
    }
}
