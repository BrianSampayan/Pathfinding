package com.pathfinding;

import java.util.HashSet;

public abstract class PathFinder implements PathFinderInterface
{
    private GenericGraph graph;
    private HashSet<Byte> collideUnits;

    @Override
    public Path findPath(int x, int y, int tX, int tY, boolean breakTies)
    {
        return null;
    }

    public void generateGraphFromArray(byte[] array, Class<GenericNode> nodeClass, int width, int height)
    {

        if (width <= 0 || height <= 0 || array == null)
        {
            this.graph = null;
        }
        else
        {
            if (this.graph == null)
            {
                this.graph = new GenericGraph<>(array, nodeClass, width, height);
            }
            else
            {
                if (this.graph.getWidth() == width && this.graph.getHeight() == height)
                {

                }
            }
        }
    }

    public void generateCollisionUnits(byte[] collideUnits)
    {
        this.collideUnits = new HashSet<>();
        for (byte b : collideUnits)
        {
            this.collideUnits.add(b);
        }
    }

    private boolean isWalkable(int x, int y)
    {
        boolean walkable = true;
        if (this.graph.isInBounds(x, y))
        {
            GenericNode n = graph.getNode(x, y);
            if (this.collideUnits.contains(n.getWalkable()))
            {
                walkable = false;
            }
        }
        else
        {
            walkable = false;
        }
        return walkable;
    }
}
