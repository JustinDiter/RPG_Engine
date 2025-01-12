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

    // This class is destined as a general map manager for the model. All map logic was to be implemented here.
    // TODO: Combat encounters, Interior maps

    private TiledMapTileLayer baseTileLayer;
    private TiledMapTileLayer collisionTileLayer;
    private MapObjects interactionObjects;

    public MapManager(TiledMap map) {
        this.baseTileLayer = (TiledMapTileLayer) map.getLayers().get("BaseTileLayer");
        this.collisionTileLayer = (TiledMapTileLayer) map.getLayers().get("CollisionTileLayer");
        this.interactionObjects = map.getLayers().get("InteractionTiles").getObjects();
    }

    public MapObjects getInteractionObjects() {
        return interactionObjects;
    } // This would allow for doors, npc interactions, etc.

    public boolean isCollidable(Vector2 position) {

        // Collision detection method. Could probably be simpler.
        // Convert position to grid coordinates
        int tileX = (int) (position.x / collisionTileLayer.getTileWidth());
        int tileY = (int) (position.y / collisionTileLayer.getTileHeight());

        // Get the cell at the grid position
        TiledMapTileLayer.Cell cell = collisionTileLayer.getCell(tileX, tileY);

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

    public int getTileSize() {
        return baseTileLayer.getTileHeight();
    }

    public Vector2 getEntityStartPositions(MapObjects objects, String target) {
        // Changed last minute to allow for an NPC on the map. This is not a good solution for the npxs.
        for (MapObject object : objects) {
            if (object.getName().equals("playerStartPosition")) {
                if (object instanceof RectangleMapObject) {
                    if (target.equals("player")) {
                        Rectangle rect = ((RectangleMapObject) object).getRectangle();
                        return new Vector2(rect.x, rect.y);
                    }
                }
            } else if (object.getName().equals("npcPosition")) {
                if (object instanceof RectangleMapObject) {
                    if (target.equals("npc")) {
                        Rectangle rect = ((RectangleMapObject) object).getRectangle();
                        return new Vector2(rect.x, rect.y);
                    }
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
        // Ensures player stays within the bounds of the map.
        int mapWidth = baseTileLayer.getWidth() * (int) baseTileLayer.getTileHeight();
        int mapHeight = baseTileLayer.getHeight() * (int) baseTileLayer.getTileHeight();

        return position.x >= 0 && position.x < mapWidth && position.y >= 0 && position.y < mapHeight;
    }
}
