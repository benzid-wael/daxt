package com.daxt.core.content;

import com.daxt.factories.core.content.FakeMessage;
import com.daxt.factories.core.content.MetadataMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class InMemoryKnowledgeBaseTest {

    private InMemoryKnowledgeBase emptyKnowledgeBase;
    private InMemoryKnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        // mock empty domain knowledge
        this.emptyKnowledgeBase = new InMemoryKnowledgeBase();

        // mock knowledge base with initial messages
        this.knowledgeBase = new InMemoryKnowledgeBase();
        FakeMessage fakeMessage = new FakeMessage("foo", 12, "bar", true, false);
        this.knowledgeBase.addMessage(fakeMessage);
        fakeMessage = new FakeMessage("tic", 10, "tac", true, false);
        this.knowledgeBase.addMessage(fakeMessage);
        fakeMessage = new FakeMessage("tac", 8, "toe", true, false);
        this.knowledgeBase.addMessage(fakeMessage);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addMessage_fillEmptyDomainKnowledge() {
        // Given
        String topic = "foo";
        FakeMessage fakeMessage = new FakeMessage(topic, 12, "bar", true, false);
        // When
        this.emptyKnowledgeBase.addMessage(fakeMessage);
        // Then
        assertTrue(this.emptyKnowledgeBase.hasInformationAboutTopic(topic));
    }

    @Test
    public void addMessage_extendKnowledgeBaseWithGlobalInformation() {
        // Given
        String topic = "foo";
        ArrayList<Information> expected = new ArrayList<Information>() {{
            add(new Information(null, "counter", 12));
            add(new Information("foo", "intField", 12));
            add(new Information("foo", "stringField", "bar"));
        }};
        MetadataMessage globalMessage = new MetadataMessage(null, 12, true, false);
        this.emptyKnowledgeBase.addMessage(globalMessage);
        FakeMessage fakeMessage = new FakeMessage(topic, 12, "bar", true, false);
        // When
        this.emptyKnowledgeBase.addMessage(fakeMessage);
        ArrayList<Information> actual = this.emptyKnowledgeBase.getTopicInformation(topic);
        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void addMessage_extendDomainKnowledge_whenItHasInformationAboutTopic() {
        // Given
        String topic = "foo";
        FakeMessage fakeMessage = new FakeMessage(topic, 100, "bar", true, false);
        int currentInformationSize = this.knowledgeBase.getTopicInformation(topic).size();
        // When
        this.knowledgeBase.addMessage(fakeMessage);
        int actual = this.knowledgeBase.getTopicInformation(topic).size();
        // Then
        assertTrue((actual > currentInformationSize));
    }

    @Test
    public void hasInformationAboutTopic_returnsTrueAfterAddingMessage() {
        // Given
        String topic = "foo";
        InMemoryKnowledgeBase testee = new InMemoryKnowledgeBase();
        FakeMessage fakeMessage = new FakeMessage(topic, 12, "bar", true, false);
        // When
        testee.addMessage(fakeMessage);
        // Then
        assertTrue(testee.hasInformationAboutTopic(topic));
    }

    @Test
    public void getTopicInformation_returnsAllInformationDefinedInTheAddedMessage() throws Exception {
        // Given
        String topic = "foo";
        InMemoryKnowledgeBase testee = new InMemoryKnowledgeBase();
        FakeMessage fakeMessage = new FakeMessage(topic, 12, "bar", true, false);
        ArrayList<Information> expectedInformation = new ArrayList<Information>() {{
            add(new Information("foo", "intField", 12));
            add(new Information("foo", "stringField", "bar"));
        }};
        testee.addMessage(fakeMessage);
        // When
        ArrayList<Information> actualInformation = testee.getTopicInformation(topic);
        // Then
        assertEquals(expectedInformation, actualInformation);
    }

    @Test
    public void getInformation_returnsAlwaysFirstFoundInformation() {
        // Given
        String topic = "foo";
        FakeMessage fakeMessage = new FakeMessage(topic, 100, "newValue", true, false);
        this.knowledgeBase.addMessage(fakeMessage);
        // When
        Information actual = this.knowledgeBase.getInformation("stringField", topic);
        // Then
        assertNotSame("newValue", actual.getValue());
    }
}