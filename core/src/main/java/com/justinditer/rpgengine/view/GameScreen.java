package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private ShapeRenderer shapeRenderer;

    public GameScreen(RPGGame game) {
        this.game = game;
        this.mapLoader = new TmxMapLoader();
        this.tiledMap = mapLoader.load("testMap.tmx");
        this.mapManager = new MapManager(tiledMap);
        Vector2 startPosition = mapManager.getPlayerStartPosition(mapManager.getInteractionObjects());
        this.player = new Player("Guy", startPosition, 100, game.getPlayerTexture());
        this.gameController = new GameController(player, mapManager);
    }

    @Override
    public void show(){

        Gdx.input.setInputProcessor(gameController);
        // Map setup
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);

        // Camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        // Shape renderer set up for UI boxes
        shapeRenderer = new ShapeRenderer();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        game.getBatch().begin();
        player.render(game.getBatch());
        game.getBatch().end();

        if (gameController.isMenuActive()) {
            drawMenu();
        }
    }

    private void drawMenu() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(200, 150, 400, 300); // Adjust x, y, width, height
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(200, 150, 400, 300); // Same dimensions for the border
        shapeRenderer.end();
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
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
    }

}
