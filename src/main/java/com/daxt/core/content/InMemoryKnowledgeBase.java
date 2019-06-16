package com.daxt.core.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


public class InMemoryKnowledgeBase implements KnowledgeBase {
    private HashMap<String, ArrayList<Information>> dataset = new HashMap<String, ArrayList<Information>>() {{
        put(GENERAL_TOPIC_IDENTIFIER, new ArrayList<>());
    }};

    public void addInformation(Information information) {
        ArrayList<Information> topicInfo;
        String topic = information.isGeneral() ? GENERAL_TOPIC_IDENTIFIER : information.getTopic();

        if (this.dataset.containsKey(topic)) {
            topicInfo = this.dataset.get(topic);
            topicInfo.add(information);
        } else {
            topicInfo = new ArrayList<>();
            topicInfo.add(information);
        }

        this.dataset.put(topic, topicInfo);
    }

    public boolean hasInformationAboutTopic(String topic) {
        return this.dataset.containsKey(topic);
    }

    /**
     * Returns information identified by the given name, and defined in the given topic.
     *
     * @param name  information identifier
     * @param topic topic identifier
     * @return Information
     */
    public Information getInformation(String name, String topic) {
        String topicKey = topic == null ? GENERAL_TOPIC_IDENTIFIER : topic;
        if (!this.dataset.containsKey(topicKey)) {
            return null;
        }

        // FIXME We should either forbid collecting duplicate information or implement strategy pattern here
        //  (first-in, priority, etc) or while adding information.
        Optional<Information> info = this.dataset
                .get(topicKey)
                .stream()
                .filter(information -> information.getName().equals(name))
                .findFirst();

        return info.isPresent() ? info.get() : null;
    }

    public ArrayList<Information> getTopicInformation(String topic) {
        ArrayList<Information> result = this.dataset.get(GENERAL_TOPIC_IDENTIFIER);

        if (this.dataset.containsKey(topic)) {
            result.addAll(this.dataset.get(topic));
        }

        return result;
    }
}