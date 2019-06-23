package com.daxt.core.document;

public enum DocumentType {
    /**
     * Definition for a document (root element)
     */
    DOCUMENT,

    /**
     * Definition for a section within a document or section.
     * Sub-sections are simply sections within another section.
     */
    SECTION,

    /**
     * Definition for a paragraph within a document or section.
     */
    PARAGRAPH,

    /**
     * Definition for a sentence within a paragraph.
     */
    SENTENCE,
}
