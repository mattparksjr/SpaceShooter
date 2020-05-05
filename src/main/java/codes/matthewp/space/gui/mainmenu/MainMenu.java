package codes.matthewp.space.gui.mainmenu;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.gui.componet.Button;
import codes.matthewp.space.gui.componet.IGUI;
import codes.matthewp.space.thread.UpdateThread;

import java.awt.*;

public class MainMenu extends IGUI {

    public MainMenu() {
        super(MenuState.MAIN_MENU);
        buttonList.add(new Button("PLAY", 125, 150, () -> {
            Main.menuState = null;
            Main.getInstance().updateThread = new UpdateThread();
            Main.getInstance().updateThread.start();
        }));
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
