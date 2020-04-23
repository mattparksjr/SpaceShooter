package codes.matthewp.space.game;

import codes.matthewp.space.Main;
import codes.matthewp.space.util.MathHelp;

import java.awt.*;

public class Player extends GameObject {

    private boolean isLeftDown = false;
    private boolean isRightDown = false;
    private boolean isUpDown = false;
    private boolean isDownDown = false;
    private boolean isMouseDown = false;
    private long lastShotAt = 0L;
    public long lastBlinkAt = 0L;

    public Player(Image img, int x, int y, int height, int width, int health) {
        super(img, x, y, height, width, health);
    }

    @Override
    public void update() {
        // Movement
        if (isLeftDown()) {
            x--;
        }
        if (isRightDown()) {
            x++;
        }

        if (isDownDown()) {
            y++;
        }
        if (isUpDown()) {
            y--;
        }
        x = MathHelp.clamp(x, 5, 280);
        y = MathHelp.clamp(y, 28, 280);

        // Shooting
        if (isMouseDown && (System.currentTimeMillis() > (lastShotAt + 1000))) {
            lastShotAt = System.currentTimeMillis();
            Main.getInstance().getBulletList().add(new Bullet(x, y, this));
        }

        Main.getInstance().getCurrentEnemies().removeIf(enemyBasic -> hitbox.intersects(enemyBasic.hitbox));
        if(Main.getInstance().getCurrentEnemies().isEmpty()) {

        }
        super.update();
    }

    public void checkHealth() {
        if (health <= 0) {
            Main.getInstance().endGame();
        } else {
            Main.getInstance().getPlayer().img = Main.getImg("spaceShooterPlayerLight.png");
            Main.getInstance().getPlayer().lastBlinkAt = System.currentTimeMillis();

            Thread blink = new Thread(() -> {
                boolean run = true;
                while (run) {
                    if (System.currentTimeMillis() > Main.getInstance().getPlayer().lastBlinkAt + 1000) {
                        Main.getInstance().getPlayer().lastBlinkAt = System.currentTimeMillis();
                        Main.getInstance().getPlayer().img = Main.getImg("spaceShooterPlayer.png");
                        run = false;
                    }
                }
            });
            blink.start();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("HEALTH: " + health, 5, 292);
    }

    public boolean isLeftDown() {
        return isLeftDown;
    }

    public void setLeftDown(boolean leftDown) {
        isLeftDown = leftDown;
    }

    public boolean isRightDown() {
        return isRightDown;
    }

    public void setRightDown(boolean rightDown) {
        isRightDown = rightDown;
    }

    public boolean isMouseDown() {
        return isMouseDown;
    }

    public void setMouseDown(boolean mouseDown) {
        isMouseDown = mouseDown;
    }

    public boolean isUpDown() {
        return isUpDown;
    }

    public void setUpDown(boolean upDown) {
        isUpDown = upDown;
    }

    public boolean isDownDown() {
        return isDownDown;
    }

    public void setDownDown(boolean downDown) {
        isDownDown = downDown;
    }
}
