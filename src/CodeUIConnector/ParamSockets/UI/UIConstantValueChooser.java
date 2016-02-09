/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.UI;

import CodeUIConnector.SocketPane.UISocket;
import java.util.Collection;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Morai
 */
public class UIConstantValueChooser extends UISocket {

    public static enum Type {

        INPUT, OUTPUT
    };

    private final Color COLOR_IDLE = Color.LIGHTGREY;
    private final Color COLOR_CONNECTED = Color.LIGHTBLUE;
    private final Color COLOR_AVAILABLE = Color.LIGHTGREEN;

    private ObjectProperty<Bounds> plugSceneBounds = new SimpleObjectProperty<>();
    private final HBox hBox = new HBox();
    private final Rectangle plug = new Rectangle();
    private final ComboBox valueBox = new ComboBox();

    public UIConstantValueChooser(Type type, double spacing, Collection values) {

        valueBox.getItems().addAll(values);
        valueBox.getSelectionModel().selectFirst();
        valueBox.setStyle("-fx-font-size : 7pt;");

        hBox.setSpacing(spacing);
        hBox.setAlignment(Pos.CENTER);
        

        plug.setWidth(4);
        plug.setHeight(20);
        plug.setFill(COLOR_IDLE);
        hBox.getChildren().addAll(plug, valueBox);
        if (type == Type.OUTPUT) {
            setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        getChildren().add(hBox);

        Platform.runLater(() -> {
            plugSceneBounds.set(plug.localToScene(plug.getBoundsInLocal()));
        });

        localToSceneTransformProperty().addListener((observable, oldValue, newValue) -> {
            plugSceneBounds.set(plug.localToScene(plug.getBoundsInLocal()));
        });

    }

    @Override
    public ObjectProperty<Bounds> getPlugBoundsProperty() {
        return plugSceneBounds;
    }

    @Override
    public void showConnected() {
        super.showConnected();
        plug.setFill(COLOR_CONNECTED);

        super.showConnected();
    }

    @Override
    public void showAvailable() {
        plug.setFill(COLOR_AVAILABLE);

        super.showAvailable();
    }

    @Override
    public void showIdle() {
        plug.setFill(COLOR_IDLE);

        super.showIdle();
    }

    public ComboBox getValueBox() {
        return valueBox;
    }
    
    

}
