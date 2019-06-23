package com.daxt.core.document;

import com.daxt.factories.core.content.GoalMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SentenceTest {

    private GoalMessage message;
    private Sentence sentence;

    @Before
    public void setUp() throws Exception {
        this.message = new GoalMessage("first-goal", 23, "Ronaldo");
        this.sentence = new Sentence(this.message);
    }

    @Test
    public void getType() {
        // Given
        GoalMessage message = new GoalMessage("first-goal", 23, "Ronaldo");
        Sentence testee = new Sentence(message);
        // When
        DocumentType actual = Sentence.type;
        // Then
        assertEquals(DocumentType.SENTENCE, actual);
    }

    @Test
    public void isLeaf() {
        assertTrue(this.sentence.isLeaf());
    }

    @Test
    public void isComposite() {
        assertFalse(this.sentence.isComposite());
    }

    @Test(expected = NullPointerException.class)
    public void setMessageThrowsExceptionWhenCalledWithNull() {
        // When
        this.sentence.setMessage(null);
    }

    @Test
    public void getMessage() {
        assertEquals(this.message, this.sentence.getMessage());
    }

    @Test
    public void getRealization() throws AlreadyRealized {
        // Given
        String expected = "Ronaldo scored hist first goal in the 23rd minute.";
        this.sentence.setRealization(expected);
        // When
        String actual = this.sentence.getRealization();
        // Then
        assertEquals(expected, actual);

    }

    @Test(expected = AlreadyRealized.class)
    public void setRealization_cannotChangeTheRealizion_onceAlreadyRealized() throws AlreadyRealized {
        // Given
        this.sentence.setRealization("Ronaldo scored hist first goal in the 23rd minute.");
        // When
        this.sentence.setRealization("Ronaldo opened the score in the 23rd minute.");
    }

    @Test
    public void generated() throws AlreadyRealized {
        // Given
        this.sentence.setRealization("For the 2nd time in this season, Ronaldo manage to open the score within the first 10 minutes.");
        // When / Then
        assertTrue(this.sentence.generated());
    }
}