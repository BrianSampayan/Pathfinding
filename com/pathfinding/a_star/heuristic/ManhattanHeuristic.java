package com.pathfinding.a_star.heuristic;

public class ManhattanHeuristic implements HeuristicInterface {
    @Override
    public float getCost(int x, int y, int tX, int tY) {
        float dx = Math.abs(x - tX);
        float dy = Math.abs(y - tY);
        float D = 1.0f;

        return D * (dx + dy);
    }
}
