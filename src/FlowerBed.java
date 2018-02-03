import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.LinkedList;

public class FlowerBed implements GardenComponent {
  private Rectangle rect;
  private Color color;
  private Point2D position;
  private boolean moveable;
  private List<GardenComponent> components = new LinkedList<GardenComponent>();

  public FlowerBed(Point2D position, Color color, boolean moveable) {
    this.position = position;
    this.color = color;
    this.moveable = moveable;
    rect = new Rectangle();
    rect.setX(position.getX());
    rect.setY(position.getY());
    rect.setWidth(100);
    rect.setHeight(100);
    rect.setStroke(Color.BLACK);
    rect.setStrokeWidth(1);
    rect.setFill(null);
  }

  public Rectangle getRect() {
    return rect;
  }

  public void move(double deltaX, double deltaY) {
    rect.setX(rect.getX() + deltaX);
    rect.setY(rect.getY() + deltaY);
    for(GardenComponent comp : components) {
      comp.move(deltaX, deltaY);
    }
  }
}
