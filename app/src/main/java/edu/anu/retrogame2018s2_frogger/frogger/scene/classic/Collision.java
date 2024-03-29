package edu.anu.retrogame2018s2_frogger.frogger.scene.classic;
/*
    @Author: Yang Yu
 */
public abstract class Collision {
    //collision detect of all objects
    public boolean collision(Spirit spirit, int x, int y, int height, int width) {
        if (spirit.getY() < y + height && spirit.getY() >= y) {
            if (spirit.getX() + (spirit.getRadius() * 0.48) >= x && spirit.getX() - (spirit.getRadius() * 0.48) <= x + width) {
                return true;
            }
        }
        return false;
    }
}
