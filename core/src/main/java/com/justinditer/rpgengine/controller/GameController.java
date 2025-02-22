package com.justinditer.rpgengine.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.justinditer.rpgengine.model.Direction;
import com.justinditer.rpgengine.model.MapManager;
import com.justinditer.rpgengine.model.Player;

public class GameController extends InputAdapter {

    // Class destined to provide an InputAdapter for the Game Screen. Handles inputs when the main game is running.

    private Player player;
    private MapManager mapManager;
    private Direction direction;
    private boolean menuActive;

    public GameController(Player player, MapManager mapManager) {
        this.player = player;
        this.mapManager = mapManager;
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) { // Menu opened Flag, to prevent player movement.
            menuActive = !menuActive;
            return true;
        }
        Direction direction = null;
        if (keycode == Input.Keys.UP) {
            direction = Direction.UP;
        } else if (keycode == Input.Keys.DOWN) {
            direction = Direction.DOWN;
        } else if (keycode == Input.Keys.LEFT) {
            direction = Direction.LEFT;
        } else if (keycode == Input.Keys.RIGHT) {
            direction = Direction.RIGHT;
        }

        if (direction != null && !menuActive) {
            Vector2 targetPosition = player.getMoveTarget(direction);
            if (mapManager.isWithinBounds(targetPosition) && !mapManager.isCollidable(targetPosition)) {
                // checks if Menu is opened and if the destination tile is clear before moving position.
                player.setPosition(targetPosition);
            }
            return true; // Input processed
        } else if (menuActive) {
            // TODO: Handle menu related inputs
            // Was supposed to handle menu selection here.
            return true;
        }
        return false; // Input not processed
    }

    public boolean isMenuActive() {
        return menuActive;
        // getter for the menuActive flag
    }
}
