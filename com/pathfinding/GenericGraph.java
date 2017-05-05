package com.pathfinding;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.concurrent.ExecutionException;

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
        if (this.isInBounds(x, y))
        {
            return (T)Array.get(this.nodes, y * this.height + x);
        }
        return null;
    }

    public ArrayDeque<T> getNeighbors(int x, int y, byte[] collideUnites)
    {
        if (this.isInBounds(x, y))
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
                    if (this.isInBounds(xp, yp))
                    {
                        if (collideUnites != null)
                        {
                            for (byte b : collideUnites)
                            {
                                int index = yp * this.height + xp;
                                if (this.nodes[index].getWalkable() != b)
                                {
                                    neighbors.push(this.nodes[index]);
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

    public boolean isInBounds(int x, int y)
    {
        return ((x >= 0 && x < this.width) && (y >= 0 && y < this.height));
    }

    public boolean updateGraph(byte graph[], Class<T> nodeClass)
    {
        boolean success = true;
        try
        {
            if (this.nodes == null)
            {
                throw new NullPointerException("[Error:GenericGraph:updateGraph]Graph nodes array has not been initialized.");
            }
            for (int j = 0; j < this.height; ++j)
            {
                for (int i = 0; j < this.width; ++i)
                {
                    int index = j * this.height + i;
                    if (!isInBounds(i, j))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Error:GenericGraph:updateGraph]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    if (this.nodes[index] == null)
                    {
                        this.nodes[index] = nodeClass.newInstance();
                        this.nodes[index].setX(i);
                        this.nodes[index].setY(j);
                    }
                    this.nodes[index].setWalkable(graph[index]);
                }
            }
        }
        catch (Exception ex)
        {
            success = false;
            clearGraph();
        }
        return success;
    }

    public boolean updateGraph(byte[] graph, Class<T> nodeClass, int width, int height)
    {
        boolean success = true;
        try
        {
            if (width == this.width && height == this.height)
            {
                this.updateGraph(graph, nodeClass);
            }
            else
            {
                this.width = width;
                this.height = height;
                this.nodes = (T[]) Array.newInstance(nodeClass, this.width * this.height);
                this.populateGraph(graph, nodeClass);
            }
        }
        catch (Exception ex)
        {
            success = false;
            clearGraph();
        }
        return success;
    }

    public void clearGraph()
    {
        try
        {
            for (int j = 0; j < this.height; ++j)
            {
                for (int i = 0; i < this.width; ++i)
                {
                    int index = j * this.height + i;
                    if (!isInBounds(i, j))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Error:GenericGraph:clearGraph]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    this.nodes[index].setX(i);
                    this.nodes[index].setY(j);
                    this.nodes[index].setWalkable((byte)0);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void resetGraph()
    {
        try
        {
            for (int j = 0; j < this.height; ++j)
            {
                for (int i = 0; i < this.width; ++i)
                {
                    int index = j * this.height + i;
                    if (!isInBounds(i, j))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Errpr:GenericGraph:resetGraph]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    this.nodes[index].setParent(null);
                    this.nodes[index].setClosed((byte)0);
                    this.nodes[index].setOpened((byte)0);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void populateGraph(byte graph[], Class<T> nodeClass)
    {
        try
        {
            for (int j = 0; j < this.height; ++j)
            {
                for (int i = 0; i < this.width; ++i)
                {
                    int index = j * this.height + i;
                    if (!isInBounds(i, j))
                    {
                        throw new ArrayIndexOutOfBoundsException("[Error:GenericGraph:populateGraph]Index: " + index + " occurs outside the bounds of the graph.");
                    }
                    this.nodes[index] = nodeClass.newInstance();
                    this.nodes[index].setX(i);
                    this.nodes[index].setY(j);
                    this.nodes[index].setWalkable(graph[index]);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
