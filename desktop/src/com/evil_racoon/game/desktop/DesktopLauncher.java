package com.evil_racoon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evil_racoon.game.Escape;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Escape.WIDTH;
		config.height = Escape.HEIGHT;
		config.title = Escape.TITLE;
		new LwjglApplication(new Escape(), config);
	}
}
