package lancer.main;


import lancer.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main(String[] args) {
        Display.create(800, 600, "Tanks", 0xff000000);

        Timer timer = new Timer(1000 / 60, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                Display.clear(); // очищаем буфер с изображением
                Display.render();
                Display.swapBuffers(); // Вставляем новое изображение
            }

        });

        timer.setRepeats(true);
        timer.start();
    }

}
