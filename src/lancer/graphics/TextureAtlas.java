package lancer.graphics;

import lancer.utils.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Project name - Tanks
 * Package name - graphics
 *
 * @author Lancer on 024 24.06.17.
 */
public class TextureAtlas {
    /**
     * Изображение атласа игры
     */
    BufferedImage image;

    /**
     * Загружаем картинку
     *
     * @param imageName String Название картинки
     */
    public TextureAtlas(String imageName) {
        image = ResourceLoader.loadImage(imageName);
    }

    /**
     * Нарезаем из атласа отдельные изображения
     *
     * @param x int Координата откуда стартовать резке
     * @param y int Координата откуда стартовать резке
     * @param w int Ширина вырезаемой картинки
     * @param h int Высота вырезаемой картинки
     * @return BufferedImage Объект вырезанного изображения
     */
    public BufferedImage cut(int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }
}
