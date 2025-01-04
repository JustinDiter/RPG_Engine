package com.justinditer.rpgengine.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class MapManager {
    private TiledMapTileLayer baseTileLayer;
    private MapObjects interactionObjects;

    public MapManager(TiledMap map) {
        this.baseTileLayer = (TiledMapTileLayer) map.getLayers().get("BaseTileLayer");
        this.interactionObjects = map.getLayers().get("InteractionTiles").getObjects();
    }

    public MapObjects getInteractionObjects() {
        return interactionObjects;
    }

    public boolean isCollidable(Vector2 position) {
        // Convert position to grid coordinates
        int tileX = (int) (position.x / baseTileLayer.getTileWidth());
        int tileY = (int) (position.y / baseTileLayer.getTileHeight());

        // Get the cell at the grid position
        TiledMapTileLayer.Cell cell = baseTileLayer.getCell(tileX, tileY);
        if (cell != null) {
            TiledMapTile tile = cell.getTile();

            // Check if the tile has the "collidable" property
            if (tile.getProperties().containsKey("collidable") &&
                (boolean) tile.getProperties().get("collidable")) {
                return true; // Tile is collidable
            }
        }
        return false; // No collision detected
    }

    public Vector2 getPlayerStartPosition(MapObjects objects) {
        for (MapObject object : objects) {
            if (object.getName() != null && object.getName().equals("playerStartPosition")) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    // Return the center of the rectangle
                    return new Vector2(rect.x, rect.y);
                }
            }
        }

        // Default fallback position if not found
        return new Vector2(0, 0);
    }

    public int getMapWidth() {
        return baseTileLayer.getWidth() * (int) baseTileLayer.getTileWidth();
    }

    public int getMapHeight() {
        return baseTileLayer.getHeight() * (int) baseTileLayer.getTileHeight();
    }

    public boolean isWithinBounds(Vector2 position) {
        int mapWidth = baseTileLayer.getWidth() * (int) baseTileLayer.getTileHeight();
        int mapHeight = baseTileLayer.getHeight() * (int) baseTileLayer.getTileHeight();

        return position.x >= 0 && position.x < mapWidth && position.y >= 0 && position.y < mapHeight;
    }
}
