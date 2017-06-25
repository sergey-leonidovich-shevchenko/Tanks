package lancer.level;

/**
 * Project name - Tanks
 * Package name - lancer.level
 * Created by Lancer on 025 25.06.17.
 */
public enum TileType {
    EMPTY(0),
    BRICK(1),
    METAL(2),
    WEATHER(3),
    GRASS(4),
    ICE(5);

    private int n;

    TileType(int n) {
        this.n = n;
    }

    public int numeric() {
        return n;
    }

    public static TileType fromNumeric(int n) {
        switch (n) {
            case 1:
                return BRICK;
            case 2:
                return METAL;
            case 3:
                return WEATHER;
            case 4:
                return GRASS;
            case 5:
                return ICE;
            default:
                return EMPTY;
        }
    }
}
