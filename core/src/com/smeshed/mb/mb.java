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
import com.smeshed.mb.Character.CharacterFacing;

public class mb extends ApplicationAdapter {
	private SpriteBatch batch;
	private Character mario;
	private int layerToRender[] = { 0, 1 };
	private OrthographicCamera camera;
	private TiledMapRenderer tiledMapRenderer;
	private Map map01;
	private float stateTime;
	private MapObjects collisionObjects;
	private Coordonnees initialBeforeJumpCoordonnees;

	@Override
	public void create() {
		batch = new SpriteBatch();
		drawCamera();
		mario = new Character(20, 24, 180, 250);
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

		if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)
				&& mario.getEtat() != CharacterEtat.FALL) {
			hitbox.x -= 4;
			hitbox.y += 4;
			if (!collisionDetectionWithMap(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.x = camera.position.x - 4;
				mario.setX(mario.getX() - 4);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
				mario.setFacing(CharacterFacing.LEFT);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		} else if (Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)
				&& mario.getEtat() != CharacterEtat.FALL) {
			hitbox.x += 4;
			hitbox.y += 4;
			if (!collisionDetectionWithMap(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.x = camera.position.x + 4;
				mario.setX(mario.getX() + 4);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
				mario.setFacing(CharacterFacing.RIGHT);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		} else if (Gdx.input.isKeyPressed(Keys.UP) && mario.getEtat() != CharacterEtat.FALL) {
			hitbox.y += 4;
			if (!collisionDetectionWithMap(hitbox)) {
				mario.setEtat(CharacterEtat.JUMP);
				camera.position.y = camera.position.y + 4;
				mario.setY(mario.getY() + 4);
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			hitbox.x -= 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x - 4;
				mario.setX(mario.getX() - 4);
				mario.setFacing(CharacterFacing.LEFT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.WALK);
			}
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			hitbox.x += 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x + 4;
				mario.setX(mario.getX() + 4);
				mario.setFacing(CharacterFacing.RIGHT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.WALK);
			}
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			hitbox.y -= 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.y = camera.position.y - 4;
				mario.setY(mario.getY() - 4);
			}
		}

		// Reset de l'état si aucune touche préssé
		if (!Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)
				&& !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN))
			mario.setEtat(CharacterEtat.STATIC);

		gravity();
		jump();
		System.out.println(mario.getEtat());
	}

	private void drawCamera() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
	}

	// TODO: Gérer autres formes que carré.
	// ? Polygon / Losanges / triangles
	public boolean collisionDetectionWithMap(Rectangle hitbox) {
		for (RectangleMapObject rectangleObject : collisionObjects.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			if (Intersector.overlaps(rectangle, hitbox)) {
				return true;
			}
		}
		return false;
	}

	public void gravity() {
		// ? Implémenter vitesse de chute
		if (mario.getEtat() != CharacterEtat.JUMP) {
			Rectangle hitbox = mario.getHitbox();
			hitbox.y -= 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.y = camera.position.y - 4;
				mario.setY(mario.getY() - 4);
			}

		}
	}

	public void jump() {
		// ? Patch: Si pas de collision en dessou ==> pas de saut : Creer hitbox et
		// ? tester la collision de la positon future
		double hauteur = 2.5;
		if (mario.getEtat() != CharacterEtat.JUMP) {
			initialBeforeJumpCoordonnees = new Coordonnees(mario.getX(), mario.getY());
		} else {
			if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * hauteur) {

				if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.3 * hauteur) {
					camera.position.y = camera.position.y + 4;
					mario.setY(mario.getY() + 4);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.5 * hauteur) {
					camera.position.y = camera.position.y + 2;
					mario.setY(mario.getY() + 2);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.7 * hauteur) {
					camera.position.y = camera.position.y + 1;
					mario.setY(mario.getY() + 1);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * hauteur) {
					camera.position.y = (float) (camera.position.y + 0.5);
					mario.setY(mario.getY() + (float) 0.5);
				}

				// ? Progression du saut
				// ? ((mario.getY() - intialBeforeJumpCoordonnees.getY()) / 100 + 0.5)
			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		}
	}

}