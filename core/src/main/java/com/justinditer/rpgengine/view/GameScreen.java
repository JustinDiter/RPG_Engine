package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.justinditer.rpgengine.RPGGame;

public class GameScreen extends ScreenAdapter {
    RPGGame game;
    BitmapFont font;
    TmxMapLoader mapLoader;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;

    public GameScreen(RPGGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        // Map setup
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("testMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);

        // Camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
    @Override
    public void dispose() {
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
