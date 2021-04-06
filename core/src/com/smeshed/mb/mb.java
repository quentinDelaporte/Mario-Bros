package com.smeshed.mb;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.files.FileHandle;

public class mb extends ApplicationAdapter {
	private SpriteBatch batch;
	private float stateTime;
	private Anim marioStaticLeft;
	private Character mario;
	private Texture staticMario;
	private Dimension screenSize;
	private int layerToRender[] = { 0 };
	private int screenHeight, screenWidth;
	private OrthographicCamera camera;
	private CharacterEtat etatMario;
	private TiledMapRenderer tiledMapRenderer;
	private Map map01;

	@Override
	public void create() {
		batch = new SpriteBatch();
		drawCamera();
		FileHandle marioStandStatic = Gdx.files.internal("./images/mario/mario-static-left.png");
		marioStaticLeft = new Anim(marioStandStatic, 5, 1, 0.1f);
		staticMario = new Texture("./images/mario/mario-static-left.png");

		mario = new Character(staticMario, 25, 25);
		etatMario = CharacterEtat.STATICLEFT;

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;

		map01 = new Map("./maps/map01.tmx", 192);
		tiledMapRenderer = map01.getTiledMapRenderer();
	}

	@Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layerToRender);
		batch.begin();
		drawingMario();
		keyPressed();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	private void keyPressed() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			camera.position.x = camera.position.x - Gdx.graphics.getDeltaTime() * 300;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			camera.position.x = camera.position.x + Gdx.graphics.getDeltaTime() * 300;
		} /*
			 * else if (Gdx.input.isKeyPressed(Keys.DOWN)) { camera.position.y =
			 * camera.position.y - Gdx.graphics.getDeltaTime() * 300; } else if
			 * (Gdx.input.isKeyPressed(Keys.UP)) { camera.position.y = camera.position.y +
			 * Gdx.graphics.getDeltaTime() * 300; }
			 */
		camera.update();
	}

	private void drawCamera() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
	}

	public void drawingMario() {
		mario.dessine(batch, marioStaticLeft.getAnimation(stateTime), 250, 180);
	}
}
