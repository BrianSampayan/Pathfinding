package com.pathfinding.bfs;

import com.pathfinding.GenericGraph;
import com.pathfinding.GenericNode;
import com.pathfinding.Path;

import java.util.ArrayDeque;

public class Pathfinder implements com.pathfinding.PathFinderInterface
{
    private ArrayDeque<GenericNode> open;
    private GenericGraph<GenericNode> graph;
    private Path path;
    private byte[] collideUnits;

    public Pathfinder(byte[] graph, int width, int height, byte[] collideUnits)
    {
        this.graph = new GenericGraph<>(graph, GenericNode.class, width, height);
        this.open = new ArrayDeque<>();
        this.path = new Path();
        this.collideUnits = collideUnits;
    }

    public Path findPath(int x, int y, int tX, int tY)
    {
        long startTime, endTime;
        startTime = System.nanoTime();
        this.open.clear();

        GenericNode start = this.graph.getNode(x, y);
        GenericNode target = this.graph.getNode(tX, tY);
        if (start != null && target != null)
        {
            this.open.add(start);
            while (!this.open.isEmpty())
            {
                GenericNode current = this.open.remove();

                if (current.equals(target))
                {
                    break;
                }
                ArrayDeque<GenericNode> neighbors = this.graph.getNeighbors(current.getX(), current.getY(), this.collideUnits);
                for (GenericNode neighbor : neighbors)
                {
                    if (neighbor.getOpened() == 0)
                    {
                        neighbor.setParent(current);
                        this.open.add(neighbor);
                        neighbor.setOpened((byte)1);
                    }
                }
            }
            if (target.getParent() != null)
            {
                GenericNode itr = target;
                while (!itr.equals(start))
                {
                    this.path.pushStep(itr.getX(), itr.getY());
                    itr = itr.getParent();
                }
                this.path.pushStep(start.getX(), start.getY());

                endTime = System.nanoTime();
                this.path.setCreationTime(endTime - startTime);
                return this.path;
            }
        }
        return null;
    }
}
