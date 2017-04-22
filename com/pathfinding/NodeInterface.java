package com.pathfinding;

public interface NodeInterface
{
    public void setX(int x);
    public int getX();

    public void setY(int y);
    public int getY();

    public void setWalkable(byte walkable);
    public byte getWalkable();

    public void setOpened(byte opened);
    public byte getOpened();

    public void setClosed(byte closed);
    public byte getClosed();
}
