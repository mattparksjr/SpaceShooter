package codes.matthewp.space.gui.replay;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.object.Player;
import codes.matthewp.space.gui.Button;
import codes.matthewp.space.thread.UpdateThread;

public class BtnMainMenu extends Button {

    public BtnMainMenu(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void onClick() {
        Main.menuState = null;
        Main.getInstance().runUpdate = false;
        Main.getInstance().updateThread = new UpdateThread();
        Main.getInstance().runUpdate = true;
        Main.getInstance().updateThread.start();
        Main.getInstance().getWaveMaster().reset();
        Main.getInstance().getCurrentEnemies().clear();
        Main.getInstance().getBulletList().clear();
        Main.getInstance().setPlayer(new Player(Main.getImg("spaceShooterPlayer.png"), 150, 280, 16, 16, 20));
        Main.getInstance().getWaveMaster().startWaves();
    }
}
