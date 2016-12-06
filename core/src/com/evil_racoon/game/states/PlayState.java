package com.evil_racoon.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evil_racoon.game.Escape;
import com.evil_racoon.game.sprites.Ap;
import com.evil_racoon.game.sprites.Tube;

/**
 * Created by уе on 02.11.2016.
 */
public class PlayState extends State {

    public static final int TUBE_SPACING = 200;//растояние по высоте
    public static final int TUBE_COUNT = 4;//кол-во комплектов труб
    public static int SCORE = 0;//счетчик
    public static Music PS;
    private Ap ap;
    private Texture bg;
    private Vector2 posbg1, posbg2;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Escape.BG.play();
        PS = Gdx.audio.newMusic(Gdx.files.internal("PS.mp3"));
        PS.setLooping(true);
        PS.play();
        ap = new Ap(480 / 2, 200);
        camera.setToOrtho(false, Escape.WIDTH, Escape.HEIGHT);
        bg = new Texture("bg1.png");
        posbg1 = new Vector2(0, camera.position.y - camera.viewportHeight);
        posbg2 = new Vector2(0,(camera.position.y - camera.viewportHeight) + bg.getHeight());
        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_HEIGHT)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched())
            ap.smart();
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            ap.left1();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            ap.left1();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            ap.right1();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            ap.right1();
    }

    @Override
    public void update(float dt) {
        updateBg();
        ap.update(dt);
        handleInput();
        camera.position.y = ap.getPosition().y + 200;
        if (SCORE % 5 == 0) {
            ap.MOVEMENT = ap.MOVEMENT + 1;
            if (SCORE <= 0)
                ap.MOVEMENT = 100;
        }
        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.y - (camera.viewportHeight / 2) > tube.getPosLeftTube().y + tube.getLeftTube().getWidth()) {
                tube.reposition(tube.getPosLeftTube().y + ((Tube.TUBE_HEIGHT + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(ap.getApr())) {//столкновение с левым крылом
                gsm.set(new OverState(gsm));
                System.out.println("1");
            }
            if (tube.collides(ap.getApr2())) {//столкновение с правым крылом
                gsm.set(new OverState(gsm));
                System.out.println("2");
            }
            if (tube.collides(ap.getApr3())) {//столкновение с носом
                gsm.set(new OverState(gsm));
                System.out.println("3");
            }
            if (tube.collides(ap.getApr4())) {//столкновение с левым задним крылом
                gsm.set(new OverState(gsm));
                System.out.println("4");
            }
            if (tube.collides(ap.getApr5())) {//столкновение с правым задним крылом
                gsm.set(new OverState(gsm));
                System.out.println("5");
            }
            if (tube.getPosLeftTube().y >= ap.apr.y)
                    SCORE = SCORE +1;
        }
        if (SCORE > Escape.getHIGHSCORE()) {
            Escape.setHIGHSCORE(SCORE);
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, posbg1.x, posbg1.y);
        sb.draw(bg, posbg2.x, posbg2.y);
        sb.draw(ap.getApt(), ap.getApr().x, ap.getApr().y-85);
        for (Tube tube : tubes) {
            sb.draw(tube.getLeftTube(), tube.getPosRightTube().x, tube.getPosRightTube().y);
            sb.draw(tube.getRightTube(), tube.getPosLeftTube().x, tube.getPosRightTube().y);
        }
        Escape.font.draw(sb, "Score: " + SCORE, Escape.WIDTH/2-230, camera.position.y+150);
        Escape.font.draw(sb, "Best:  " + Escape.getHIGHSCORE(), Escape.WIDTH/2-230, camera.position.y+220);
        sb.end();
    }

    private void updateBg(){
        if (camera.position.y - (camera.viewportHeight/2) > posbg1.y + bg.getHeight())
            posbg1.add(0, bg.getHeight() * 2);
        if (camera.position.y - (camera.viewportHeight/2) > posbg2.y + bg.getHeight())
            posbg2.add(0, bg.getHeight() * 2);
    }

    @Override
    public void dispose() {
        bg.dispose();
        ap.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");
        PS.dispose();
        Escape.BG.dispose();
    }
}