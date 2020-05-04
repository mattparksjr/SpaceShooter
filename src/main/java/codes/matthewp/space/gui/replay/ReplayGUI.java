package codes.matthewp.space.gui.replay;

import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.gui.IGUI;

import java.awt.*;

public class ReplayGUI extends IGUI {

    private ReplayReason replayReason;

    public ReplayGUI(ReplayReason replayReason, MenuState state) {
        super(state);
        this.replayReason = replayReason;
        buttonList.add(new BtnMainMenu("REPLAY", 115, 140));
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
