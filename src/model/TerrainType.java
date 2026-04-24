package model;

public enum TerrainType {
    WALL(999),
    GRASS(1),
    SAND(3),
    WATER(5);

    int cost;

    TerrainType(int cost) {
        this.cost = cost;
    }
}
