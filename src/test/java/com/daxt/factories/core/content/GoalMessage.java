package com.daxt.factories.core.content;

import com.daxt.core.content.Message;
import com.daxt.core.content.Priority;

import java.util.HashMap;


public class GoalMessage extends Message {

    private int minute;
    private String player;

    public GoalMessage(String topic, int minute, String player) {
        super(topic);
        this.minute = minute;
        this.player = player;
    }

    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public HashMap<String, Object> export() {
        HashMap<String, Object> hmap = new HashMap<>();
        hmap.put("minute", this.minute);
        hmap.put("player", this.player);
        return hmap;
    }
}
