package com.daxt.core.document;

import com.daxt.factories.core.content.GoalMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DocumentContainerTest {

    DocumentContainer container;

    @Before
    public void setUp() throws Exception {
        this.container = new DocumentContainer() {
        };
    }

    @Test
    public void isComposite() {
        assertTrue(this.container.isComposite());
    }

    @Test
    public void setTitle() {
        // Given
        GoalMessage msg = new GoalMessage("openning", 4, "Ronaldo");
        Sentence title = new Sentence(msg);
        // When
        this.container.setTitle(title);
        // Then
        assertNotNull(this.container.getTitle());
    }

    @Test
    public void setTitleMessage() {
        // Given
        GoalMessage msg = new GoalMessage("openning", 4, "Ronaldo");
        // When
        this.container.setTitleMessage(msg);
        // Then
        assertNotNull(this.container.getTitle());
    }

    @Test
    public void getTitle() {
        // Given
        GoalMessage msg = new GoalMessage("openning", 4, "Ronaldo");
        this.container.setTitleMessage(msg);
        // When
        Sentence title = this.container.getTitle();
        // Then
        assertEquals(msg, title.getMessage());
    }
}