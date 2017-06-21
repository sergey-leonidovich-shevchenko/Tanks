package lancer.display;

import lancer.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {
    private static boolean created = false; // Проверка открыто ли главное окно
    private static JFrame window; // рамка
    private static Canvas content;

    private static BufferedImage bufferedImage;
    private static int[] bufferedData; // Массив значений цветов
    private static Graphics bufferedGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy; // Для имплементпции наших buffer`ов

    // temp
//    private static float delta = 0;
    // temp end

    /**
     * Создаем окно
     *
     * @param width       Ширина окна
     * @param height      Высота окна
     * @param title       Название окна
     * @param _clearColor Очищаем изображение
     * @param numBuffers  Сколько buffer`ов будем имплементировать
     */
    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

        /* Если окно создано то выходим */
        if (created) {
            return;
        }

        window = new JFrame(title); // Создаем фрейм
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Закрываем программу при нажатии на крестик
        content = new Canvas(); // Создаем контент типа Canvas

        Dimension size = new Dimension(width, height); // Создаем размер листа
        content.setPreferredSize(size); // Задаем размер

        window.setResizable(false); // Запрещаем пользователю изменять размер окна
        window.getContentPane().add(content); // Размещаем всю игру на внутренней части окна (чтоб кнопки закрытия окна и пр. не перекрывали наш контент)
        window.pack(); // Изменяем размер нашего окна, чтоб он подходил под размер нашего контента (var content)
        window.setLocationRelativeTo(null); // Выводим окно в центре экрана
        window.setVisible(true); // Отображаем наше созданное окно

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // Создаем изображение
        bufferedData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        bufferedGraphics = bufferedImage.getGraphics();
        ((Graphics2D) bufferedGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Включаем сглаживание
        clearColor = _clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    /**
     * Очищаем наше изображение
     */
    public static void clear() {
        Arrays.fill(bufferedData, clearColor);
    }

    /**
     * Вывод изображения на экран
     */
//    public static void render() {
//        bufferedGraphics.setColor(new Color(0xff0000ff)); // Устанавливаем цвет нашей фигуры
//        bufferedGraphics.fillOval((int) (350 + (Math.sin(delta) * 200)), 250, 100, 100); // Фигуру делаем овалом диаметром 100 в центре экрана
//
//        /* Добавляем сглаживание краев (Anti Aliasing)*/
//        ((Graphics2D) bufferedGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        delta += 0.02f; // todo [REFACTOR] move to another function
//    }

    public static void swapBuffers() {
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        bufferStrategy.show();
    }

    /**
     * Возвращаем объект нашей картинки, для изменения графики из вне
     */
    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferedGraphics;
    }

    /**
     * Уничтожаем окно
     */
    public static void destroy() {
        if (!created) {
            return;
        }

        window.dispose();
    }

    /**
     * Изменяем название нашего окна
     */
    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void addInputListener(Input inputListener) {
        window.add(inputListener);
    }
}
