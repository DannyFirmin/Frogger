package edu.anu.retrogame2018s2_frogger.frogger.scene.classic;


import edu.anu.retrogame2018s2_frogger.frogger.ControlPanel;
import edu.anu.retrogame2018s2_frogger.frogger.Dialog;
import edu.anu.retrogame2018s2_frogger.frogger.Direction;
import edu.anu.retrogame2018s2_frogger.frogger.Frog;
import edu.anu.retrogame2018s2_frogger.frogger.FrogCanvas;
import edu.anu.retrogame2018s2_frogger.frogger.GameSetting;
import edu.anu.retrogame2018s2_frogger.frogger.SceneEndInformation;
import edu.anu.retrogame2018s2_frogger.frogger.scene.Scene;

public class ClassicFroggerScene implements Scene {
    private GameSetting gameSetting;
    private Frog frog;
    private Logs logs;
    private Cars cars;
    private Background background;
    private ControlPanel controlPanel;

    public Dialog getDialog() {
        return dialog;
    }

    private FrogHole frogHole;
    private Dialog dialog = null;

    public ClassicFroggerScene(GameSetting gameSetting) {
        this.isActive = true;
        this.gameSetting = gameSetting;
        this.gameSetting.setFrog(frog);
        this.frog = new Frog(this);
        this.gameSetting.setFrog(this.frog);

        background = new Background(this);
        cars = new Cars(this);
        logs = new Logs(this);
        this.frogHole = new FrogHole(this);
     //   controlPanel = new ControlPanel(gameSetting.getWidth() - 300, gameSetting.getHeight() - 300, this);
    }

    @Override
    public void draw(FrogCanvas frogCanvas) {
        frogHole.draw(frogCanvas);
        background.draw(frogCanvas);
        logs.draw(frogCanvas);
        frog.draw(frogCanvas);
        cars.draw(frogCanvas);
        controlPanel.draw(frogCanvas);
        if (isActive && this.frogHole.inHole(frog)) {
            if (this.frogHole.isFinished()) {
                sceneOver(new SceneEndInformation(true, "Mission Accomplished !"));
            } else {
                this.frog.resetFrog();
            }
        }
    }

    @Override
    public void step() {
        if (isActive) {
            cars.step();
            if (this.frog.getLog() == null && this.frog.getY() >= gameSetting.get("riverTop") && frog.getY() <= gameSetting.get("riverBottom")) {
                sceneOver(new SceneEndInformation(false, "You dropped into the river !"));
            }
            frog.step();
            logs.step();
        } else {
            if (dialog != null)
                dialog.step();
        }
    }

    @Override
    public void onEnterScene(FrogCanvas frogCanvas) {

    }

    @Override
    public void onFinishScene(FrogCanvas frogCanvas) {
        if (this.dialog != null) {
            this.dialog.draw(frogCanvas);
        }
    }

    @Override
    public void sceneOver(SceneEndInformation sceneEndInformation) {
        isActive = false;
        this.dialog = new Dialog(sceneEndInformation, gameSetting);
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public GameSetting getGameSetting() {
        return gameSetting;
    }


    public void move(ControlPanel.Direction direction) {
        this.frog.move(direction);
    }

    @Override
    public boolean isMoveble(int x, int y) {
        return this.frogHole.isOkToMove(x, y);
    }

    @Override
    public Integer onTouch(float x, float y, boolean isDown) {
        if (isActive) {
            this.controlPanel.onTouch(x, y, isDown);
        } else if (this.dialog != null) {
            if (this.dialog.gotClick(x, y)) {
                return 0;
            }
        }
        return null;
    }

    private boolean isActive;

    @Override
    public boolean isActive() {
        return isActive;
    }
}