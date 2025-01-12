package com.justinditer.rpgengine.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NPC extends Entity{

    private String dialogue;
    public Texture texture;

    public NPC(String name, Vector2 position, String dialogue, Texture texture) {
        super(name, position);
        this.dialogue = dialogue;
        this.texture = texture;
    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public String getDialogue() {
        return this.dialogue;
    }

    @Override
    public void dispose() {

    }
}
