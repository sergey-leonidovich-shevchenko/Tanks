package lancer.graphics;

import java.awt.image.BufferedImage;

/**
 * Будет держать маленький кусок изображения и отвечать за его анимацию
 * Project name - Tanks
 * Package name - lancer.game
 * Created by Lancer on 024 24.06.17.
 */
public class SpriteSheet {
    /* Кусок изображения, отрезанного от атласа, содержащий несколько спрайтов */
    private BufferedImage sheet;

    /* Количество спрайтов */
    private int spriteCount;

    /* Размер одного спрайта */
    private int scale;

    /* Количество спрайтов держащий в ширину */
    private int spritesInWidth;

    /**
     * @param sheet       int Кусок изображения, отрезанного от атласа, содержащий несколько спрайтов
     * @param spriteCount int Количество спрайтов
     * @param scale       int Размер одного спрайта
     */
    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale) {
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;
        this.spritesInWidth = sheet.getWidth() / scale;
    }

    /**
     * Получаем спрайт по его индексу (начинаю с 0)
     *
     * @param index Индекс спрайта
     * @return BufferedImage Изображение спрайта
     */
    public BufferedImage getSprite(int index) {
        index = index % spriteCount;

        int x = index % spritesInWidth * scale;
        int y = index / spritesInWidth * scale;

        return sheet.getSubimage(x, y, scale, scale);
    }
}
