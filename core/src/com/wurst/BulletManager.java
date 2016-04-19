package com.wurst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Maxi on 15.04.2016.
 */
public class BulletManager {
    private ArrayList<Bullet> bullets;
    private long time;
    private Rectangle rec;
    private float speed;
    private Random rand;

    public BulletManager() {
        bullets = new ArrayList<Bullet>();
        time = System.currentTimeMillis();
        rec = new Rectangle(0, 0, 16, 16);
        rand = new Random();
    }

    public void update(int score) {
        speed = (float) ((50+Math.sqrt(score*(score/2))*10)*Gdx.graphics.getDeltaTime());
        if ((System.currentTimeMillis() - time - score*10) > (1000 - (score*10))) {
            for (int i = 0; i <= rand.nextInt(3); i++) {
                speed = (float) ((50+Math.sqrt(score*(score/2))*10)*Gdx.graphics.getDeltaTime());
                Bullet tempBullet = new Bullet(rec, (int) (Math.random()*90),speed);
                bullets.add(tempBullet);
            }
            time = System.currentTimeMillis();
        }

        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            float direction = (float )Math.toRadians((float) b.getDirection());
            //Aktualisiert Position von Bullet anhand von Richtung
            b.setPosition(b.getPosition().x + (float)Math.sin(direction)*b.getSpeed(),
                        b.getPosition().y + (float)Math.cos(direction)*b.getSpeed());

            if(b.getPosition().x > BananenWurst.WIDTH || b.getPosition().y > BananenWurst.HEIGHT) {
                bullets.remove(b);
            }
        }
    }

    public void draw(ShapeRenderer shape) {
        for(Bullet b : bullets) {
            b.setSpeed(speed);
            shape.circle(b.getPosition().x + 8, b.getPosition().y + 8, 16);
        }
    }

    public boolean overlap(Rectangle rec) {
        for(Bullet b : bullets) {
            if (b.getRectangle().overlaps(rec)) {
                bullets.remove(b);
                BananenWurst.lifes--;
                return true;
            }
        }
        return false;
    }

    public float getSpeed(){return speed;}
}
