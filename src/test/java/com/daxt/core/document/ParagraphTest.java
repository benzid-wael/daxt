package com.daxt.core.document;

import com.daxt.factories.core.content.GoalMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ParagraphTest {

    GoalMessage message;

    @Before
    public void setUp() throws Exception {
        this.message = new GoalMessage("openning", 5, "Ronaldo");
    }

    @Test
    public void isLeaf() {
        // Given
        Paragraph p = new Paragraph();
        p.addMessage(this.message);
        // When / Then
        assertFalse(p.isLeaf());
    }

    @Test
    public void isComposite() {
        // Given
        Paragraph p = new Paragraph();
        p.addMessage(this.message);
        // When / Then
        assertTrue(p.isComposite());
    }

    @Test
    public void addMessage() {
        // Given
        Paragraph p = new Paragraph();
        // When
        p.addMessage(this.message);
        // Then
        assertEquals(1, p.getChildren().size());
    }

    @Test
    public void getSentences() {
        // Given
        Paragraph p = new Paragraph();
        Sentence expected = new Sentence(this.message);
        // When
        p.addMessage(this.message);
        // Then
        assertEquals(1, p.getSentences().size());
        assertEquals(expected, p.getSentences().get(0));
    }

    @Test
    public void getRealization() throws AlreadyRealized {
        // Given
        String text1 = "Renaldo opened the scoring in the 5th minute.";
        String text2 = "One minute later, Zineddine Zidane replied from a direct kick from outside the box.";
        String expected = text1 + " " + text2;
        Paragraph p = new Paragraph();
        GoalMessage msg1 = new GoalMessage("openning", 5, "Ronaldo");
        Sentence sent1 = new Sentence(msg1);
        sent1.setRealization(text1);
        GoalMessage msg2 = new GoalMessage("draw", 6, "Zidane");
        Sentence sent2 = new Sentence(msg2);
        sent2.setRealization(text2);
        p.addSentence(sent1);
        p.addSentence(sent2);
        // When
        String actual = p.getRealization();
        // Then
        assertEquals(expected, actual);
    }
}