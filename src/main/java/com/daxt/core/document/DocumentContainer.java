package com.daxt.core.document;


import com.daxt.core.content.Message;


public abstract class DocumentContainer extends DocumentPart {
    private Sentence title;

    @Override
    protected boolean isComposite() {
        return true;
    }

    public void setTitleMessage(Message message) {
        this.title = new Sentence(message);
    }

    public Sentence getTitle() {
        return title;
    }

    public void setTitle(Sentence title) {
        this.title = title;
    }
}
