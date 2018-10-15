
package edu.anu.retrogame2018s2_frogger;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;

import edu.anu.retrogame2018s2_frogger.frogger.Direction;
import edu.anu.retrogame2018s2_frogger.frogger.FrogCanvas;
import edu.anu.retrogame2018s2_frogger.frogger.FrogPaint;
import edu.anu.retrogame2018s2_frogger.frogger.Path;

public class UsingCanvas implements FrogCanvas {
    Canvas canvas;
    Resources res;
    String thePackage;
    android.graphics.drawable.Drawable car_blue;
    android.graphics.drawable.Drawable car_red;
    android.graphics.drawable.Drawable car_yellow;
    android.graphics.drawable.Drawable car_green;
    android.graphics.drawable.Drawable log;
    android.graphics.drawable.Drawable frog_0;
    android.graphics.drawable.Drawable frog_1;
    android.graphics.drawable.Drawable frog_2;
    android.graphics.drawable.Drawable frog_3;

    android.graphics.drawable.Drawable frog_left_0;
    android.graphics.drawable.Drawable frog_left_1;
    android.graphics.drawable.Drawable frog_left_2;
    android.graphics.drawable.Drawable frog_left_3;

    android.graphics.drawable.Drawable frog_right_0;
    android.graphics.drawable.Drawable frog_right_1;
    android.graphics.drawable.Drawable frog_right_2;
    android.graphics.drawable.Drawable frog_right_3;

    android.graphics.drawable.Drawable frog_down_0;
    android.graphics.drawable.Drawable frog_down_1;
    android.graphics.drawable.Drawable frog_down_2;
    android.graphics.drawable.Drawable frog_down_3;

    public UsingCanvas(Canvas canvas, Resources res, String thePackage) {
        this.canvas = canvas;
        this.res = res;
        this.thePackage = thePackage;
        this.car_blue = createImageDrawble("car_blue");
        this.car_red = createImageDrawble("car_red");
        this.car_yellow = createImageDrawble("car_yellow");
        this.car_green = createImageDrawble("car_green");
        this.log = createImageDrawble("log");
//        this.frog_0 = createImageDrawble("frog_static");
//        this.frog_1 = createImageDrawble("frog_jump_1");
//        this.frog_2 = createImageDrawble("frog_jump_2");
//        this.frog_3 = createImageDrawble("frog_jump_3");
//
//        this.frog_left_0 = createImageDrawble("frog_static_left");
//        this.frog_left_1 = createImageDrawble("frog_jump_1_left");
//        this.frog_left_2 = createImageDrawble("frog_jump_2_left");
//        this.frog_left_3 = createImageDrawble("frog_jump_3_left");
//
//        this.frog_right_0 = createImageDrawble("frog_static_right");
//        this.frog_right_1 = createImageDrawble("frog_jump_1_right");
//        this.frog_right_2 = createImageDrawble("frog_jump_2_right");
//        this.frog_right_3 = createImageDrawble("frog_jump_3_right");
//
//        this.frog_down_0 = createImageDrawble("frog_static_down");
//        this.frog_down_1 = createImageDrawble("frog_jump_1_down");
//        this.frog_down_2 = createImageDrawble("frog_jump_2_down");
//        this.frog_down_3 = createImageDrawble("frog_jump_3_down");
    }

    Bitmap bitmap;

    @Override
    public void drawRect(float left, float top, float right, float bottom, FrogPaint frogPaint) {
        canvas.drawRect(left, top, right, bottom, getPaint(frogPaint));
    }

    @Override
    public void drawCircle(float cx, float cy, float radius, FrogPaint frogPaint) {
        canvas.drawCircle(cx, cy, radius, getPaint(frogPaint));
    }

    @Override
    public void drawPath(Path path, FrogPaint frogPaint) {
        canvas.drawPath(getPath(path), getPaint(frogPaint));
    }

    @Override
    public void drawText(String text, float x, float y, FrogPaint frogPaint) {
        canvas.drawText(text, x, y, getPaint(frogPaint));
    }

    @Override
    public void drawImage(String image, int left, int top, int right, int bottom, FrogPaint frogPaint) {
        android.graphics.drawable.Drawable d;
        switch (image) {
            case "car_blue":
                d = this.car_blue;
                break;
            case "car_red":
                d = this.car_red;
                break;
            case "car_yellow":
                d = this.car_yellow;
                break;
            case "car_green":
                d = this.car_green;
                break;
            case "frog_static":
                d = getFrogDrawable(image, frogPaint.getDirection());
                break;
            case "frog_jump_1":
                d = getFrogDrawable(image, frogPaint.getDirection());
                break;
            case "frog_jump_2":
                d = getFrogDrawable(image, frogPaint.getDirection());
                break;
            case "frog_jump_3":
                d = getFrogDrawable(image, frogPaint.getDirection());
                break;
            case "log":
                d = this.log;
                break;
            default:
                if (image.endsWith(".***")) {
                    image = image.substring(0, image.length() - 4);
                }
                int drawableResourceId = res.getIdentifier(image, "drawable", thePackage);
                d = res.getDrawable(drawableResourceId);
                break;

        }
        d.setBounds(left, top, right, bottom);
        d.draw(canvas);
    }

    private android.graphics.drawable.Drawable createImageDrawble(String image) {
        int drawableResourceId = res.getIdentifier(image, "drawable", thePackage);
        android.graphics.drawable.Drawable d = res.getDrawable(drawableResourceId);
        return d;
    }

    private android.graphics.drawable.Drawable getFrogDrawable(String image, Direction direction) {
        String s = "";
        if (direction != null)
            switch (direction) {
                case NORTH:
                    s = "_down";
                    break;
                case WEST:
                    s = "_left";
                    break;
                case EAST:
                    s = "_right";
                    break;
            }
        int drawableResourceId = res.getIdentifier(image + s, "drawable", thePackage);
        android.graphics.drawable.Drawable d = res.getDrawable(drawableResourceId);
        return d;
    }


    @Override
    public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, FrogPaint paint) {
        canvas.drawRoundRect(left, top, right, bottom, rx, ry, getPaint(paint));
    }

    static android.graphics.Paint getPaint(FrogPaint frogPaint) {

        android.graphics.Paint paint = new android.graphics.Paint();
        if (frogPaint == null) {
            return null;
        }
        if (frogPaint.getColor() != null) {
            paint.setColor(Color.parseColor(frogPaint.getColor()));
        }
        if (frogPaint.getStyleFill() != null) {
            paint.setStyle(android.graphics.Paint.Style.FILL);
        }
        if (frogPaint.getTextSize() != null) {
            paint.setTextSize(frogPaint.getTextSize());
        }
        if (frogPaint.getTextAlign() != null) {
            switch (frogPaint.getTextAlign()) {
                case Center:
                    paint.setTextAlign(Paint.Align.CENTER);
                    break;
            }
        }
        return paint;
    }

    static android.graphics.Path getPath(Path path) {
        android.graphics.Path p = new android.graphics.Path();
        if (path.size() >= 1) {
            p.moveTo(path.get(0)[0], path.get(0)[1]);
        }
        for (int i = 1; i < path.size(); i++) {
            p.lineTo(path.get(i)[0], path.get(i)[1]);
        }
        return p;
    }
}
