import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower implements GardenComponent {
  private Color color;
  private Point2D position;
  private boolean moveable;
  private Circle circle;

  public Flower(Point2D position, Color color, boolean moveable) {
      this.color = color;
      this.position = position;
      this.moveable = moveable;
      circle = new Circle();
      circle.setCenterX(position.getX());
      circle.setCenterY(position.getY());
      circle.setRadius(10);
      circle.setFill(color);
      circle.setStroke(Color.BLACK);
      circle.setStrokeWidth(1);
  }

  public Circle getCircle() {
    return circle;
  }

  public void move(double deltaX, double deltaY) {
    position.add(new Point2D(deltaX, deltaY));
    circle.setCenterX(circle.getCenterX() + deltaX);
    circle.setCenterY(circle.getCenterY() + deltaY);
  }
}
