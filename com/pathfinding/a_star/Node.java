package com.pathfinding.a_star;

import com.pathfinding.GenericNode;

public class Node extends GenericNode implements Comparable {
    private float f, g, h;
    private Node parent;

    public Node(int x, int y, byte walkable)
    {
        super(x, y, walkable);
    }

    @Override
    public int compareTo(Object o)
    {
        Node n = (Node)o;
        int result = 0;
        if (this.f < n.getF())
        {
            result = -1;
        }
        else if (this.f > n.getF())
        {
            result = 1;
        }
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Node)
        {
            Node n = (Node)o;

            return ((n.getX() == this.getX()) && (n.getY() == this.getY()));
        }
        return false;
    }

    public void setF(float f)
    {
        this.f = f;
    }
    public float getF()
    {
        return this.f;
    }

    public void setG(float g) {
        this.g = g;
    }
    public float getG() {
        return g;
    }

    public void setH(float h) {
        this.h = h;
    }
    public float getH() {
        return h;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent() {
        return parent;
    }
}
