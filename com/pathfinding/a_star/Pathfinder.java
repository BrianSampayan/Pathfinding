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
    private static final boolean debugging = false;

    public Pathfinder(byte[] graph, int width, int height, byte[] collideUnits) {
        this(graph, width, height, collideUnits, new ManhattanHeuristic());
    }

    private Pathfinder(byte[] graph, int width, int height, byte[] collideUnits, HeuristicInterface heuristic)
    {
        this.heuristic = heuristic;
        this.graph = new GenericGraph<>(graph, Node.class, width, height);
        this.open = new PriorityQueue<>();
        this.path = new Path();
        this.collideUnits = collideUnits;
    }

    @Override
    public Path findPath(int x, int y, int tX, int tY, boolean breakTies) {
        long startTime, endTime;
        startTime = System.nanoTime();
        this.open.clear();

        com.pathfinding.a_star.Node start = graph.getNode(x, y);
        com.pathfinding.a_star.Node target = graph.getNode(tX, tY);

        if (start != null && target != null)
        {
            if (debugging)
            {
                System.out.println("Starting: " + start);
                System.out.println("Target: " + target);
            }

            start.setG(0);
            start.setF(0);

            this.open.add(start);
            start.setOpened((byte)1);
            while(!this.open.isEmpty())
            {
                com.pathfinding.a_star.Node current = this.open.remove();
                current.setClosed((byte)1);
                if (debugging)
                {
                    System.out.println("Current: " + current);
                }

                if (current.equals(target))
                {
                    if (debugging)
                    {
                        System.out.println("Target Hit");
                    }
                    break;
                }

                ArrayDeque<com.pathfinding.a_star.Node> neighbors = this.graph.getNeighbors(current.getX(), current.getY(), this.collideUnits);
                for (com.pathfinding.a_star.Node neighbor: neighbors)
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
                        if (breakTies)
                        {
                            neighbor.setH(this.heuristic.getCost(neighbor.getX(), neighbor.getY(), x, y, tX, tY));
                        }
                        else
                        {
                            neighbor.setH(this.heuristic.getCost(neighbor.getX(), neighbor.getY(), tX, tY));
                        }
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

            if (target.getParent() != null)
            {
                com.pathfinding.a_star.Node itr = target;
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

    private boolean isWalkable(int x, int y)
    {
        boolean walkable = true;
        if (this.graph.isOutOfBounds(x, y))
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
