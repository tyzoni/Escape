package com.evil_racoon.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by уе on 04.11.2016.
 */
public class Ap {
    public static int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velosity;
    public Rectangle apr, apr2, apr3, apr4, apr5;
    public Texture apt;

    public Ap(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        apt = new Texture("ap.png");
        apr = new Rectangle(x, position.y, apt.getWidth(), apt.getHeight());
        apr2 = new Rectangle(x + 136, position.y, 0, 0);
        apr3 = new Rectangle(x + 68, position.y + 27, 0, 0);
        apr4 = new Rectangle(x + 68 - 26, position.y - 79, 0, 0);
        apr5 = new Rectangle(x + 68 + 26, position.y - 79, 0, 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void update(float dt) {
        apr.set(apr.x, position.y - 27, 0, 0);//левое крыло
        apr2.set(apr.x + 136, position.y - 27, 0, 0);//правое крыло
        apr3.set(apr.x + 68, position.y, 0, 0);//нос
        apr4.set(apr.x + 68 - 26, position.y - 79 - 27, 0, 0);//левое хвостьвое крыло
        apr5.set(apr.x + 68 + 26, position.y - 79 - 27, 0, 0);//правое хвостовое крыло
        position.add(velosity.x, MOVEMENT * dt, 0);
        if (apr.x < 0)
            apr.x = 0;
        if (apr.x > 480 - 136)
            apr.x = 480 - 136;
        velosity.scl(1 / dt);
        apr.setPosition(apr.x, position.y - 27);
        apr2.setPosition(apr.x + 136, position.y - 27);
        apr3.setPosition(apr.x + 68, position.y);
        apr4.setPosition(apr.x + 68 - 26, position.y - 79 - 27);
        apr5.setPosition(apr.x + 68 + 26, position.y - 79 - 27);
    }

    public void smart() {
        getApr().set(Gdx.input.getX()/2-80, getApr().y, 0, 0);
        if (apr.x < 0)
            apr.x = 0;
        if (apr.x > 480 - 136)
            apr.x = 480 - 136;
    }

    public void left1() {
        apr.x -= 300 * Gdx.graphics.getDeltaTime();
    }

    public void right1() {
        apr.x += 300 * Gdx.graphics.getDeltaTime();
    }

    public Rectangle getApr(){
        return apr;
    }
    public Rectangle getApr2(){
        return apr2;
    }
    public Rectangle getApr3(){
        return apr3;
    }
    public Rectangle getApr4(){
        return apr4;
    }
    public Rectangle getApr5(){
        return apr5;
    }
    public Texture getApt(){
        return apt;
    }

    public void dispose(){
        apt.dispose();
    }
}
