package com.smeshed.mb;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.MapProperties;

public class Map {
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private int tilePixelWidth;
    private int tilePixelHeight;
    private int groundHeight;

    public Map(String mapPath, int groundHeight) {
        tiledMap = new TmxMapLoader().load(mapPath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        MapProperties propMap = tiledMap.getProperties();
        tilePixelWidth = propMap.get("tilewidth", Integer.class) * propMap.get("width", Integer.class);
        tilePixelHeight = propMap.get("tileheight", Integer.class) * propMap.get("height", Integer.class);
        this.groundHeight = groundHeight;

    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public int getTilePixelHeight() {
        return tilePixelHeight;
    }

    public int getTilePixelWidth() {
        return tilePixelWidth;
    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public MapLayer getCollisionLayer(int i) {
        return tiledMap.getLayers().get(i);
    }
}
