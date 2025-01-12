package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.justinditer.rpgengine.RPGGame;

public class MainMenuScreen extends ScreenAdapter {

    // Bare-bones Main menu placeholder.
    // TODO: Continue, New Game, Load, Exit

    RPGGame game;
    BitmapFont font;
    Stage mainStage;

    public MainMenuScreen(RPGGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        mainStage = new Stage(new FitViewport(640, 480));

        Gdx.input.setInputProcessor(mainStage);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        Label titleLabel = new Label("Placeholder title !", labelStyle);
        Label interactLabel = new Label("Default: Press Enter to Continue", labelStyle);

        titleLabel.setPosition(200, 400);
        interactLabel.setPosition(200, 200);

        mainStage.addActor(titleLabel);
        mainStage.addActor(interactLabel);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.act(delta);
        mainStage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_ENTER)) {
            game.setScreen(new GameScreen(game));
        }
    }
    @Override
    public void dispose() {
        mainStage.dispose();
    }
}
