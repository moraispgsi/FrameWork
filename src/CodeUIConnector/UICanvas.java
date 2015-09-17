/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import DynamicClassUtils.DynamicClassUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class UICanvas extends Region {

    private final UISocketCanvasSet socketSet = new UISocketCanvasSet();
    private final Map<VariableInputSocket, UISocketIOLine> lineMap = new HashMap<>();

    private String filePath;
    private Method method;

    private ObjectProperty<Point2D> sceneBoundsProperty = new SimpleObjectProperty<>();

    private VariableOutputSocket draggingSocket;
    private HorizontalCurvedLine draggingLine;

    private final Double mouseHOffset = 10d;
    private final Double mouseVOffset = 10d;
    private final DoubleProperty mouseX = new SimpleDoubleProperty();
    private final DoubleProperty mouseY = new SimpleDoubleProperty();

    public UICanvas(String filePath, Method method) {
        this.filePath = filePath;
        this.method = method;

        this.setCursor(Cursor.OPEN_HAND);

        setOnMouseDragOver(e -> {

            mouseX.set(mouseHOffset + e.getX());
            mouseY.set(mouseVOffset + e.getY());

        });

        setOnMouseMoved(e -> {

            mouseX.set(mouseHOffset + e.getX());
            mouseY.set(mouseVOffset + e.getY());

        });

        socketSet.getVariableInputSockets()
                .addListener((SetChangeListener.Change<? extends VariableInputSocket> change) -> {

                    addInputSocketListener(change);
                });

        socketSet.getVariableOutputSockets()
                .addListener((SetChangeListener.Change<? extends VariableOutputSocket> change) -> {

                    addOutputSocketListener(change);
                });

        localToSceneTransformProperty().addListener((observable, oldValue, newValue) -> {
            sceneBoundsProperty.setValue(this.localToScene(0, 0));
        });

        getChildren().addListener((ListChangeListener.Change<? extends Node> change) -> {
            childrenChangeListener(change);
        });

        UIStartMethod startUI = new UIStartMethod(filePath, method);
        getChildren().add(startUI);

        UICondition coditionUI = new UICondition();
        getChildren().add(coditionUI);

        if (!method.getReturnType().equals(Void.TYPE)) {

            UIEndMethod endUI = new UIEndMethod(filePath, method);
            getChildren().add(endUI);

        }
    }

    private void addInputSocketListener(SetChangeListener.Change<? extends VariableInputSocket> change) {

        if (change.wasAdded()) {

            VariableInputSocket variableInputSocket = change.getElementAdded();

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                if (draggingSocket == null || !DynamicClassUtils.primitiveToWrapper(variableInputSocket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(draggingSocket.getVariableType()))) {
                    return;
                }

                UISocket inputUISocket = variableInputSocket.getUISocket();
                UISocket outputUISocket = draggingSocket.getUISocket();
                boolean containsKey = lineMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(lineMap.get(variableInputSocket).getOutputSocket() == outputUISocket);

                if (changed) {

                    lineMap.get(variableInputSocket).getStrokeDashArray().addAll(2d, 10d);

                }

                draggingLine.bindRightSocket(sceneBoundsProperty, inputUISocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                if (draggingSocket == null || !DynamicClassUtils.primitiveToWrapper(variableInputSocket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(draggingSocket.getVariableType()))) {
                    return;
                }

                UISocket inputUISocket = variableInputSocket.getUISocket();
                UISocket outputUISocket = draggingSocket.getUISocket();
                
                boolean containsKey = lineMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(lineMap.get(variableInputSocket).getOutputSocket() == outputUISocket);

                if (changed) {

                    lineMap.get(variableInputSocket).getStrokeDashArray().clear();

                }

                draggingLine.startXProperty().bind(mouseX);
                draggingLine.startYProperty().bind(mouseY);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                if (draggingSocket == null || !DynamicClassUtils.primitiveToWrapper(variableInputSocket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(draggingSocket.getVariableType()))) {
                    return;
                }

                UISocket inputUISocket = variableInputSocket.getUISocket();
                UISocket outputUISocket = draggingSocket.getUISocket();

                boolean containsKey = lineMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(lineMap.get(variableInputSocket).getOutputSocket() == outputUISocket);

                if (changed) {
                    variableInputSocket.getOutputSource().getUISocket().showIdle();
                    this.getChildren().remove(lineMap.get(variableInputSocket));
                }
                /* The line could be reused in a future version*/
                if (!containsKey || changed) {

                    UISocketIOLine line = new UISocketIOLine(sceneBoundsProperty, inputUISocket, outputUISocket);
                    line.setStroke(Color.LIGHTBLUE);
                    line.setStrokeWidth(2);
                    line.setStrokeLineCap(StrokeLineCap.ROUND);
                    line.setFill(new Color(0, 0, 0, 0));

                    lineMap.put(variableInputSocket, line);
                    this.getChildren().addAll(line);

                }

                variableInputSocket.setOutputSource(draggingSocket);

            });

        }

    }

    private void addOutputSocketListener(SetChangeListener.Change<? extends VariableOutputSocket> change) {

        if (change.wasAdded()) {

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                this.setCursor(Cursor.CLOSED_HAND);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> {

                draggingSocket = change.getElementAdded();
                draggingSocket.getUISocket().startFullDrag();
                this.setCursor(Cursor.CLOSED_HAND);

                HorizontalCurvedLine line = new HorizontalCurvedLine();
                line.bindLeftSocket(sceneBoundsProperty, draggingSocket.getUISocket());
                line.setStroke(Color.LIGHTGREEN);
                line.setStrokeWidth(1);
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                line.setFill(new Color(0, 0, 0, 0));
                line.setDisable(true);
                line.startXProperty().bind(mouseX);
                line.startYProperty().bind(mouseY);

                draggingLine = line;
                getChildren().add(line);

                socketSet.findAvailableInputSockets(change.getElementAdded().getVariableType());

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                this.setCursor(Cursor.OPEN_HAND);
                change.getElementAdded().getUISocket().showAvailable();
            });

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {

                if (draggingSocket == null) {
                    this.setCursor(Cursor.DEFAULT);
                    socketSet.makeOutputSocketUnavailable(change.getElementAdded());
                }

            });
            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                this.setCursor(Cursor.OPEN_HAND);
         
            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                socketSet.makeInputSocketUnavailable();

                if (draggingSocket != null) {
                    socketSet.makeOutputSocketUnavailable(draggingSocket);
                }

                draggingSocket = null;
                getChildren().remove(draggingLine);
                draggingLine = null;

            });

        }

    }

    private void childrenChangeListener(ListChangeListener.Change<? extends Node> change) {

        while (change.next()) {

            if (change.wasAdded() || change.wasReplaced()) {

                for (Node node : change.getAddedSubList()) {

                    if (node instanceof IOSocketPluggable) {

                        socketSet.getVariableInputSockets().addAll(((IOSocketPluggable) node).getVariableInputSockets());
                        socketSet.getVariableOutputSockets().addAll(((IOSocketPluggable) node).getVariableOutputSockets());

                    }

                }

            }

            if (change.wasRemoved() || change.wasReplaced()) {

                for (Node node : change.getRemoved()) {

                    if (node instanceof IOSocketPluggable) {

                        socketSet.getVariableInputSockets().removeAll(((IOSocketPluggable) node).getVariableInputSockets());
                        socketSet.getVariableOutputSockets().removeAll(((IOSocketPluggable) node).getVariableOutputSockets());

                    }

                }

            }
        }

    }

}
