package lancer.level;

import lancer.game.Game;
import lancer.graphics.TextureAtlas;
import lancer.utils.Utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Project name - Tanks
 * Package name - lancer.level
 * Created by Lancer on 025 25.06.17.
 */
public class Level {


    public static final int TILE_SCALE = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILES_IN_WIDTH = Game.WIDTH / (TILE_SCALE * TILE_IN_GAME_SCALE);
    public static final int TILES_IN_HEIGHT = Game.HEIGHT / (TILE_SCALE * TILE_IN_GAME_SCALE);

    private Integer[][] tileMap;
    private Map<TileType, Tile> tiles;

    public Level(TextureAtlas atlas) {
        tileMap = new Integer[TILES_IN_WIDTH][TILES_IN_HEIGHT];
        tiles = new HashMap<TileType, Tile>();

        tiles.put(TileType.EMPTY, new Tile(
                atlas.cut(36 * TILE_SCALE, 6 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.EMPTY
        ));

        tiles.put(TileType.BRICK, new Tile(
                atlas.cut(32 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.BRICK
        ));

        tiles.put(TileType.METAL, new Tile(
                atlas.cut(32 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.METAL
        ));

        tiles.put(TileType.WEATHER, new Tile(
                atlas.cut(32 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.WEATHER
        ));

        tiles.put(TileType.GRASS, new Tile(
                atlas.cut(34 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.GRASS
        ));

        tiles.put(TileType.ICE, new Tile(
                atlas.cut(36 * TILE_SCALE, 4 * TILE_SCALE, TILE_SCALE, TILE_SCALE),
                TILE_IN_GAME_SCALE,
                TileType.ICE
        ));

        tileMap = Utils.levelParser("res/levels/01.lvl");
    }

    public void update() {

    }

    public void render(Graphics2D graphics2D) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                tiles.get(TileType.fromNumeric(tileMap[i][j])).render(graphics2D, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
            }
        }
    }
}
