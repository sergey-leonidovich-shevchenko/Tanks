package lancer.game;

import lancer.IO.Input;

import java.awt.*;

/**
 * Общий класс для всех сущностей в игре
 * Project name - Tanks
 * Package name - lancer.game
 * Created by Lancer on 024 24.06.17.
 */
public abstract class Entity {
    public final EntityType type;

    // Координаты на которой находится сущность
    protected float x;
    protected float y;

    /**
     * @param type {@link EntityType}
     * @param x    float Координаты на которой находится сущность
     * @param y    float Координата Y на которой находится сущность
     */
    Entity(EntityType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * Обновление ввода с клавиатуры
     *
     * @param input {@link Input}
     */
    public abstract void update(Input input);

    /**
     * Обновление отображения
     *
     * @param graphics2D {@link Graphics2D}
     */
    public abstract void render(Graphics2D graphics2D);
}
