import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import java.util.List;
import java.util.LinkedList;

public class GardenLayout extends Application {
  Flower flower = new Flower(new Point2D(250, 250), Color.RED, true);
  Point2D lastPosition;
  FlowerBed flowerBed = new FlowerBed(new Point2D(50, 50), Color.BLUE, true);

  EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e) {
      e.setDragDetect(true);
      Point2D clickPoint = new Point2D(e.getX(), e.getY());
      System.out.println(clickPoint.getX() + " " + clickPoint.getY());
      String eventName = e.getEventType().getName();

      switch(eventName) {
        case("MOUSE_DRAGGED"):
          if(lastPosition != null) {
            if(flower.getCircle().contains(clickPoint)) {
              double deltaX = clickPoint.getX() - lastPosition.getX();
              double deltaY = clickPoint.getY() - lastPosition.getY();
              flower.move(deltaX, deltaY);
            }
            else if(flowerBed.getRect().contains(clickPoint)) {
              double deltaX = clickPoint.getX() - lastPosition.getX();
              double deltaY = clickPoint.getY() - lastPosition.getY();
              flowerBed.move(deltaX, deltaY);
            }
          }
          break;
      }
      lastPosition = clickPoint;
    }
  };

  public void start(Stage primaryStage) {
    try {
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root, 500, 500);
      scene.setOnMouseDragged(mouseHandler);
			primaryStage.setScene(scene);
      root.getChildren().add(flower.getCircle());
      root.getChildren().add(flowerBed.getRect());
			primaryStage.setTitle("Lab 3");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
  }
}
