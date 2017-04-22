package com.pathfinding;

import java.util.ArrayDeque;

public class Path {
    private ArrayDeque<Step> steps;

    public Path()
    {
        this.steps = new ArrayDeque<>();
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
}
