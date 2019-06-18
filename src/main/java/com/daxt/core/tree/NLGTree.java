package com.daxt.core.tree;


import java.util.ArrayList;
import java.util.Iterator;


public abstract class NLGTree<Parent extends NLGTree<Parent>> extends NLGElement<Parent> implements Iterable<NLGTree<Parent>> {

    private ArrayList<NLGElement<Parent>> children = new ArrayList<>();
    public boolean isLeaf() { return children.size() == 0; }

    protected boolean isComposite() { return false; };

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

    @Override
    public Iterator<NLGTree<Parent>> iterator() {
        return null;
    }
}
