import editor.ImageEditor;
import shapes.Circle;
import shapes.Polygon;
import shapes.CompoundShape;
import shapes.TextShape;
import shapes.Dot;
import shapes.Rectangle;
import java.awt.*;

public class Client {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();
        int[] xPoints = {250, 300, 320, 300, 250, 230};
        int[] yPoints = {120, 120, 80, 40, 40, 80};
        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),
                new CompoundShape(
                new Polygon(xPoints, yPoints, 6, Color.PINK),
                        new Dot(225, 35, Color.PINK),
                        new Dot(225, 125, Color.PINK),
                        new Dot(325, 35, Color.PINK),
                        new Dot(325, 125, Color.PINK)
                        ),
                new CompoundShape(
                        new TextShape(300, 200, "Бахаров Савватеев АВТ-918", Color.ORANGE )
                ),
                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED),
                        new Rectangle(130, 130, 100, 100, Color.YELLOW)
                ),
                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}
