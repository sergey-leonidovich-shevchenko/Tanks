package lancer.graphics;

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

    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
    }

    public void render(Graphics2D g, float x, float y) {
        BufferedImage image = sheet.getSprite(0);
        g.drawImage(image, (int) (x), (int) (y), (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), null);
    }
}
