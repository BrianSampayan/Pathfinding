package com.pathfinding.a_star.heuristic;

public class ManhattanHeuristic implements HeuristicInterface {
    @Override
    public float getCost(int x, int y, int sX, int sY, int tX, int tY) {
        float dx1 = x - tX;
        float dy1 = y - tY;
        float dx2 = sX - tX;
        float dy2 = sY - tY;
        float cross = Math.abs(dx1*dy2 - dx2*dy1);

        float dx = Math.abs(x - tX);
        float dy = Math.abs(y - tY);
        float D = 1.0f;

        float heuristic = D * (dx + dy);


        heuristic *= cross*0.001;

        return heuristic;
    }

    @Override
    public float getCost(int x, int y, int tX, int tY)
    {
        float dx = Math.abs(x - tX);
        float dy = Math.abs(y - tY);
        float D = 1.0f;

        return (D * (dx + dy));
    }
}
