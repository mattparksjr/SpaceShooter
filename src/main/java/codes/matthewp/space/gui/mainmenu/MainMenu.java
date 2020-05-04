package codes.matthewp.space.gui.mainmenu;

import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.gui.IGUI;

import java.awt.*;

public class MainMenu extends IGUI {

    public MainMenu() {
        super(MenuState.MAIN_MENU);
        buttonList.add(new PlayBtn("PLAY", 125, 150));
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g2d.drawString("Space shooter", 70, 100);
        g2d.drawString("V 0.0.1", 217, 290);
    }
}
