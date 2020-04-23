package codes.matthewp.space.gui.mainmenu;

import codes.matthewp.space.Main;
import codes.matthewp.space.gui.Button;
import codes.matthewp.space.thread.UpdateThread;

public class PlayBtn extends Button {

    public PlayBtn(String text, int x, int y) {
        super(text, x,  y);
    }

    @Override
    public void onClick() {
        Main.menuState = null;
        Main.getInstance().updateThread = new UpdateThread();
        Main.getInstance().updateThread.start();
        System.out.println("CLICKED!");
    }
}
