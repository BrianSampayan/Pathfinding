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

            return ((n.getX() == this.x) && (n.getY() == this.y));
        }
        return false;
    }

    void setF(float f)
    {
        this.f = f;
    }
    float getF()
    {
        return this.f;
    }

    void setG(float g) {
        this.g = g;
    }
    float getG() {
        return g;
    }

    void setH(float h) {
        this.h = h;
    }
    float getH() {
        return h;
    }

    void setParent(Node parent) {
        this.parent = parent;
    }
    Node getParent() {
        return parent;
    }
}
