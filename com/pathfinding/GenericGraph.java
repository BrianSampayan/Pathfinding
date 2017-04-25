package com.pathfinding;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class GenericGraph <T extends GenericNode> implements GraphInterface<T>
{
    private int width;
    private int height;

    private T nodes[];

    public GenericGraph(byte graph[], Class<T> nodeClass, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.nodes = (T[]) Array.newInstance(nodeClass, this.width * this.height);
        this.populateGraph(graph, nodeClass);
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public T getNode(int x, int y)
    {
        if (this.isOutOfBounds(x, y))
        {
            return (T)Array.get(this.nodes, y * this.width + x);
        }
        return null;
    }

    public ArrayDeque<T> getNeighbors(int x, int y, byte[] collideUnites)
    {
        if (this.isOutOfBounds(x, y))
        {
            ArrayDeque<T> neighbors = new ArrayDeque<>();
            for (int i = -1; i < 2; ++i)
            {
                for (int j = -1; j < 2; ++j)
                {
                    if (i == 0 && j == 0)
                    {
                        continue;
                    }
                    if (i != 0 && j != 0)
                    {
                        continue;
                    }

                    int xp = i + x;
                    int yp = j + y;
                    if (this.isOutOfBounds(xp, yp))
                    {
                        if (collideUnites != null)
                        {
                            for (byte b : collideUnites)
                            {
                                if (this.nodes[yp * this.width + xp].getWalkable() != b)
                                {
                                    neighbors.push(this.nodes[yp * this.width + xp]);
                                }
                            }
                        }
                    }
                }
            }
            return neighbors;
        }
        return null;
    }

    public boolean isOutOfBounds(int x, int y)
    {
        return ((x >= 0 && x < this.width) && (y >= 0 && y < this.height));
    }

    private void populateGraph(byte graph[], Class<T> nodeClass)
    {
        try
        {
            for (int j = 0; j < this.height; ++j)
            {
                for (int i = 0; i < this.width; ++i)
                {
                    this.nodes[j * this.width + i] = nodeClass.newInstance();
                    this.nodes[j * this.width + i].setX(i);
                    this.nodes[j * this.width + i].setY(j);
                    this.nodes[j * this.width + i].setWalkable(graph[j * this.width + i]);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


}
