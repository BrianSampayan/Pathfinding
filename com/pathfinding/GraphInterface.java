package com.pathfinding;

import java.util.ArrayDeque;

public interface GraphInterface <T extends NodeInterface>
{
    NodeInterface getNode(int x, int y);

    ArrayDeque<T> getNeighbors(int x, int y, byte[] collideUnits);
}
