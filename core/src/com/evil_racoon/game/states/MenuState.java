package com.evil_racoon.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evil_racoon.game.Escape;

/**
 * Created by уе on 02.11.2016.
 */
public class MenuState extends State {
    private Texture bg;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Escape.WIDTH, Escape.HEIGHT);
        bg = new Texture("bg1.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            PlayState.SCORE = 0;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        Escape.font.draw(sb,"Touch to \nplay",30,600);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        System.out.println("MenuState Disposed");
    }
}
