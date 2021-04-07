package com.smeshed.mb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.smeshed.mb.Character.CharacterEtat;

public class mb extends ApplicationAdapter {
	private SpriteBatch batch;
	private Character mario;
	private int layerToRender[] = { 0, 1 };
	private OrthographicCamera camera;
	private TiledMapRenderer tiledMapRenderer;
	private Map map01;
	private float stateTime;
	private MapObjects collisionObjects;
	private Coordonnees itialBeforeJumpCoordonnees;

	@Override
	public void create() {
		batch = new SpriteBatch();
		drawCamera();
		mario = new Character(25, 25, 180, 250);
		map01 = new Map("./maps/map1-1.tmx", 192);
		collisionObjects = map01.getCollisionTile(2);
		tiledMapRenderer = map01.getTiledMapRenderer();
	}

	@Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layerToRender);
		batch.begin();
		mario.draw(batch, stateTime);

		mario.isDead();
		keyPressed();
		camera.update();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	private void keyPressed() {
		Rectangle hitbox = mario.getHitbox();

		if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) {
			hitbox.x -= 4;
			hitbox.y += 4;
			if (!collisionDetection(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.x = camera.position.x - 4;
				mario.setX(mario.getX() - 4);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		} else if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
			hitbox.x += 4;
			hitbox.y += 4;
			if (!collisionDetection(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.x = camera.position.x + 4;
				mario.setX(mario.getX() + 4);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			hitbox.x -= 4;
			if (!collisionDetection(hitbox)) {
				camera.position.x = camera.position.x - 4;
				mario.setX(mario.getX() - 4);
			}
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			hitbox.x += 4;
			if (!collisionDetection(hitbox)) {
				camera.position.x = camera.position.x + 4;
				mario.setX(mario.getX() + 4);
			}
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			hitbox.y -= 4;
			if (!collisionDetection(hitbox)) {
				camera.position.y = camera.position.y - 4;
				mario.setY(mario.getY() - 4);
			}
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			hitbox.y += 4;
			if (!collisionDetection(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		}
		gravity();
	}

	private void drawCamera() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
	}

	// TODO: Gerer autres formes que carré.
	// ? Polygon / Losanges
	public boolean collisionDetection(Rectangle hitbox) {
		for (RectangleMapObject rectangleObject : collisionObjects.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			if (Intersector.overlaps(rectangle, hitbox)) {
				return true;
			}
		}
		return false;
	}

	public void gravity() {
		if (mario.getEtat() != CharacterEtat.JUMP) {
			Rectangle hitbox = mario.getHitbox();
			hitbox.y -= 4;
			if (!collisionDetection(hitbox)) {
				camera.position.y = camera.position.y - 4;
				mario.setY(mario.getY() - 4);
			}

		}
	}

	public void Jump() {
		itialBeforeJumpCoordonnees = new Coordonnees(mario.getX(), mario.getY());

	}

}