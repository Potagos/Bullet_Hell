package com.wurst;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class BananenWurst extends ApplicationAdapter {
	public  static int WIDTH;
	public static int HEIGHT;
	private SpriteBatch batch;
	private Texture img;
	private Texture texMario;
	private ParticleEffect p;
	private BitmapFont font;
	private Sprite spr;
	private Player pe;
	private InputManager input;
	private ShapeRenderer shapeR;
	private Rectangle rec;
	private int score;
	public static int  lifes;
	private BulletManager bullet;

	boolean penis = false;

	public BananenWurst(LwjglApplicationConfiguration config){
		WIDTH = config.width;
		HEIGHT = config.height;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		texMario = new Texture("BabyMario.png");
		p = new ParticleEffect();
		p.load(Gdx.files.internal("boom"),Gdx.files.internal(""));

		p.setPosition(300, 300);
		p.start();


		font = new BitmapFont();
		font.setColor(Color.RED);

		spr = new Sprite(img);
		spr.setScale(0.25f);
		spr.setPosition(300, 200);

		pe = new Player();
		input = new InputManager(pe);
		Gdx.input.setInputProcessor(input);

		rec = new Rectangle(pe.getPosition().x, pe.getPosition().y, texMario.getWidth(), texMario.getHeight());
		shapeR = new ShapeRenderer();

		score = 0;
		lifes = 5;

		bullet = new BulletManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
//		p.update(Gdx.graphics.getDeltaTime());
//		p.draw(batch, Gdx.graphics.getDeltaTime());
		if (penis){
			font.draw(batch, "penis", 50, 50);
		}
		spr.draw(batch);
		batch.draw(texMario, pe.getPosition().x, pe.getPosition().y);
		font.draw(batch, "Score: "+ score, 500, 400);
		font.draw(batch, "Lifes: "+ lifes, 500, 415);
		batch.end();


		shapeR.begin(ShapeRenderer.ShapeType.Line);
		bullet.draw(shapeR);
		shapeR.end();

		pe.update();
		bullet.update(score);
		pe.setSpeed(bullet.getSpeed());
		rec.setPosition(pe.getPosition());

		bullet.overlap(rec);
		if(rec.overlaps(spr.getBoundingRectangle())) {
			spr.setPosition((float)Math.random() * 300,(float) Math.random() * 300);
			score++;
		}

		if(lifes <= 0){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Gdx.app.exit();
		}
/*
		if (p.isComplete()) {
			p.setPosition((float) Math.random() * 600, (float) Math.random() * 400);
			p.start();
		}
*/
		if (penis == false && Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			penis = true;
		} else if (penis == true && Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			penis = false;
		}

		spr.rotate(2);
	}
}
