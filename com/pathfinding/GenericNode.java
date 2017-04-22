package com.pathfinding;

public class GenericNode implements NodeInterface
{
    protected int x;
    protected int y;
    private byte walkable;
    private byte opened;
    private byte closed;

    public GenericNode()
    {
        this.x = 0;
        this.y = 0;
        this.walkable = 0;
        this.opened = 0;
        this.closed = 0;
    }

    public GenericNode(int x, int y, byte walkable)
    {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.opened = 0;
        this.closed = 0;
    }

    public void setX(int x)
    {
        this.x = x;
    }
    public int getX()
    {
        return this.x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    public int getY()
    {
        return this.y;
    }

    public void setWalkable(byte walkable)
    {
        this.walkable = walkable;
    }
    public byte getWalkable()
    {
        return this.walkable;
    }

    public void setOpened(byte opened)
    {
        this.opened = opened;
    }
    public byte getOpened()
    {
        return this.opened;
    }

    public void setClosed(byte closed)
    {
        this.closed = closed;
    }
    public byte getClosed()
    {
        return this.closed;
    }
}
