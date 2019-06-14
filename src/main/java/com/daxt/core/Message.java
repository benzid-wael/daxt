package com.daxt.core;

import java.util.HashMap;


public abstract class Message {
    protected boolean isKnowledgeBase = false;
    protected boolean conveyAlways = false;
    protected String topic;

    /* Public Constructor */
    public Message(String topic) {
        this(topic, false, false);
    }

    public Message(String topic, boolean isKnowledgeBase) {
        this(topic, isKnowledgeBase, false);
    }

    public Message(String topic, boolean isKnowledgeBase, boolean conveyAlways) {
        this.topic = topic;
        this.isKnowledgeBase = isKnowledgeBase;
        this.conveyAlways = conveyAlways;
    }

    /* Methods */
    public String getMessageType() {
        return this.getClass().getSimpleName();
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

    public abstract HashMap<String, Object> export();
}
