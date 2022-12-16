package shapes;

import java.awt.*;

public class Polygon extends BaseShape {
    public int nPoints;
    public int[] xPoints;
    public int[] yPoints;
    public Polygon(int[] xPoints, int[] yPoints, int nPoints, Color color) {
        super(xPoints[5], yPoints[4], color);
        this.nPoints = nPoints;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }
    @Override
    public int getWidth() {
        return (xPoints[2]-xPoints[5]);
    }
    @Override
    public int getHeight() {
        return (yPoints[0]-yPoints[4]);
    }
    @Override
    public void move(int x, int y) {
        super.move(x, y);
        for (int i=0; i<xPoints.length; i++) {
            this.xPoints[i] += x;
            this.yPoints[i] += y;
        }
    }
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.fillPolygon(xPoints, yPoints, 6);
    }
}
