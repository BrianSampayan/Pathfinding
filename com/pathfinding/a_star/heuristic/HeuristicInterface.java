package com.pathfinding.a_star.heuristic;

public interface HeuristicInterface {
    float getCost(int x, int y, int tX, int tY);
    float getCost(int x, int y, int sX, int sY, int tX, int tY);
}
