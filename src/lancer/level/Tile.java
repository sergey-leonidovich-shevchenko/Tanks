package lancer.level;

import lancer.graphics.SpriteSheet;
import lancer.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Project name - Tanks
 * Package name - lancer.level
 * Created by Lancer on 025 25.06.17.
 */
public class Tile {

    /**
     * Изображение тайла
     */
    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type) {
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);
    }

    protected void render(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(image, x, y, null);
    }

    protected TileType type() {
        return type;
    }


}
