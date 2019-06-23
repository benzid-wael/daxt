package com.daxt.core.document;


import com.daxt.core.tree.NLGTree;


public class DocumentPart extends NLGTree<DocumentPart> {
    /* Defines the type of this document part. */
    static DocumentType type;

    protected boolean isComposite() {
        return false;
    }
}
