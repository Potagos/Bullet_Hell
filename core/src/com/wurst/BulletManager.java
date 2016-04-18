package com.wurst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Maxi on 15.04.2016.
 */
public class BulletManager {
    private ArrayList<Bullet> bullets;
    private long time;
    private Rectangle rec;

    public BulletManager() {
        bullets = new ArrayList<Bullet>();
        time = System.currentTimeMillis();
        rec = new Rectangle(0, 0, 16, 16);
    }

    public void update(int score) {
        if ((System.currentTimeMillis() - time - score*10) > 1000) {
            for (int i = 0; i < 1 + score % 3; i++) {
                Bullet tempBullet = new Bullet(rec, (int) (Math.random()*90),(50+score*10)*Gdx.graphics.getDeltaTime());
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

            if(b.getPosition().x > 1000 || b.getPosition().y > 800) {
                bullets.remove(b);
            }
        }
    }

    public void draw(ShapeRenderer shape) {
        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            shape.circle(b.getPosition().x + 8, b.getPosition().y + 8, 16);
        }
    }

    public boolean overlap(Rectangle rec) {
        for(int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getRectangle().overlaps(rec)) {
                bullets.remove(bullets.get(i));
                return true;
            }
        }
        return false;
    }
}
