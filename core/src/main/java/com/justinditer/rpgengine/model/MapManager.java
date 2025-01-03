package com.justinditer.rpgengine.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class MapManager {
    private MapObjects collisionObjects;
    private TiledMapTileLayer baseTileLayer;
    private HashMap<Vector2, Rectangle> collisionMap = new HashMap<>();

    public MapManager(TiledMap map) {
        this.baseTileLayer = (TiledMapTileLayer) map.getLayers().get("BaseTileLayer");
        MapObjects collisionObjects = map.getLayers().get("CollisionLayer").getObjects();

        for (MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject &&
                object.getProperties().containsKey("collidable") &&
                (boolean) object.getProperties().get("collidable")) {

                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                Vector2 gridPosition = new Vector2((int) rect.x, (int) rect.y);
                collisionMap.put(gridPosition, rect);
            }
        }
    }

    public boolean isCollidable(Vector2 position) {
        Vector2 gridPosition = new Vector2((int) position.x, (int) position.y);
        Rectangle rect = collisionMap.get(gridPosition);

        // Check if the object contains the exact position
        return rect != null && rect.contains(position.x, position.y);
    }

    public boolean isWithinBounds(Vector2 position) {
        int mapWidth = baseTileLayer.getWidth() * (int) baseTileLayer.getTileHeight();
        int mapHeight = baseTileLayer.getHeight() * (int) baseTileLayer.getTileHeight();

        return position.x >= 0 && position.x < mapWidth && position.y >= 0 && position.y < mapHeight;
    }
}
