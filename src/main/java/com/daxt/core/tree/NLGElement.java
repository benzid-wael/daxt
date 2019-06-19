package com.daxt.core.tree;


/**
 * This parameterized class extends the type parameter with hierarchical grouping capabilities,
 * by allowing it to be child or parent of other nodes with the same type.
 *
 * @param <Parent> type parameter
 */
public class NLGElement<Parent extends NLGTree<Parent>> {
    private Parent parent;

    public Parent getParent() { return this.parent; }

    /**
     * Updates the parent pointer to point to the given parent.
     *
     * @param parent
     */
    public void setParent(Parent parent) { this.parent = parent; }

    /**
     * Returns True iff this node is the root node in the tree.
     *
     * @return
     */
    public boolean isRoot() { return parent == null; }


    /**
     * Returns the root of this tree.
     *
     * @return Tree's root.
     */
    public NLGElement<Parent> root() {
        NLGElement<Parent> root = getParent();
        while(root.getParent() != null) {
            root = root.getParent();
        }

        return root;
    }

    /**
     * Returns the level of this node in the tree.
     *
     * @return node's level.
     */
    public int getLevel() { return isRoot() ? 0 : parent.getLevel() + 1; }
}
