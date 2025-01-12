package com.justinditer.rpgengine.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    // Straightforward, typical Entity abstract class.
    public Vector2 position;
    public String name;

    public Entity(String name, Vector2 position) {
        this.name = name;
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 newPosition) {
        this.position.set(newPosition);
    }

    public abstract void update(float deltatime);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
