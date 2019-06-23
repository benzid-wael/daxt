package com.daxt.core.document;

import com.daxt.core.content.Message;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Paragraph extends DocumentPart {
    final static DocumentType type = DocumentType.PARAGRAPH;

    public Paragraph() {
    }

    public Paragraph(ArrayList<Message> messages) {
        messages.stream().forEach(message -> this.addMessage(message));
    }

    public void addMessage(Message message) throws UnsupportedOperationException {
        this.addChild(new Sentence(message));
    }

    public void addSentence(Sentence sentence) {
        this.addChild(sentence);
    }

    public ArrayList<Sentence> getSentences() {
        ArrayList<Sentence> sentences = new ArrayList<>();
        getChildren().forEach(element -> sentences.add((Sentence) element));
        return sentences;
    }

    public String getRealization() {
        return this.getRealization(" ");
    }

    public String getRealization(String delimiter) {
        return getChildren().stream()
                .map(sentence -> ((Sentence) sentence).getRealization())
                .collect(Collectors.joining(delimiter));
    }

    final protected boolean isComposite() {
        return true;
    }
}
