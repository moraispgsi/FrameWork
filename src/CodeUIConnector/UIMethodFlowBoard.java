/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import CodeUIConnector.Connectors.ParamOutput;
import CodeUIConnector.Connectors.ParamInput;
import CodeUIConnector.SocketSets.Pluggable;
import CodeUIConnector.Connectors.CallInput;
import CodeUIConnector.SocketSets.UISocketFlowBoardSet;
import CodeUIConnector.Connectors.CallOutput;
import CodeUIConnector.SocketPane.UISocket;
import CodeUIConnector.SocketPane.UIElementFactory;
import DynamicClassUtils.DynamicClassUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class UIMethodFlowBoard extends Region {

    private final UISocketFlowBoardSet socketSet = new UISocketFlowBoardSet();
    
    private final Map<ParamInput, HorizontalCurvedLine> lineVariableMap = new HashMap<>();
    private final Map<CallOutput, HorizontalCurvedLine> lineCallMap = new HashMap<>();

    private final String filePath;
    private final Method method;

    private CallOutput draggingCallSocket;
    private ParamOutput draggingSocket;
    private HorizontalCurvedLine draggingLine;

    private final Double mouseHOffset = 10d;
    private final Double mouseVOffset = 10d;
    private final DoubleProperty mouseX = new SimpleDoubleProperty();
    private final DoubleProperty mouseY = new SimpleDoubleProperty();

    public UIMethodFlowBoard(String filePath, Method method) {
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

        socketSet.getInputParams()
                .addListener((SetChangeListener.Change<? extends ParamInput> change) -> {

                    buildVInputSocketListeners(change);
                });

        socketSet.getOutputParams()
                .addListener((SetChangeListener.Change<? extends ParamOutput> change) -> {

                    buildVOutputSocketListeners(change);
                });

        socketSet.getCallInputs()
                .addListener((SetChangeListener.Change<? extends CallInput> change) -> {

                    buildInputCallSocketListeners(change);
                });

        socketSet.getCallOutputs()
                .addListener((SetChangeListener.Change<? extends CallOutput> change) -> {

                    buildOutputCallSocketListeners(change);
                });

        getChildren().addListener((ListChangeListener.Change<? extends Node> change) -> {
            childrenChangeListener(change);
        });

        getChildren().add(UIElementFactory.createStartMethod(method));

        getChildren().add(UIElementFactory.createCondition());

        if (!method.getReturnType().equals(Void.TYPE)) {

            getChildren().add(UIElementFactory.createEndMethod(method));

        }
    }

    private void buildVInputSocketListeners(SetChangeListener.Change<? extends ParamInput> change) {

        if (change.wasAdded()) {

            ParamInput variableInputSocket = change.getElementAdded();

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                if (draggingSocket == null || !variableInputSocket.isCastCompatible(draggingSocket)) {
                    return;
                }

                UISocket inputUISocket = variableInputSocket.getUISocket();
                boolean containsKey = lineVariableMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(variableInputSocket.getOutputSource() == draggingSocket);

                if (changed) {

                    lineVariableMap.get(variableInputSocket).getStrokeDashArray().addAll(2d, 10d);

                }

                draggingLine.bindRightSocket(inputUISocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                if (draggingSocket == null || !variableInputSocket.isCastCompatible(draggingSocket)) {
                    return;
                }


                boolean containsKey = lineVariableMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(variableInputSocket.getOutputSource() == draggingSocket);

                if (changed) {

                    lineVariableMap.get(variableInputSocket).getStrokeDashArray().clear();

                }

                draggingLine.startXProperty().bind(mouseX);
                draggingLine.startYProperty().bind(mouseY);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                if (draggingSocket == null || !variableInputSocket.isCastCompatible(draggingSocket)) {
                    return;
                }

                UISocket inputUISocket = variableInputSocket.getUISocket();
                UISocket outputUISocket = draggingSocket.getUISocket();

                boolean containsKey = lineVariableMap.containsKey(variableInputSocket);
                boolean changed = containsKey && !(variableInputSocket.getOutputSource() == draggingSocket);

                if (changed) {
                    variableInputSocket.getOutputSource().getUISocket().showIdle();
                    this.getChildren().remove(lineVariableMap.get(variableInputSocket));
                }
                /* The line could be reused in a future version*/
                if (!containsKey || changed) {

                    HorizontalCurvedLine line = new HorizontalCurvedLine();
                    line.bindLeftSocket(outputUISocket);
                    line.bindRightSocket(inputUISocket);
                    line.setStroke(Color.LIGHTBLUE);
                    line.setStrokeWidth(2);
                    line.setStrokeLineCap(StrokeLineCap.ROUND);
                    line.setFill(new Color(0, 0, 0, 0));

                    lineVariableMap.put(variableInputSocket, line);
                    this.getChildren().addAll(line);

                }

                variableInputSocket.setOutputSource(draggingSocket);

            });

        }

    }

    private void buildVOutputSocketListeners(SetChangeListener.Change<? extends ParamOutput> change) {

        if (change.wasAdded()) {

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                this.setCursor(Cursor.CLOSED_HAND);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> {

                draggingSocket = change.getElementAdded();
                draggingSocket.getUISocket().startFullDrag();
                this.setCursor(Cursor.CLOSED_HAND);

                HorizontalCurvedLine line = new HorizontalCurvedLine();
                line.bindLeftSocket(draggingSocket.getUISocket());
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

    private void buildInputCallSocketListeners(SetChangeListener.Change<? extends CallInput> change) {

        if (change.wasAdded()) {

            CallInput inputCallSocket = change.getElementAdded();

            inputCallSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                UISocket inputUISocket = inputCallSocket.getUISocket();

                if (draggingCallSocket == null) {
                    return;
                }

                if (draggingCallSocket.getConnectSocket() != null) {

                    if (lineCallMap.containsKey(draggingCallSocket)
                            && draggingCallSocket.getConnectSocket() != inputCallSocket) {

                        lineCallMap.get(draggingCallSocket).getStrokeDashArray().addAll(2d, 10d);
                    }

                }

                draggingLine.bindRightSocket(inputUISocket);

            });

            inputCallSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                if (draggingCallSocket == null) {
                    return;
                }

                if (draggingCallSocket.getConnectSocket() != null) {

                    if (lineCallMap.containsKey(draggingCallSocket)
                            && draggingCallSocket.getConnectSocket() != inputCallSocket) {

                        lineCallMap.get(draggingCallSocket).getStrokeDashArray().clear();
                    }

                }

                draggingLine.startXProperty().bind(mouseX);
                draggingLine.startYProperty().bind(mouseY);

            });

            inputCallSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                if (draggingCallSocket == null) {
                    return;
                }

                if (draggingCallSocket.getConnectSocket() != null) {

                    if (lineCallMap.containsKey(draggingCallSocket)
                            && draggingCallSocket.getConnectSocket() != inputCallSocket) {
                        
                        getChildren().remove(lineCallMap.get(draggingCallSocket));
                        lineCallMap.remove(draggingCallSocket);
                        
                    }

                }

                UISocket inputUISocket = inputCallSocket.getUISocket();
                UISocket outputUISocket = draggingCallSocket.getUISocket();

                HorizontalCurvedLine line = new HorizontalCurvedLine();
                line.bindLeftSocket(outputUISocket);
                line.bindRightSocket(inputUISocket);
                line.setStroke(Color.LIGHTBLUE);
                line.setStrokeWidth(2);
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                line.setFill(new Color(0, 0, 0, 0));
                lineCallMap.put(draggingCallSocket, line);

                this.getChildren().addAll(line);

                draggingCallSocket.setConnect(inputCallSocket);

            });

        }

    }

    private void buildOutputCallSocketListeners(SetChangeListener.Change<? extends CallOutput> change) {

        if (change.wasAdded()) {

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                this.setCursor(Cursor.CLOSED_HAND);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> {

                draggingCallSocket = change.getElementAdded();
                draggingCallSocket.getUISocket().startFullDrag();
                this.setCursor(Cursor.CLOSED_HAND);

                HorizontalCurvedLine line = new HorizontalCurvedLine();
                line.bindLeftSocket(draggingCallSocket.getUISocket());
                line.setStroke(Color.LIGHTGREEN);
                line.setStrokeWidth(1);
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                line.setFill(new Color(0, 0, 0, 0));
                line.setDisable(true);
                line.startXProperty().bind(mouseX);
                line.startYProperty().bind(mouseY);

                draggingLine = line;
                getChildren().add(line);

                socketSet.getCallInputs()
                        .stream()
                        .forEach(socket -> socket.getUISocket().showAvailable());

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                this.setCursor(Cursor.OPEN_HAND);
                change.getElementAdded().getUISocket().showAvailable();
            });

            change.getElementAdded().getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {

                if (draggingCallSocket == null) {
                    this.setCursor(Cursor.DEFAULT);
                    socketSet.getCallInputs()
                            .stream()
                            .forEach(socket -> socket.getUISocket().showIdle());
                }

            });
            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                this.setCursor(Cursor.OPEN_HAND);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                socketSet.makeInputSocketUnavailable();

                if (draggingCallSocket != null) {
                    //socketSet.makeOutputSocketUnavailable(draggingCallSocket);
                }

                draggingCallSocket = null;
                getChildren().remove(draggingLine);
                draggingLine = null;

            });

        }
    }

    private void childrenChangeListener(ListChangeListener.Change<? extends Node> change) {

        while (change.next()) {

            if (change.wasAdded() || change.wasReplaced()) {

                for (Node node : change.getAddedSubList()) {

                    if (node instanceof Pluggable) {

                        socketSet.getInputParams().addAll(((Pluggable) node).getInputParams());
                        socketSet.getOutputParams().addAll(((Pluggable) node).getOutputParams());
                        socketSet.getCallInputs().addAll(((Pluggable) node).getCallInputs());
                        socketSet.getCallOutputs().addAll(((Pluggable) node).getCallOutputs());
                    }

                }

            }

            if (change.wasRemoved() || change.wasReplaced()) {

                for (Node node : change.getRemoved()) {

                    if (node instanceof Pluggable) {

                        socketSet.getInputParams().removeAll(((Pluggable) node).getInputParams());
                        socketSet.getOutputParams().removeAll(((Pluggable) node).getOutputParams());
                        socketSet.getCallInputs().removeAll(((Pluggable) node).getCallInputs());
                        socketSet.getCallOutputs().removeAll(((Pluggable) node).getCallOutputs());

                    }

                }

            }
        }

    }

}
