package com.pathfinding;

import java.util.ArrayDeque;

public class GenericGraph implements GraphInterface
{
    private int width;
    private int height;

    private NodeInterface nodes[];

    public GenericGraph(byte graph[], int width, int height)
    {
        this.width = width;
        this.height = height;
        
    }
}
