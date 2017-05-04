package com.pathfinding;

import java.util.ArrayDeque;

public class Path {
    private ArrayDeque<Step> steps;
    private long creationTime;

    public Path()
    {
        this.steps = new ArrayDeque<>();
        this.creationTime = 0;
    }

    public Step popStep()
    {
        return steps.pop();
    }

    public boolean isEmpty()
    {
        return steps.isEmpty();
    }

    public void pushStep(int x, int y)
    {
        steps.push(new Step(x, y));
    }

    public void clearPath()
    {
        this.steps.clear();
    }

    public long getCreationTime()
    {
        return this.creationTime;
    }

    public void setCreationTime(long creationTime)
    {
        this.creationTime = creationTime;
    }

    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Step step : steps)
        {
            result.append(step + "\n");
        }
        return result.toString();
    }
}
