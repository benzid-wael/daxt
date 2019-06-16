package com.daxt.core.content;


import java.util.ArrayList;


interface KnowledgeBase {

    String GENERAL_TOPIC_IDENTIFIER = "__GENERAL__";

    /**
     * Load initial domain knowledge dataset.
     */
    default void load() {
    }

    /**
     * Extend the knowledge base with the given message.
     *
     * @param message message object to be added
     */
    default void addMessage(Message message) {
        message.toList().forEach(information -> this.addInformation(information));
    }

    /**
     * Extend the knowledge base with the given information.
     *
     * @param information Information object to be added
     */
    void addInformation(Information information);

    /**
     * Determine whether we have information about the given topic or not.
     *
     * @param topic topic identifier
     */
    boolean hasInformationAboutTopic(String topic);

    /**
     * Returns relevant information for the given topic.
     *
     * @param topic topic identifier
     */
    ArrayList<Information> getTopicInformation(String topic);

    /**
     * Returns information identified by the given name, and defined in the given topic.
     *
     * @param name  information identifier
     * @param topic topic identifier
     * @return Information
     */
    Information getInformation(String name, String topic);

    /**
     * Returns information identified by the given name.
     *
     * @param name information identifier
     * @return Information
     */
    default Information getInformation(String name) {
        return this.getInformation(name, GENERAL_TOPIC_IDENTIFIER);
    }
}