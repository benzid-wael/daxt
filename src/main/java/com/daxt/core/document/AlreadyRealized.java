package com.daxt.core.document;

/**
 * This class represent an exception thrown by the {@link com.daxt.core.document.Sentence} when we try to set the
 * realization for already realized sentences.
 */
public class AlreadyRealized extends Throwable {

    public AlreadyRealized() {
    }

    public AlreadyRealized(String message) {
        super(message);
    }
}
