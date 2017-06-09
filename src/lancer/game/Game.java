package lancer.game;

import lancer.display.Display;
import lancer.utils.Time;

import java.awt.*;

/**
 * Работаем над структурой игры
 */
public class Game implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Tanks";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f; // Сколько раз считаем физику в секунду (кадров в сек)
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; // Время которое должно проходить между каждым обновление физики (кадром в сек)
    public static final long IDLE_TIME = 1; // Останавливаем наш фрейд, чтоб дать другим процессам сработать нормально

    private boolean running; // Проверяем "бежит" ли игра или нет
    private Thread gameThread; // Процесс который запускается дополнительно
    private Graphics2D graphics2D;

    //temp
    float x = 350;
    float y = 250;
    float delta = 0;
    float radius = 50;
    //temp end

    /**
     * Вся логика игры
     */
    public Game() {
        running = false;

        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics2D = Display.getGraphics();
    }

    /**
     * Служит для запуска параллельного потока игры
     */
    public synchronized void start() {

        if (running) {
            return;
        }

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Служит для остановки игры
     */
    public synchronized void stop() {

        if (!running) {
            return;
        }

        running = false;

        try {
            gameThread.join(); // Ждем пока поток закончит свое действие
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    /**
     * Служит для подсчета всех математических расчетов физики игры
     */
    private void update() {
        delta += 0.02F;
    }

    /**
     * После подсчета всей физики (движение, пуль и пр) показываем на экране
     */
    private void render() {
        Display.clear();
        graphics2D.setColor(Color.yellow);
        graphics2D.fillOval((int) (x + Math.sin(delta) * 200), (int) y, (int) radius * 2, (int) radius * 2);
        Display.swapBuffers(); // Мы закончили рисовать нашу сцену, теперь ее нужно отобразить
    }

    /**
     * Будет крутиться "бесконечный" цикл, пока не остановили игру
     */
    public void run() {

        /* Инициализируем служебные счетчики */
        long count = 0; // Время с момента запуска игры
        int countFps = 0; // Сколько кадров в секунду
        int countUpdate = 0; // Сколько сделанно update()
        int countUpdateLoops = 0; // Сколько пытались догнать наш update()


        float delta = 0;

        long lastTime = Time.get(); // Получаем время прошлой итерации (цикла снизу)

        while (running) {
            long now = Time.get(); // Получаем текущее время
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            delta += (elapsedTime / UPDATE_INTERVAL);

            boolean render = false; // Говорим, нужно ли перерисовать наш экран или нет

            while (delta > 1) {
                update();
                countUpdate++;
                delta--;

                if (render) {
                    countUpdateLoops++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                countFps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /* Если прошла одна секунда с момента запуска игры */
            if (count >= Time.SECOND) {
                /* Выводим служебную информауию в заголовок окна */
                Display.setTitle(TITLE + " || Fps: " + countFps + " | Update: " + countUpdate + " | Update loops: " + countUpdateLoops);

                /* Сбрасываем все счетчики */
                count = countUpdate = countFps = countUpdateLoops = 0;
            }
        }
    }

    /**
     * Служит для закрытия ресурсов которых создавали
     */
    private void cleanUp() {
        Display.destroy(); // Уничтожаем окно, после закрытия игры
    }
}
