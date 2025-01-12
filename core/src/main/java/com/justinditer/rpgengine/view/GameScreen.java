package com.justinditer.rpgengine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.justinditer.rpgengine.RPGGame;
import com.justinditer.rpgengine.controller.GameController;
import com.justinditer.rpgengine.model.MapManager;
import com.justinditer.rpgengine.model.NPC;
import com.justinditer.rpgengine.model.Player;

public class GameScreen extends ScreenAdapter {

    // Main game screen class, as you can see, it got a bit bloated. Would require a refactor.
    // Big difficulties when trying to implement a camera that follows the player, halted progress significantly.

    RPGGame game;
    TmxMapLoader mapLoader;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Skin skin;
    private Player player;
    private NPC npc;
    private MapManager mapManager;
    private GameController gameController;
    private ShapeRenderer shapeRenderer;
    private Stage uiStage;
    private MenuUI menuUI;

    public GameScreen(RPGGame game) {
        this.game = game;
        this.mapLoader = new TmxMapLoader();
        this.tiledMap = mapLoader.load("testMap.tmx");
        this.mapManager = new MapManager(tiledMap);
        Vector2 playerStartPosition = mapManager.getEntityStartPositions(mapManager.getInteractionObjects(), "player");
        Vector2 npcStartPosition = mapManager.getEntityStartPositions(mapManager.getInteractionObjects(), "npc");
        this.player = new Player("Guy", playerStartPosition, 100, game.getPlayerTexture());
        this.npc = new NPC("Test", npcStartPosition, "This is a Test npc.", game.getNpcTexture());
        this.gameController = new GameController(player, mapManager);

    }

    @Override
    public void show(){

        Gdx.input.setInputProcessor(gameController);
        uiStage = new Stage(new ScreenViewport());
        menuUI = new MenuUI();
        uiStage.addActor(menuUI);


        // Map setup
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);

        // Camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        camera.update();

        // Shape renderer set up for UI boxes
        shapeRenderer = new ShapeRenderer();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);
        // This following code was the last functionality I tried to implement. Dialogue box when player
        // is adjacent to an npc.
        if ((player.position.x == npc.position.x - 16 || player.position.x == npc.position.x + 16) &&
            (player.position.y == npc.position.y - 16 || player.position.y == npc.position.y + 16)) {
            this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
            TextArea dialogueBox = new TextArea(npc.getDialogue(), skin);
            dialogueBox.setPosition(npc.position.x + 25, npc.position.y + 25);
            dialogueBox.setFillParent(false);
        }
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        game.getBatch().begin();
        player.render(game.getBatch());
        npc.render(game.getBatch());
        game.getBatch().end();

        if (gameController.isMenuActive()) {
            uiStage.act(delta);
            uiStage.draw();
        }
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
