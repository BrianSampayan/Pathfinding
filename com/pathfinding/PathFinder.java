package com.pathfinding;

import java.util.HashSet;

public abstract class PathFinder implements PathFinderInterface
{
    private GenericGraph graph;
    private HashSet<Byte> collideUnits;

    @Override
    public Path findPath(int x, int y, int tX, int tY)
    {
        if (isReady())
        {
            this.graph.resetGraph();
            search(x, y, tX, tY);
        }
        return constructPath(this.graph.getNode(x, y), this.graph.getNode(tX, tY));
    }

    abstract void search(int sX, int sY, int tX, int tY);
    abstract boolean isReady();

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
                    if (!this.graph.updateGraph(array, nodeClass))
                    {
                        this.graph = null;
                    }
                }
                else
                {
                    if (!this.graph.updateGraph(array, nodeClass, width, height))
                    {
                        this.graph = null;
                    }
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

    private Path constructPath(GenericNode start, GenericNode target)
    {
        Path path = null;
        if (target.getParent() != null)
        {
            path = new Path();
            GenericNode itr = target;
            while (!itr.equals(start))
            {
                path.pushStep(itr.getX(), itr.getY());
                itr = itr.getParent();
            }
            path.pushStep(start.getX(), start.getY());
        }
        return path;
    }
}
