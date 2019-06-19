package com.daxt.core.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


abstract class Shape extends NLGTree<Shape> {
    private String uid;

    protected boolean isComposite() { return false; }
    public abstract void draw(String fillColor);

    public Shape(String uid) { this.uid = uid; }
}

class Triangle extends Shape {

    public Triangle(String uid) { super(uid); }

    public void draw(String fillColor) {
        System.out.println("Drawing triangle with color: " + fillColor);
    }
}

class Circle extends Shape {

    public Circle(String uid) { super(uid); }

    public void draw(String fillColor) {
        System.out.println("Drawing circle with color: " + fillColor);
    }
}

class Drawing extends Shape {
    final protected boolean isComposite() { return true; }

    public Drawing(String uid) { super(uid); }

    @Override
    public void draw(String fillColor) {
        for(NLGElement<Shape> sh : this.getChildren())
        {
            ((Shape) sh).draw(fillColor);
        }
    }
}



public class TestNLGTree {

    private Drawing oneLevelDrawingTree;
    private Triangle t1, t2, t3;
    private Circle c1, c2;

    @Before
    public void setUp() throws Exception {
        this.t1 = new Triangle("t1");
        this.t2 = new Triangle("t2");
        this.t3 = new Triangle("t2");
        this.c1 = new Circle("t1");
        this.c2 = new Circle("t2");

        this.oneLevelDrawingTree = new Drawing("d1");
        this.oneLevelDrawingTree.addChild(this.t1);
        this.oneLevelDrawingTree.addChild(this.c1);
        this.oneLevelDrawingTree.addChild(this.t2);
    }

    @Test
    public void createSimpleTreeWithCompositePattern() throws Exception {
        Drawing drawing = new Drawing("d1");
        Triangle child1 = new Triangle("t1");
        Triangle child2 = new Triangle("t2");
        Circle child3 = new Circle("c1");

        drawing.addChild(child1);
        drawing.addChild(child2);
        drawing.addChild(child3);

        assertEquals(drawing.getChild(0), child1);
        assertTrue(drawing.isRoot());
    }

    @Test
    public void isRootReturnsTrueWhenElementDoesNotHaveAnyParent() {
        // Given
        Shape element = this.oneLevelDrawingTree;
        // when
        boolean actual = element.isRoot();
        // Then
        assertTrue(actual);
    }

    @Test
    public void isRootReturnsFalseWhenCalledWithLeaf() {
        assertFalse(this.t1.isRoot() && this.t1.getParent() != null);
        assertFalse(this.t2.isRoot() && this.t2.getParent() != null);
        assertFalse(this.c1.isRoot() && this.c1.getParent() != null);
    }

    @Test
    public void isLeafReturnsTrueWhenElementHasParent() {
        assertTrue(this.t1.isLeaf() && this.t1.getParent() != null);
        assertTrue(this.t2.isLeaf() && this.t2.getParent() != null);
        assertTrue(this.c1.isLeaf() && this.c1.getParent() != null);
    }

    @Test
    public void addChildSucceedWhenCalledWithCompositeElement() {
        // Given
        Shape testee = this.oneLevelDrawingTree;
        Shape element = this.t3;

        // when
        testee.addChild(element);

        // Then
        assertEquals(testee, element.getParent());
    }

    @Test
    public void getLevelReturnsZeroWhenCalledWithRootElement() {
        // Given
        Shape testee = this.oneLevelDrawingTree;

        // when
        int actual = testee.getLevel();

        // Then
        assertEquals(0, actual);
    }

    @Test
    public void getLevelWhenCalledWithOneLevelTree() {
        assertEquals(1, this.t1.getLevel());
        assertEquals(1, this.t2.getLevel());
        assertEquals(1, this.c1.getLevel());
    }

    @Test
    public void getLevelReturnsCorrectLevelWhenCalledWithMultiLevelTree() {
        // Given
        Drawing d2 = new Drawing("d2");
        d2.addChild(this.t3);
        d2.addChild(this.oneLevelDrawingTree);

        // When / Then
        assertEquals(1, this.t3.getLevel());
        assertEquals(1, this.oneLevelDrawingTree.getLevel());
        assertEquals(2, this.t1.getLevel());
        assertEquals(2, this.t2.getLevel());
        assertEquals(2, this.c1.getLevel());
    }

    @Test
    public void lengthReturnsTwoWhenCalledWithOneLevelTree() {
        // Given
        Shape testee = this.oneLevelDrawingTree;
        // When
        int actual = testee.length();
        // Then
        assertEquals(3, actual);
    }

    @Test
    public void heightReturnsOneWhenCalledWithEmptyTree() {
        // Given
        Shape testee = new Drawing("temp");
        // When
        int actual = testee.height();
        // Then
        assertEquals(1, actual);
    }

    @Test
    public void heightReturnsTwoWhenCalledWithOneLevelTree() {
        // Given
        Shape testee = this.oneLevelDrawingTree;
        // When
        int actual = testee.height();
        // Then
        assertEquals(2, actual);
    }

    @Test
    public void rootReturnsRootElementWhenFromLeaves() {
        assertEquals(this.oneLevelDrawingTree, this.t1.root());
        assertEquals(this.oneLevelDrawingTree, this.t2.root());
        assertEquals(this.oneLevelDrawingTree, this.c1.root());
    }

    @Test
    public void leavesRemovesNonLeafNodes() {
        // given
        ArrayList<NLGElement<Shape>> expected = new ArrayList<>();
        expected.add(this.t1);
        expected.add(this.c1);
        expected.add(this.t2);
        // When
        ArrayList<NLGElement<Shape>> actual = this.oneLevelDrawingTree.leaves();
        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void leavesRetutnsEmptyListWhenCalledWithEmptyTree() {
        // Given
        Drawing testee = new Drawing("empty");
        // When
        ArrayList<NLGElement<Shape>> actual = testee.leaves();
        // Then
        assertTrue(actual.size() == 0);
    }
}

