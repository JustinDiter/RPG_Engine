package com.justinditer.rpgengine.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.justinditer.rpgengine.model.Direction;
import com.justinditer.rpgengine.model.MapManager;
import com.justinditer.rpgengine.model.Player;

public class GameController extends InputAdapter {
    private Player player;
    private MapManager mapManager;
    private Direction direction;

    public GameController(Player player, MapManager mapManager) {
        this.player = player;
        this.mapManager = mapManager;
    }
    @Override
    public boolean keyDown(int keycode) {
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

        if (direction != null) {
            Vector2 targetPosition = player.getMoveTarget(direction);
            if (mapManager.isWithinBounds(targetPosition) && !mapManager.isCollidable(targetPosition)) {
                player.setPosition(targetPosition);
            }
            return true; // Input processed
        }

        return false; // Input not processed
    }
}
