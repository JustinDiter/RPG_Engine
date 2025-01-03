package com.justinditer.rpgengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.justinditer.rpgengine.view.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RPGGame extends Game {
    private SpriteBatch batch;
    private Texture playerTexture;


    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture("placeholder_player.png");
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose(); // Dispose the current screen
        }
        if (batch != null) {
            batch.dispose();
        }
        if (playerTexture != null) {
            playerTexture.dispose();
        }
        super.dispose();    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }
}
