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
import com.smeshed.mb.Entity.Character.CharacterEtat;
import com.smeshed.mb.Entity.Character.CharacterFacing;
import com.smeshed.mb.Entity.Character;
import com.smeshed.mb.GUI.Gui;
import com.smeshed.mb.GUI.TimerParty;
import com.smeshed.mb.Entity.Mob.MobType;
import com.smeshed.mb.Objects.Coin;
import com.smeshed.mb.Objects.Coin.CoinType;
import com.smeshed.mb.Utils.AmbiantSound;
import com.smeshed.mb.Utils.BackgroundMusic;
import com.smeshed.mb.Utils.Coordonnees;

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
	// private Goomba goomba;
	// private Coin c0;
	private int jumpHeight;
	private Gui gui;
	private BackgroundMusic m;
	private boolean deadSoundPlayed = false;

	@Override
	public void create() {
		batch = new SpriteBatch();
		drawCamera();
		mario = new Character(20, 24, Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 2 - 12);
		map01 = new Map("./maps/map1-1.tmx");
		gui = new Gui();
		m = new BackgroundMusic(0.01f);
		// goomba = new Goomba(180, 700, 20, 24, MobType.GOOMBA, false);
		// c0 = new Coin(CoinType.GIANT_COIN, 180, 350, 24, 24);
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
		gui.draw(batch);
		// goomba.draw(batch, stateTime);
		// goomba.move(mario.getEtat(), mario.getFacing(), jumpHeight);
		// c0.draw(batch, stateTime);
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
		CharacterEtat lastEtat = mario.getEtat();

		if (mario.getEtat() != CharacterEtat.DEAD) {
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				// ! vers la gauche
				mario.setFacing(CharacterFacing.LEFT);
			else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				// ! vers la droite
				mario.setFacing(CharacterFacing.RIGHT);
			}

			if ((Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT))
					&& !Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && mario.getEtat() != CharacterEtat.JUMPWALK
					&& mario.getEtat() != CharacterEtat.JUMPRUN && mario.getEtat() != CharacterEtat.FALL) {
				// ! Marche
				mario.setEtat(CharacterEtat.WALK);
				walk(hitbox);
			}

			if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
					&& (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT))
					&& mario.getEtat() != CharacterEtat.JUMPWALK && mario.getEtat() != CharacterEtat.JUMPRUN
					&& mario.getEtat() != CharacterEtat.FALL) {
				// ! Course
				mario.setEtat(CharacterEtat.RUN);
				run(hitbox);
			}

			if (!Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)
					&& !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)
					&& mario.getEtat() != CharacterEtat.JUMPWALK && mario.getEtat() != CharacterEtat.JUMPRUN
					&& mario.getEtat() != CharacterEtat.FALL) {
				// ! Static
				mario.setEtat(CharacterEtat.STATIC);
				statiq(hitbox);
			}

			if (mario.getEtat() != CharacterEtat.FALL) {
				if (Gdx.input.isKeyPressed(Keys.UP)) {
					if (lastEtat != CharacterEtat.JUMP && lastEtat != CharacterEtat.JUMPWALK
							&& lastEtat != CharacterEtat.JUMPRUN) {
						AmbiantSound s = new AmbiantSound(1f, "./song/smb_jump-small.wav");

					}
					if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
							&& (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT))) {
						// ! Saut en courant
						mario.setEtat(CharacterEtat.JUMPRUN);
					} else if ((Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT))) {
						// ! Saut en marchant
						mario.setEtat(CharacterEtat.JUMPWALK);
					} else {
						// ! Saut static
						mario.setEtat(CharacterEtat.JUMP);
					}
				}
			}
		} else {
			if (!deadSoundPlayed) {
				AmbiantSound s2 = new AmbiantSound(1f, "./song/smb_mariodie.wav");
				deadSoundPlayed = true;
			}
		}
		gravity();
		jump();

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
		if (mario.getEtat() != CharacterEtat.JUMP && mario.getEtat() != CharacterEtat.JUMPRUN
				&& mario.getEtat() != CharacterEtat.JUMPWALK) {
			Rectangle hitbox = mario.getHitbox();
			hitbox.y -= 4;
			if (!collisionDetectionWithMap(hitbox)) {
				mario.setEtat(CharacterEtat.FALL);
				camera.position.y = camera.position.y - 4;
				mario.setY(mario.getY() - 4);
			}
			if (collisionDetectionWithMap(hitbox) && (mario.getEtat() == CharacterEtat.FALL)) {
				mario.setEtat(CharacterEtat.STATIC);
			}

		}
	}

	public void jump() {
		// ? Patch: Si pas de collision en dessou ==> pas de saut : Creer hitbox et
		// ? tester la collision de la positon future
		double hauteur = 2.5;
		if (mario.getEtat() != CharacterEtat.JUMP && mario.getEtat() != CharacterEtat.JUMPWALK
				&& mario.getEtat() != CharacterEtat.JUMPRUN) {
			initialBeforeJumpCoordonnees = new Coordonnees(mario.getX(), mario.getY());
		} else {
			if (mario.getEtat() == CharacterEtat.JUMPWALK) {
				if (mario.getFacing() == CharacterFacing.LEFT) {
					camera.position.x = camera.position.x - 2;
					mario.setX(mario.getX() - 2);
					mario.setFacing(CharacterFacing.LEFT);
				} else if (mario.getFacing() == CharacterFacing.RIGHT) {
					camera.position.x = camera.position.x + 2;
					mario.setX(mario.getX() + 2);
					mario.setFacing(CharacterFacing.RIGHT);
				}
			} else if (mario.getEtat() == CharacterEtat.JUMPRUN) {
				if (mario.getFacing() == CharacterFacing.LEFT) {
					camera.position.x = camera.position.x - 4;
					mario.setX(mario.getX() - 4);
					mario.setFacing(CharacterFacing.LEFT);
				} else if (mario.getFacing() == CharacterFacing.RIGHT) {
					camera.position.x = camera.position.x + 4;
					mario.setX(mario.getX() + 4);
					mario.setFacing(CharacterFacing.RIGHT);
				}
			}

			if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * hauteur) {
				if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.3 * hauteur) {
					jumpHeight = 4;
					camera.position.y = camera.position.y + 4;
					mario.setY(mario.getY() + 4);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.5 * hauteur) {
					jumpHeight = 2;
					camera.position.y = camera.position.y + 2;
					mario.setY(mario.getY() + 2);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * 0.7 * hauteur) {
					jumpHeight = 1;
					camera.position.y = camera.position.y + 1;
					mario.setY(mario.getY() + 1);
				} else if (mario.getY() <= initialBeforeJumpCoordonnees.getY() + 16 * hauteur) {
					camera.position.y = (float) (camera.position.y + 0.5);
					mario.setY(mario.getY() + (float) 0.5);
				}

			} else {
				mario.setEtat(CharacterEtat.FALL);
			}
		}
		// ? Progression du saut
		// ? ((mario.getY() - intialBeforeJumpCoordonnees.getY()) / 100 + 0.5)
		// TODO : Debut fall apres un saut : tester si arrivé au sol -> mettre sur
		// TODO : STATIC
	}

	public void run(Rectangle hitbox) {
		if (mario.getFacing() == CharacterFacing.RIGHT) {
			hitbox.x += 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x + 4;
				mario.setX(mario.getX() + 4);
				mario.setFacing(CharacterFacing.RIGHT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.RUN);
			}
		} else if (mario.getFacing() == CharacterFacing.LEFT) {
			hitbox.x -= 4;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x - 4;
				mario.setX(mario.getX() - 4);
				mario.setFacing(CharacterFacing.LEFT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.RUN);
			}
		}
	}

	public void walk(Rectangle hitbox) {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			hitbox.x -= 2;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x - 2;
				mario.setX(mario.getX() - 2);
				mario.setFacing(CharacterFacing.LEFT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.WALK);
			}
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			hitbox.x += 2;
			if (!collisionDetectionWithMap(hitbox)) {
				camera.position.x = camera.position.x + 2;
				mario.setX(mario.getX() + 2);
				mario.setFacing(CharacterFacing.RIGHT);
				if (mario.getEtat() == CharacterEtat.STATIC)
					mario.setEtat(CharacterEtat.WALK);
			}
		}
	}

	public void fallRun(Rectangle hitbox) {

	}

	public void fallWalk(Rectangle hitbox) {

	}

	public void statiq(Rectangle hitbox) {

	}
}