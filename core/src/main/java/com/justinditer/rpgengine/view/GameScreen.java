package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.justinditer.rpgengine.RPGGame;

public class GameScreen extends ScreenAdapter {
    RPGGame game;
    BitmapFont font;
    Stage stage;

    public GameScreen(RPGGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        stage = new Stage(new FitViewport(800, 600));

        Gdx.input.setInputProcessor(stage);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        Label gameLabel = new Label("Placeholder Game screen !", labelStyle);

        gameLabel.setPosition(400, 400);

        stage.addActor(gameLabel);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
