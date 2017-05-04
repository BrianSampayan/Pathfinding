package com.pathfinding.bfs;

import com.pathfinding.GenericGraph;
import com.pathfinding.Path;
import com.pathfinding.PathfindingInterface;

import java.util.ArrayDeque;

public class Pathfinder implements PathfindingInterface{
    private ArrayDeque<com.pathfinding.bfs.Node> open;
    private GenericGraph<com.pathfinding.bfs.Node> graph;
    private Path path;
    private byte[] collideUnits;

    public Pathfinder(byte[] graph, int width, int height, byte[] collideUnits)
    {
        this.graph = new GenericGraph<>(graph, com.pathfinding.bfs.Node.class, width, height);
        this.open = new ArrayDeque<>();
        this.path = new Path();
        this.collideUnits = collideUnits;
    }

    public Path findPath(int x, int y, int tX, int tY, boolean breakTies)
    {
        long startTime, endTime;
        startTime = System.nanoTime();
        this.open.clear();

        com.pathfinding.bfs.Node start = this.graph.getNode(x, y);
        com.pathfinding.bfs.Node target = this.graph.getNode(tX, tY);
        if (start != null && target != null)
        {
            this.open.add(start);
            while (!this.open.isEmpty())
            {
                com.pathfinding.bfs.Node current = this.open.remove();

                if (current.equals(target))
                {
                    break;
                }
                ArrayDeque<com.pathfinding.bfs.Node> neighbors = this.graph.getNeighbors(current.getX(), current.getY(), this.collideUnits);
                for (com.pathfinding.bfs.Node neighbor : neighbors)
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
                com.pathfinding.bfs.Node itr = target;
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
