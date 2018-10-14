package edu.anu.retrogame2018s2_frogger.frogger.scene.classic;

import edu.anu.retrogame2018s2_frogger.frogger.Frog;
import edu.anu.retrogame2018s2_frogger.frogger.FrogCanvas;
import edu.anu.retrogame2018s2_frogger.frogger.GameSetting;

public class Log extends MovingItem implements MoveAlong, ActiveItem {
    Log(MovingParameters movingParameters, GameSetting gameSetting) {
        super(movingParameters, gameSetting);
        this.width = 250;
        this.height = 100;
        frogPaint.setColor("#795548");
    }

    @Override
    public void draw(FrogCanvas frogCanvas) {
        frogCanvas.drawImage("log", x,mp.getY(), x + width, (mp.getY() + height), frogPaint);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean activity(Frog frog) {
        if (this.Collision(frog)) {
            frog.logOn(this);
        }
        return false;
    }

    @Override
    public void moveAlong(Frog frog) {
        if (frog.getX() > 0 && frog.getX() < gameSetting.getWidth())
            if (mp.getDir()) {
                frog.setX(frog.getX() + mp.getSpeed());
            } else {
                frog.setX(frog.getX() - mp.getSpeed());
            }
    }
}
