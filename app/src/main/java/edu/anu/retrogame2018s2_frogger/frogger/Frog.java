package edu.anu.retrogame2018s2_frogger.frogger;

import edu.anu.retrogame2018s2_frogger.frogger.scene.Scene;
import edu.anu.retrogame2018s2_frogger.frogger.scene.classic.Log;
import edu.anu.retrogame2018s2_frogger.frogger.scene.classic.Spirit;

public class Frog implements Spirit {
    public Frog(Scene scene) {
        this.x = scene.getGameSetting().getWidth() / 2;
        frogPaint.setColor("#1b5e20");
        this.scene = scene;
        radius = (int) (scene.getGameSetting().getHeight() * 0.05);
        this.y = scene.getGameSetting().getHeight() - radius;
    }

    public void resetFrog() {
        this.x = scene.getGameSetting().getWidth() / 2;
        this.y = scene.getGameSetting().getHeight() - radius;
    }

    int y, x;
    private Scene scene;
    private int radius;
    private Log log = null;
    private Direction direction = null;
    private FrogPaint frogPaint = new FrogPaint();

    private int FrogStatus = 0;
    private int currentStatusTimeLeft = 5;

    public void draw(FrogCanvas frogCanvas) {
        frogPaint.setDirection(direction);//change image for different frog state when moving
        String image="frog_static";
        switch (FrogStatus) {
            case 0:
                image="frog_static";
                break;
            case 1:
                image="frog_jump_1";
                break;
            case 2:
                image="frog_jump_2";
                break;
            case 3:
                image="frog_jump_3";
                break;
        }
        if(direction!=null)
        switch (direction){
            case EAST:
                image+="_right";
                break;
            case WEST:
                image+="_left";
                break;
            case NORTH:
                image+="_down";
                break;
        }

        frogCanvas.drawImage(image, x - radius / 2, y - radius / 2, x + radius / 2, y + radius / 2, frogPaint);
    }

    public void step() {
        if (this.direction != null) {
            if (direction == Direction.EAST) {
                if (this.scene.isMoveble(x + 6, this.y))
                    this.x += 6;
            } else if (direction == Direction.SOUTH) {
                if (this.scene.isMoveble(x, this.y - 6))
                    this.y -= 6;
            } else if (direction == Direction.NORTH) {
                if (this.scene.isMoveble(x, this.y + 6))
                    this.y += 6;
            } else if (direction == Direction.WEST) {
                if (this.scene.isMoveble(x - 6, this.y))
                    this.x -= 6;
            }
        }

        if (this.log != null) {
            if (!this.log.Collision(this)) {
                this.log = null;
                return;
            }
            log.moveAlong(this);
        }

        if (direction != null) {
            //if user touch the direction will not be null
            //loop to change the frog image to make the jumping animation
            this.currentStatusTimeLeft -= 1;
            if (currentStatusTimeLeft <= 0) {
                this.FrogStatus = (FrogStatus + 1) % 4;
                currentStatusTimeLeft = 5;
            }
            if (!this.scene.getGameSetting().getSoundControl().isSoundPlaying())
                this.scene.getGameSetting().getSoundControl().playSound("jump_music.mp3");
        } else {
            this.FrogStatus = 0;
            if (this.scene.getGameSetting().getSoundControl().isSoundPlaying())
                this.scene.getGameSetting().getSoundControl().stopSound();
        }
    }

    public Log getLog() {
        return log;
    }

    public void move(Direction direction) {
        this.direction = direction;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void logOn(Log log) {
        this.log = log;
    }
}
