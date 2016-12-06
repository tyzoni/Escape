package com.evil_racoon.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evil_racoon.game.Escape;

/**
 * Created by уе on 02.11.2016.
 */
public class OverState extends State {
    public static Sound OS;
    private Texture bg;
    private Texture Over;

    public OverState(GameStateManager gsm) {
        super(gsm);
        PlayState.PS.pause();
        Escape.BG.pause();
        camera.setToOrtho(false, Escape.WIDTH, Escape.HEIGHT);
        bg = new Texture("bg1.png");
        Over = new Texture("gameover.png");
        OS = Gdx.audio.newSound(Gdx.files.internal("OS.mp3"));
        OS.play();
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

    @Override //отрисовка элементов экрана
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(Over, camera.position.x - Over.getWidth() / 2, camera.position.y+250);
        Escape.font.draw(sb, "Your: " + PlayState.SCORE, 30, camera.position.y+200);
        Escape.font.draw(sb, "Best: " + Escape.getHIGHSCORE(), 30, camera.position.y+120);
        Escape.font.draw(sb,"Touch to \nreplay",30,400);
        sb.end();
    }

    @Override //выгрузка из памяти текстур и прочего
    public void dispose() {
        bg.dispose();
        Over.dispose();
        OS.dispose();
        Escape.BG.dispose();
        PlayState.PS.dispose();
        System.out.println("OverState Disposed");
    }
}