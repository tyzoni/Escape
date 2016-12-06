package com.evil_racoon.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evil_racoon.game.states.GameStateManager;
import com.evil_racoon.game.states.MenuState;

public class Escape extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static Music BG;
	public static BitmapFont font;
	public static final String TITLE = "Escape";
	private GameStateManager gsm;
	private SpriteBatch batch;
	public static Preferences prefs;

	@Override
	public void create () {
		BG = Gdx.audio.newMusic(Gdx.files.internal("bg.mp3"));
		BG.setLooping(true);
		BG.play();
		font = new BitmapFont(Gdx.files.internal("text.fnt"));
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		prefs = Gdx.app.getPreferences("highPREF");
		if (!prefs.contains("HIGHSCORE")) {
			prefs.putInteger("HIGHSCORE", 0);
		}
	}

	public static void setHIGHSCORE(int val) {
		prefs.putInteger("HIGHSCORE", val);
		prefs.flush();
	}

	public static int getHIGHSCORE() {
		return prefs.getInteger("HIGHSCORE");
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
