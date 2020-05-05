package codes.matthewp.space.game.object;

import codes.matthewp.space.Main;

import java.awt.*;

public class GameObject implements Cloneable {

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Type getType() {
        return Type.OTHER;
    }

    public enum Type {
        ENEMY,
        OTHER,
    }

}
