package lancer.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {
    private static boolean created = false; // Проверка открыто ли главное окно
    private static JFrame window; // рамка
    private static Canvas content;

    private static BufferedImage bufferedImage;
    private static int[] bufferedData; // массив значений цветов
    private static Graphics bufferedGraphics;
    private static int clearColor;

    // temp
    private static float delta = 0;
    // temp end

    /**
     * Создаем окно
     *
     * @param width      Ширина окна
     * @param height     Высота окна
     * @param title      Название окна
     * @param _clearColor Очищаем изображение
     */
    public static void create(int width, int height, String title, int _clearColor) {

        /* Если окно создано то выходим */
        if (created) {
            return;
        }

        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Закрываем программу при нажатии на крестик
        content = new Canvas();

        Dimension size = new Dimension(width, height); // Создаем размер листа
        content.setPreferredSize(size);

        window.setResizable(false); // Запрещаем пользователю изменять размер окна
        window.getContentPane().add(content); // размещаем всю игру на внутренней части окна (чтоб кнопки закрытия окна и пр. не перекрывали наш контент)
        window.pack(); // изменяем размер нашего окна, чтоб он подходил под размер нашего контента (var content)
        window.setLocationRelativeTo(null); // Выводим окно в центре экрана
        window.setVisible(true); // отображаем наше созданное окно

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // Создаем изображение
        bufferedData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        bufferedGraphics = bufferedImage.getGraphics();
        clearColor = _clearColor;

        created = true;
    }

    /**
     * Очищаем наше изображение
     */
    public static void clear() {
        Arrays.fill(bufferedData, clearColor);
    }

    /**
     * Выводим наше изображение на экран
     */
    public static void render() {
        bufferedGraphics.setColor(new Color(0xff0000ff)); // устанавливаем цвет нашей фигуры
        bufferedGraphics.fillOval((int) (350 + (Math.sin(delta) * 200)), 250, 100, 100); // фигуру делаем овалом диаметром 100 в центре экрана
        delta += 0.02f; // todo [REFACTOR] move to another function

    }

    public static void swapBuffers() {
        Graphics graphics = content.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
    }
}
