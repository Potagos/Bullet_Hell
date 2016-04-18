package com.wurst;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maxi on 14.04.2016.
 */
public class Player {
    private Direction dir;
    private Vector2 pos;
    private double speed;

    public Player() {
        dir = Direction.STAND;
        pos = new Vector2(300, 300);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public Direction getDirection() {
        return dir;
    }

    public Vector2 getPosition() {
        return pos;
    }

    public double getSpeed() {
        return speed;
    }

    public void update() {
        switch(dir) {
            case UP : {
                pos.y += speed;
                break;
            }
            case DOWN : {
                pos.y -= speed;
                break;
            }
            case RIGHT : {
                pos.x += speed;
                break;
            }
            case LEFT : {
                pos.x -= speed;
                break;
            }
            case STAND : {
                speed = 0;
            }
        }
    }
}
