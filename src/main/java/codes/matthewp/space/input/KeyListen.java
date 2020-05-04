package codes.matthewp.space.input;

import codes.matthewp.space.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListen implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        // Required, but ignored
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            Main.getInstance().getPlayer().setRightDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Main.getInstance().getPlayer().setLeftDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            Main.getInstance().getPlayer().setUpDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            Main.getInstance().getPlayer().setDownDown(true);
        }
        if(e.getKeyCode() == KeyEvent.VK_0) {
            Main.getInstance().showGameObjColliders = !Main.getInstance().showGameObjColliders;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            Main.getInstance().getPlayer().setRightDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Main.getInstance().getPlayer().setLeftDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            Main.getInstance().getPlayer().setUpDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            Main.getInstance().getPlayer().setDownDown(false);
        }
    }
}
