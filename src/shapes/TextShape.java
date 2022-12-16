package shapes;
import java.awt.*;
public class TextShape extends BaseShape {
    public String str;
    public TextShape( int x, int y, String str, Color color) {
        super(x, y, color);
        this.str = str;
    }
    @Override
    public int getWidth() {
        return str.length()*9;
    }
    @Override
    public int getHeight() {
        return 20;
    }
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics.drawString(str, x, y+20);
    }
}
