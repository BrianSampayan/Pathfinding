package com.pathfinding;

public class Step {
    private int x;
    private int y;

    Step(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public boolean equals(Object object)
    {
        if (object instanceof Step)
        {
            Step p = (Step)object;

            return ((p.getX() == this.x) && (p.getY() == this.y));
        }
        return false;
    }
}
