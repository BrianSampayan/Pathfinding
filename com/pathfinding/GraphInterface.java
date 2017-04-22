package com.pathfinding;

import java.util.ArrayDeque;

public interface GraphInterface
{
    NodeInterface getNode(int x, int y);

    ArrayDeque<NodeInterface> getNeighbors(int x, int y);
}
