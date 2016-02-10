package CodeUIConnector;

import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Morai
 */
public final class ZoomPane extends Pane {

    private final double SCALE_DELTA = 1.1;

    public ZoomPane() {

        this.setOnScroll((ScrollEvent event) -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }

            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA
                    : 1 / SCALE_DELTA;

            this.setScaleX(this.getScaleX() * scaleFactor);
            this.setScaleY(this.getScaleY() * scaleFactor);

        });
        
    }


}
