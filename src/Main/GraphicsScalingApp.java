package Main;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GraphicsScalingApp extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage stage) {
    final StackPane stack = new StackPane(new Label("Text"));

    Parent zoomPane = createZoomPane(stack);

    StackPane pane = new StackPane(zoomPane);

    Scene scene = new Scene(pane);

    stage.setTitle("Zoomy");
    stage.setScene(scene);
    stage.show();
  }

  private Parent createZoomPane(final Pane pane) {
      
    final double SCALE_DELTA = 1.1;
    final StackPane zoomPane = new StackPane();

    zoomPane.getChildren().add(pane);

    zoomPane.setOnScroll((ScrollEvent event) -> {
        event.consume();

        if (event.getDeltaY() == 0) {
            return;
        }

        double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA
                : 1 / SCALE_DELTA;
        
        zoomPane.setScaleX(zoomPane.getScaleX() * scaleFactor);
        zoomPane.setScaleY(zoomPane.getScaleY() * scaleFactor);

    });

    return zoomPane;
  }

}