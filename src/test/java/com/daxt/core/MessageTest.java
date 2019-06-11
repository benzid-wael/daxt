package com.daxt.core;

import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class FakeMessage extends Message {
    protected int intField;
    protected String stringField;

    public FakeMessage(String uid, String topic, int intField, String stringField, boolean isDomainKnowledge, boolean alwaysConvey) {
        super(uid, topic, isDomainKnowledge, alwaysConvey);

        this.intField = intField;
        this.stringField = stringField;
    }
}


public class MessageTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MessageTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MessageTest.class );
    }

    public void testIsConveyableReturnsFalseForDomainKnowledgeMessages() throws Exception {
        // Given
        Message testee = new FakeMessage("1", "main", 12, "foo", true, false);

        // When
        boolean actual = testee.isConveyable();

        // Then
        assertThat(actual, is(false));
    }

    public void testIsConveyableReturnsTrueForStandaloneDomainKnowledgeMessages() throws Exception {
        // Given
        Message testee = new FakeMessage("1", "main", 12, "foo", true, true);

        // When
        boolean actual = testee.isConveyable();

        // Then
        assertThat(actual, is(true));
    }

    public void testExport() throws Exception {
        // Given
        Message testee = new FakeMessage("1", "main", 12, "foo", false, false);
        HashMap<String, Object> expected = new HashMap<String, Object>() {{
            put("topic", "main");
            put("intField", 12);
            put("stringField", "foo");
        }};

        // When
        HashMap<String, Object> actual = testee.export();

        // Then
        assertTrue(expected.equals(actual));
    }
}