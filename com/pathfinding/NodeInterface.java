package com.pathfinding;

public interface NodeInterface
{
    void setX(int x);
    int getX();

    void setY(int y);
    int getY();

    void setWalkable(byte walkable);
    byte getWalkable();

    void setOpened(byte opened);
    byte getOpened();

    void setClosed(byte closed);
    byte getClosed();
}
