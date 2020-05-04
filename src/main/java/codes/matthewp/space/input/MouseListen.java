package codes.matthewp.space.input;

import codes.matthewp.space.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.getInstance().getGuiMaster().getCurrentGUI().notifyClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Main.getInstance().getPlayer().setMouseDown(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Main.getInstance().getPlayer().setMouseDown(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // ignored
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("DANGER, LEFT SCREEN");

    }
}
