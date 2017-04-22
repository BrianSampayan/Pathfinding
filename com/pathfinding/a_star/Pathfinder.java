package com.pathfinding.a_star;

import com.pathfinding.GenericGraph;
import com.pathfinding.Path;
import com.pathfinding.PathfindingInterface;
import com.pathfinding.a_star.heuristic.HeuristicInterface;
import com.pathfinding.a_star.heuristic.ManhattanHeuristic;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Pathfinder implements PathfindingInterface {
    private PriorityQueue<Node> open;
    private GenericGraph<Node> graph;
    private HeuristicInterface heuristic;
    private Path path;
    private byte[] collideUnits;
    private int width;
    private int height;

    public Pathfinder(byte[] graph, int width, int height, byte[] collideUnits)
    {
        this(graph, width, height, collideUnits, new ManhattanHeuristic());
    }

    private Pathfinder(byte[] graph, int width, int height, byte[] collideUnits, HeuristicInterface heuristic)
    {
        this.width = width;
        this.height = height;
        this.heuristic = heuristic;
        this.graph = new GenericGraph<>(graph, Node.class, width, height);
        this.open = new PriorityQueue<>();
        this.path = new Path();
        this.collideUnits = collideUnits;
    }

    @Override
    public Path findPath(int x, int y, int tX, int tY) {
        open.clear();

        Node start = graph.getNode(x, y);
        Node target = graph.getNode(tX, tY);
        start.setG(0);
        start.setF(0);

        this.open.add(start);
        start.setOpened((byte)1);
        while(!this.open.isEmpty())
        {
            Node current = this.open.remove();
            current.setClosed((byte)1);

            if (current.equals(target))
            {
                break;
            }

            ArrayDeque<Node> neighbors = this.graph.getNeighbors(current.getX(), current.getY());
            for (Node neighbor: neighbors)
            {
                if (neighbor.getClosed() > 0)
                {
                    continue;
                }
                if (!this.isWalkable(neighbor.getX(), neighbor.getY()))
                {
                    continue;
                }

                float ng = current.getG() + 1;
                if (neighbor.getOpened() == 0 || ng < neighbor.getG())
                {
                    neighbor.setG(ng);
                    neighbor.setH(this.heuristic.getCost(neighbor.getX(), neighbor.getY(), tX, tY));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    neighbor.setParent(current);

                    if (neighbor.getOpened() == 0)
                    {
                        this.open.add(neighbor);
                        neighbor.setOpened((byte)1);
                    }
                }
            }
        }

        if (target.getParent() == null)
        {
            return null;
        }

        Node itr = target;
        while (!itr.equals(start))
        {
            this.path.pushStep(itr.getX(), itr.getY());
            itr = itr.getParent();
        }

        return this.path;
    }

    private boolean isWalkable(int x, int y)
    {
        boolean walkable = true;
        if (!this.graph.isOutOfBounds(x, y))
        {
            Node n = this.graph.getNode(x, y);
            for (byte b : collideUnits)
            {
                if (b == n.getWalkable())
                {
                    walkable = false;
                }
            }
        }
        else
        {
            walkable = false;
        }
        return walkable;
    }
}
