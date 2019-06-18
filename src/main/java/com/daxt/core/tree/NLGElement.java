package com.daxt.core.tree;


public class NLGElement<Parent extends NLGTree<Parent>> {
    private Parent parent;

    public void setParent(Parent parent) { this.parent = parent; }
    public Parent getParent() { return this.parent; }
    public boolean isRoot() { return parent == null; }
    public int getLevel() { return isRoot() ? 0 : parent.getLevel() + 1; }
}
