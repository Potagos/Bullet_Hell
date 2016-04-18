package com.wurst;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maxi on 15.04.2016.
 */
public class Bullet {
    private Rectangle bbox;
    private float direction; //Degree
    private float speed;

    public Bullet(Rectangle rec, float dir, float speed) { //Deep Copy-Constructor
        bbox = new Rectangle(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
        direction = dir;
        this.speed = speed; // penis
    }

    public Rectangle getRectangle() {
        return bbox;
    }

    public void setPosition(float x, float y) {
        bbox.setPosition(x, y);
    }

    public void setPosition(Vector2 pos) {
        bbox.setPosition(pos);
    }

    public Vector2 getPosition() {
        return new Vector2(bbox.x, bbox.y);
    }

    public void setDirection(float dir) {
        direction = dir;
    }

    public float getDirection() {
        return direction;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed(){
        return speed;
    }

}
