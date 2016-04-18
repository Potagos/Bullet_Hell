package com.wurst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Maxi on 14.04.2016.
 */
public class InputManager implements InputProcessor {

    private Player p;
    private final float SPEED = 200.0f;

    InputManager(Player p) {
        this.p = p;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.RIGHT: {
                p.setDirection(Direction.RIGHT);
                p.setSpeed(SPEED * Gdx.graphics.getDeltaTime());
                break;
            }
            case Input.Keys.LEFT: {
                p.setDirection(Direction.LEFT);
                p.setSpeed(SPEED * Gdx.graphics.getDeltaTime());
                break;
            }
            case Input.Keys.UP: {
                p.setDirection(Direction.UP);
                p.setSpeed(SPEED * Gdx.graphics.getDeltaTime());
                break;
            }
            case Input.Keys.DOWN: {
                p.setDirection(Direction.DOWN);
                p.setSpeed(SPEED * Gdx.graphics.getDeltaTime());
                break;
            }

            default: {
                p.setDirection(Direction.STAND);
                p.setSpeed(0);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT && p.getDirection() == Direction.LEFT) {
            p.setDirection(Direction.STAND);
        } else if (keycode == Input.Keys.RIGHT && p.getDirection() == Direction.RIGHT) {
            p.setDirection(Direction.STAND);
        } else if (keycode == Input.Keys.UP && p.getDirection() == Direction.UP) {
            p.setDirection(Direction.STAND);
        } else if (keycode == Input.Keys.DOWN && p.getDirection() == Direction.DOWN) {
            p.setDirection(Direction.STAND);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
