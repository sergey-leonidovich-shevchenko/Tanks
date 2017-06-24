package lancer.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* Вспомогательный класс, который будет подгружать файлы с компьютера */
public class ResourceLoader {

    /* Путь где лежат ресурсы */
    public static final String PATH = "res/";

    /**
     * Загружает изображение
     *
     * @param fileName String
     * @return image {@link BufferedImage}
     */
    public static BufferedImage loadImage(String fileName) {

        BufferedImage image = null;

        /* Пытаемся найти наш файл и считать его */
        try {
            image = ImageIO.read(new File(PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
