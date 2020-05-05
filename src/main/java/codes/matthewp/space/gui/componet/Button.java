package codes.matthewp.space.gui.componet;

import codes.matthewp.space.Main;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button {

    private Rectangle collider;
    private int x;
    private int y;
    private double width;
    private double height;
    private String text;
    private Font f;
    private BtnClick btnClick;
    private Image img = Main.getImg("btnBase.png");

    public Button(String text, int x, int y, BtnClick func) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.btnClick = func;
        this.f = new Font("TimesRoman", Font.PLAIN, 18);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        int textwidth = (int) (f.getStringBounds(text, frc).getWidth());
        int textheight = (int) (f.getStringBounds(text, frc).getHeight());
        this.collider = new Rectangle(x - 10, y - textheight + 2, textwidth + 20, textheight + 5);
    }

    public void onClick() {
        btnClick.onClick();
    }

    public void render(Graphics2D g2d) {
        width = f.getStringBounds(text, g2d.getFontRenderContext()).getWidth();
        height = f.getStringBounds(text, g2d.getFontRenderContext()).getHeight();
        g2d.setFont(f);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
        g2d.drawImage(img, x - 10, y - (int) height + 2, (int) width + 20, (int) height + 5, null);
        if (Main.getInstance().showGameObjColliders) {
            g2d.setColor(Color.GREEN);
            g2d.drawRect((int) collider.getX(), (int) collider.getY(), (int) collider.getWidth(), (int) collider.getHeight());
        }
    }

    public Rectangle getCollider() {
        return collider;
    }
}
