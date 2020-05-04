package codes.matthewp.space.game;

import codes.matthewp.space.Main;

import java.awt.*;

public class GameObject {

    public Image img;
    public int x;
    public int y;
    public int height;
    public int width;
    public int health;
    public Rectangle hitbox;

    public GameObject(Image img, int x, int y, int height, int width, int health) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.health = health;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
        // USED TO DEBUG HITBOXES, and coords
        if (Main.getInstance().showGameObjColliders) {
            g.setColor(Color.GREEN);
            g.drawRect(x, y, width, height);
            g.drawString("(" + x + "," + y + ")", x + 16, y - 7);
        }
    }

    public void update() {
        hitbox.setLocation(x, y);
    }
}
