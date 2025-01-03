package com.justinditer.rpgengine.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
    private int health;
    public Texture texture;

    public Player(String name, Vector2 position, int health, Texture texture){
        super(name, position);
        this.health = health;
        this.texture = texture;
    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Vector2 getMoveTarget(Direction direction) {
        switch (direction) {
            case UP:
                return new Vector2(position.x, position.y + 16);
            case DOWN:
                return new Vector2(position.x, position.y - 16);
            case LEFT:
                return new Vector2(position.x - 16, position.y);
            case RIGHT:
                return new Vector2(position.x + 16, position.y);
            default:
                return position;
        }
    }



}
