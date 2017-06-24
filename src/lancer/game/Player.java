package lancer.game;

import lancer.IO.Input;
import lancer.graphics.Sprite;
import lancer.graphics.SpriteSheet;
import lancer.graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Project name - Tanks
 * Package name - lancer.game
 * Created by Lancer on 024 24.06.17.
 */
public class Player extends Entity {

    /* Размер квадратного спрайта в px */
    public static final int SPRITE_SCALE = 16;

    /* Количество спрайтов (для анимации) когда сущность движется в одном направлении */
    public static final int SPRITES_PER_HEADING = 1;

    /* Перечисления - в какие стороны может смотреть сущность */
    private enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE), // Вверх
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),  // Вправо
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE), // Вниз
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),; // Влево

        private int x, y, h, w;

        /**
         * Определяем нахождение на атласе нужного спрайта
         *
         * @param x int Координата X верхнего угла спрайта
         * @param y int Координата Y верхнего угла спрайта
         * @param w int Ширина спрайта
         * @param h int Высота спрайта
         */
        Heading(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        /**
         * Вырезаем спрайт из изображения
         *
         * @param atlas {@link TextureAtlas}
         * @return BufferedImage
         */
        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    /* В какую сторону смотрит сущность */
    private Heading heading;

    /* Вытаскиваем нужный спрайт в зависимости от направления сущности */
    private Map<Heading, Sprite> spriteMap;

    /* С каким размером мы хотим отображать нашу сущность */
    private float scale;

    /* С какой скоростью движется сущность в одном направлении */
    private float speed;

    /**
     * @param x     float Координата X на которой находится сущность
     * @param y     float Координата Y на которой находится сущность
     * @param scale float Масштаб спрайта
     * @param speed int Скорость передвижения
     * @param atlas {@link TextureAtlas} Атлас наших текстур
     */
    public Player(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.Player, x, y);

        // По умолчанию смотрит вверх
        heading = Heading.NORTH;

        // На каждую сторону, куда может смотреть сущность,
        // мы хотим сохранять отдельный спрайт
        spriteMap = new HashMap<Heading, Sprite>();

        this.scale = scale;
        this.speed = speed;

        /*  */
        for (Heading h : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(h, sprite); // Связываем каждое направление со своим спрайтом
        }
    }

    /**
     * @param input {@link Input}
     */
    @Override
    public void update(Input input) {
        float newX = x;
        float newY = y;

        /* События при нажатии определенной клавиши */
        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
            heading = Heading.NORTH;

        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
            heading = Heading.SOUTH;

        } else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.WEST;

        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.EAST;
        }

        /* Запрещаем сущности выходить за экран */
        if (newX < 0) {
            newX = 0;
        } else if (newX > Game.WIDTH - SPRITE_SCALE * scale) {
            newX = Game.WIDTH - SPRITE_SCALE * scale;
        }

        /* Запрещаем сущности выходить за экран */
        if (newY < 0) {
            newY = 0;
        } else if (newY > Game.HEIGHT - SPRITE_SCALE * scale) {
            newY = Game.HEIGHT - SPRITE_SCALE * scale;
        }

        x = newX;
        y = newY;
    }

    /**
     * Отображаем сушность в правильном направлении и правильном положении на экране
     *
     * @param graphics2D {@link Graphics2D}
     */
    @Override
    public void render(Graphics2D graphics2D) {
        spriteMap.get(heading).render(graphics2D, x, y);
    }
}
