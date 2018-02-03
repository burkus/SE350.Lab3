import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import java.util.List;
import java.util.LinkedList;

public class GardenLayout extends Application {
  Flower flower = new Flower(new Point2D(250, 250), Color.RED, true);
  Point2D lastPosition;
  FlowerBed flowerBed = new FlowerBed(new Point2D(50, 50), Color.BLUE, true);
  boolean circleHasBeenDragged = false;

  EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e) {
      e.setDragDetect(true);
      Point2D clickPoint = new Point2D(e.getX(), e.getY());
      System.out.println(clickPoint.getX() + " " + clickPoint.getY());
      String eventName = e.getEventType().getName();

      switch(eventName) {
        case("MOUSE_DRAGGED"):
          if(lastPosition != null) {
            if(flower.getCircle().contains(clickPoint)) {
              circleHasBeenDragged = true;
              double deltaX = clickPoint.getX() - lastPosition.getX();
              double deltaY = clickPoint.getY() - lastPosition.getY();
              flower.move(deltaX, deltaY);
            }
            else if(flowerBed.getRect().contains(clickPoint)) {
              circleHasBeenDragged = false;
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
  
  EventHandler<MouseEvent> releaseHandler = new EventHandler<MouseEvent>() {
	  public void handle(MouseEvent e) {
		  Point2D clickReleasedPoint = new Point2D(e.getX(), e.getY());
		  System.out.println("Click Released at: " + e.getX() + ", " + e.getY());
		  Point2D circleCenterPoint = new Point2D(flower.getCircle().getCenterX(),
				  								  flower.getCircle().getCenterY());
		  
		  if(flowerBed.containsCircle(flower.getCircle())) {
			  if(circleHasBeenDragged) {
				  System.out.println("Added");
				  flowerBed.add(flower);
			  }
		  }
		  else if(circleHasBeenDragged) {
			  flowerBed.remove(flower);
		  }
		  
		  lastPosition = null;
	  }
  };

  public void start(Stage primaryStage) {
    try {
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root, 500, 500);
			scene.setOnMouseDragged(dragHandler);
			scene.setOnMouseReleased(releaseHandler);
			primaryStage.setScene(scene);
			root.getChildren().add(flowerBed.getRect());
			root.getChildren().add(flower.getCircle());
			primaryStage.setTitle("Lab 3");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
  }
  
  public static void main(String[] args) {
	  launch(args);
  }
}
