package com.daxt.core.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public abstract class Message {
    private boolean isKnowledgeBase;
    private boolean conveyAlways;
    private String topic;

    /* Constructors */
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

    /* Public Methods */
    public String getTopic() {
        return this.topic;
    }

    public String getType() {
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

    ArrayList<Information> toList() {
        ArrayList<Information> informations = new ArrayList<>();
        HashMap<String, Object> serializedMessage = this.export();
        Iterator entries = serializedMessage.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) entries.next();
            String name = (String) entry.getKey();
            if (name.equals("topic"))
                continue;

            Object value = entry.getValue();
            informations.add(new Information(this.topic, name, value));
        }

        return informations;
    }

    /*
     * Define message's priority.
     */
    public abstract Priority getPriority();

    public abstract HashMap<String, Object> export();
}
