package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.justinditer.rpgengine.RPGGame;
import com.justinditer.rpgengine.controller.GameController;
import com.justinditer.rpgengine.model.MapManager;
import com.justinditer.rpgengine.model.Player;

public class GameScreen extends ScreenAdapter {
    RPGGame game;
    TmxMapLoader mapLoader;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    private Player player;
    private MapManager mapManager;
    private GameController gameController;

    public GameScreen(RPGGame game) {
        this.game = game;
        this.player = new Player("Guy", new Vector2(32, 32), 100, game.getPlayerTexture());
        Gdx.app.log("GameScreen", "Player initialized: " + player);
        this.mapLoader = new TmxMapLoader();
        this.tiledMap = mapLoader.load("testMap.tmx");
        Gdx.app.log("GameScreen", "Tiled map loaded: " + tiledMap);
        this.mapManager = new MapManager(tiledMap);
        Gdx.app.log("GameScreen", "MapManager initialized: " + mapManager);
        this.gameController = new GameController(player, mapManager);
        Gdx.app.log("GameScreen", "GameController initialized: " + gameController);
    }

    @Override
    public void show(){

        Gdx.input.setInputProcessor(gameController);
        // Map setup
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

        player.update(delta);

        game.getBatch().begin();
        player.render(game.getBatch());
        game.getBatch().end();
    }
    @Override
    public void dispose() {
        if (tiledMapRenderer != null) {
            tiledMapRenderer.dispose();
        }
        if (tiledMap != null) {
            tiledMap.dispose();
        }
        if (camera != null) {
            camera = null;
        }
    }
}
