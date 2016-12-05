package com.evil_racoon.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by уе on 04.11.2016.
 */
public class Tube {
    public static final int TUBE_HEIGHT = 76;//толщина труб
    public static final int FLUCTUATION = 200;//диапазон откланений труб
    public static final int TUBE_GAP = 250+146;//длина просвета
    public static final int LOWEST_OPENING = -130;// боковая граница просвета
    private Texture leftTube, rightTube;
    private Vector2 posLeftTube, posRightTube, posScore;
    private Random rand;
    private Rectangle boundsLeft, boundsRight;

    public Texture getLeftTube() {
        return leftTube;
    }

    public Texture getRightTube() {
        return rightTube;
    }

    public Vector2 getPosLeftTube() {
        return posLeftTube;
    }

    public Vector2 getPosRightTube() {
        return posRightTube;
    }

    public Tube(float y){
        leftTube = new Texture("tubeL.png");
        rightTube = new Texture("tubeR.png");
        rand = new Random();
        posLeftTube = new Vector2(rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING, y+400);
        posRightTube = new Vector2(posLeftTube.x - TUBE_GAP - rightTube.getHeight(), y+400);
        posScore = new Vector2(0, posLeftTube.y);
        boundsLeft = new Rectangle(posLeftTube.x, posLeftTube.y, leftTube.getWidth(), leftTube.getHeight());
        boundsRight = new Rectangle(posRightTube.x, posRightTube.y, rightTube.getWidth(), rightTube.getHeight());
    }

    public void reposition(float y){
        posLeftTube.set(rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING, y);
        posRightTube.set(posLeftTube.x - TUBE_GAP - rightTube.getHeight(), y);
        posScore.set(0, posLeftTube.y);
        boundsLeft.setPosition(posLeftTube.x, posLeftTube.y);
        boundsRight.setPosition(posRightTube.x, posRightTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsLeft) || player.overlaps(boundsRight);
    }

    public void dispose(){
        leftTube.dispose();
        rightTube.dispose();
    }
}
