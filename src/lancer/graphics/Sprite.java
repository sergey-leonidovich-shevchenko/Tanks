package lancer.graphics;

import lancer.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Project name - Tanks
 * Package name - lancer.game
 * Created by Lancer on 024 24.06.17.
 */
public class Sprite {
    /**
     * Спрайт
     */
    private SpriteSheet sheet; // Вырезанное изображение, содержащее несколько спрайтов

    /**
     * Маштаб рисуемого спрайта на экране
     */
    private float scale;

    private BufferedImage image;

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        image = sheet.getSprite(0);
        image = Utils.resize(image, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale));
    }

    public void render(Graphics2D g, float x, float y) {
        g.drawImage(image, (int) (x), (int) (y), null);
    }
}
