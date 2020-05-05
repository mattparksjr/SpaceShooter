package codes.matthewp.space.gui.replay;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.game.object.Player;
import codes.matthewp.space.gui.componet.Button;
import codes.matthewp.space.gui.componet.IGUI;
import codes.matthewp.space.thread.UpdateThread;

import java.awt.*;

public class ReplayGUI extends IGUI {

    private ReplayReason replayReason;

    public ReplayGUI(ReplayReason replayReason, MenuState state) {
        super(state);
        this.replayReason = replayReason;
        buttonList.add(new Button("REPLAY", 115, 140, () -> {
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
        }));
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        if(replayReason == ReplayReason.LOSS) {
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            graphics.drawString("YOU LOST!", 88, 100);
        } else {
            graphics.setColor(Color.GREEN);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            graphics.drawString("YOU WIN!", 100, 95);
        }
    }
}
