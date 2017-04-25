package com.pathfinding.bfs;

import com.pathfinding.GenericNode;

public class Node extends GenericNode
{
    private Node parent;

    public Node()
    {
        super(0, 0, (byte)0);
    }

    public Node(int x, int y, byte walkable)
    {
        super(x, y, walkable);
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public Node getParent()
    {
        return this.parent;
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
}
