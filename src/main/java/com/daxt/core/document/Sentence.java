package com.daxt.core.document;

import com.daxt.core.content.Message;


public class Sentence extends DocumentPart {
    final static DocumentType type = DocumentType.SENTENCE;
    private Message message;
    private String text = null;

    public Sentence(Message message) {
        this.setMessage(message);
    }

    public Message getMessage() {
        return this.message;
    }

    public void setMessage(Message message) throws NullPointerException {
        if (message == null) {
            throw new NullPointerException("Message is required to define a sentence.");
        } else {
            this.message = message;
        }
    }

    public String getRealization() {
        return text;
    }

    public void setRealization(String text) throws AlreadyRealized {
        if (this.text == null) {
            this.text = text;
        } else {
            throw new AlreadyRealized();
        }
    }

    public boolean generated() {
        return this.text != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sentence) {
            return ((Sentence) obj).message == this.message;
        }
        return false;
    }
}
