package codes.matthewp.space.gui;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.MenuState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IGUI {

    public List<Button> buttonList;
    private MenuState state;

    public IGUI(MenuState state) {
        this.state = state;
        buttonList = new ArrayList<>();
    }

    public MenuState getMenuState() {
        return state;
    }

    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        for(Button btn : buttonList) {
            btn.render(g2d);
        }
    }

    public void notifyClick(int x, int y) {
        Rectangle rectangle = new Rectangle(x, y, 1, 1);
        for(Button button : buttonList) {
            if(rectangle.intersects(button.getCollider())) {
                button.onClick();
            }
        }
    }

    public Image getImg(String path) {
        return Main.getImg(path);
    }
}
