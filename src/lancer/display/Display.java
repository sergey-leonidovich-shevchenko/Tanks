package lancer.display;

import javax.swing.*;
import java.awt.*;

public abstract class Display {
    private static boolean created = false; // Проверка открыто ли главное окно
    private static JFrame window; // рамка
    private static Canvas content; //

    /** Создаем окно
     *
     * @param width Ширина окна
     * @param height Высота окна
     * @param title Название окна
     */
    public static void create(int width, int height, String title) {

        /* Если окно создано то выходим */
        if (created) {
            return;
        }

        window = new JFrame(title);
        content = new Canvas();

        Dimension size = new Dimension(width, height); // Создаем размер листа
        content.setPreferredSize(size);

        /* Запрещаем пользователю изменять размер окна */
        window.setResizable(false);

        // размещаем всю игру на внутренней части окна (чтоб кнопки закрытия окна и пр. не перекрывали наш контент)
        window.getContentPane().add(content);
        window.pack(); // изменяем размер нашего окна, чтоб он подходил под размер нашего контента (var content)
        window.setVisible(true); // отображаем наше созданное окно



    }
}
