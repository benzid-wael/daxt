package com.daxt.core.tree;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Abstract class for representing a hierarchy of NLG elements that can grouped together for any specific task.
 * This class is needed for two main functions: document planning and surface realization, as both of them deals with
 * hierarchical elements.
 *
 * NLGTree represents a hierarchical group of leaves and subtrees. For example, each constituent in a syntax tree
 * {@link com.daxt.core.surface.SyntaxTree} is represented by a single tree.
 *
 * NLGTree children are encoded as a list of leaves and subtrees. Both of them are instance of
 * {@link com.daxt.core.tree.NLGElement}\<Parent\>, where leaf is a basic node; and a subtree is a nested tree.
 *
 * Leaf and Trees are identified by `isComposite` method, which is implementer responsibility.
 * See {@link com.daxt.core.tree.NLGTree}
 *
 * @param <Parent>
 */
public abstract class NLGTree<Parent extends NLGTree<Parent>> extends NLGElement<Parent> implements Iterable<NLGTree<Parent>> {

    private ArrayList<NLGElement<Parent>> children = new ArrayList<>();

    public boolean isLeaf() { return children.size() == 0; }

    protected boolean isComposite() {
        return false;
    }

    public ArrayList<NLGElement<Parent>> getChildren() {
        return this.children;
    }

    public void addChild(NLGElement<Parent> child) throws UnsupportedOperationException {
        if(this.isComposite()) {
            child.setParent((Parent) this);
            children.add(child);
        } else {
            throw new UnsupportedOperationException("Leaf nodes cannot have any element.");
        }
    }

    public NLGElement<Parent> getChild(int index) {
        return children.size() >= index ? children.get(index) : null;
    }

    /**
     * Returns the length of the tree.
     *
     * @return Length of the tree
     */
    public int length() { return children.size(); }

    /**
     * Returns the height of the tree.
     *
     * A height of a tree containing no children is 1; the height of the tree containing only leaves is 2;
     * and the height of any other tree is one plus the maximum of it's children height.
     *
     * @return Height of the tree
     */
    public int height() {
        try {
            int maxChildHeight = children.stream()
                    .map(child -> ((NLGTree<Parent>) child).height())
                    .max((x, y) -> Integer.compare(x, y))
                    .get();
            return maxChildHeight + 1;
        } catch (NoSuchElementException err) {
            return 1;
        }
    }

    /**
     * Return list containing leaves of the tree.
     *
     * @return tree's leaves in the same order in the hierarchical structure - parsed from left to right.
     */
    public ArrayList<NLGElement<Parent>> leaves() {
        ArrayList<NLGElement<Parent>> result = new ArrayList<>();
        for(NLGElement<Parent> child: children) {
            if(((Parent) child).isLeaf()) {
                result.add(child);
            } else if (((Parent) child).length() > 0) {
                result.addAll(((Parent) child).leaves());
            }
        }
        return result;
    }

    @Override
    public Iterator<NLGTree<Parent>> iterator() {
        return null;
    }
}
