package com.justinditer.rpgengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.justinditer.rpgengine.view.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RPGGame extends Game {

    // Main class. Very minimal.

    private SpriteBatch batch;
    private Texture playerTexture;
    private Texture npcTexture;


    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture("placeholder_player.png");
        npcTexture = new Texture("placeholder_npc.png");
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
        if (playerTexture != null) {
            playerTexture.dispose();
        }
        if (npcTexture != null) {
            npcTexture.dispose();
        }
        super.dispose();    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public Texture getNpcTexture() {
        return npcTexture;
    }
}
