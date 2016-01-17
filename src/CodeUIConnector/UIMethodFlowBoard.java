/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import CodeUIConnector.ParamSockets.Controller.ParamInput;
import CodeUIConnector.SocketSets.Pluggable;
import CodeUIConnector.InvokeSockets.Controller.InvokeInput;
import CodeUIConnector.SocketSets.UISocketFlowBoardSet;
import CodeUIConnector.InvokeSockets.Controller.InvokeOutput;
import CodeUIConnector.SocketPane.UISocket;
import CodeUIConnector.SocketPane.UIStatement;
import DynamicClassUtils.DynamicClassUtils;
import UIStatements.UIBranchStatement;
import UIStatements.UIEndMethodStatement;
import UIStatements.UIStartMethodStatement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    private final List<Class> loadedClasses;

    private final UISocketFlowBoardSet socketSet = new UISocketFlowBoardSet();

    private final Map<ParamInput, HorizontalCurvedLine> lineVariableMap = new HashMap<>();
    private final Map<InvokeOutput, HorizontalCurvedLine> lineCallMap = new HashMap<>();

    private final String filePath;
    private final Method method;

    private InvokeOutput draggingCallOutput;
    private ParamOutput draggingParamOutput;
    private HorizontalCurvedLine draggingLine;

    private final Double mouseHOffset = 10d;
    private final Double mouseVOffset = 10d;
    private final DoubleProperty mouseX = new SimpleDoubleProperty();
    private final DoubleProperty mouseY = new SimpleDoubleProperty();

    public UIMethodFlowBoard(String filePath, Method method) {

        try {
            loadedClasses = DynamicClassUtils.getLoadedClasses(this.getClass().getClassLoader());
            Collections.sort(loadedClasses, (Class class1, Class class2) -> class1.getSimpleName().compareTo(class2.getSimpleName()));

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException("Unable to get loaded classes.");
        }

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

                    createParamInputListeners(change);
                });

        socketSet.getOutputParams()
                .addListener((SetChangeListener.Change<? extends ParamOutput> change) -> {

                    createParamOutputListeners(change);
                });

        socketSet.getCallInputs()
                .addListener((SetChangeListener.Change<? extends InvokeInput> change) -> {

                    createCallInputListeners(change);
                });

        socketSet.getCallOutputs()
                .addListener((SetChangeListener.Change<? extends InvokeOutput> change) -> {

                    createCallOutputListeners(change);
                });

        getChildren().addListener((ListChangeListener.Change<? extends Node> change) -> {
            childrenChangeListener(change);
        });

        getChildren().add(new UIStartMethodStatement(method));

        getChildren().add(new UIBranchStatement());

        getChildren().add(new UIEndMethodStatement(method));

    }

    public void addUIStatement(UIStatement uiStatement) {

        getChildren().add(uiStatement);

    }

    private void createParamInputListeners(SetChangeListener.Change<? extends ParamInput> change) {

        if (change.wasAdded()) {

            ParamInput variableInputSocket = change.getElementAdded();

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                onParamInputMouseDragEntered(e, variableInputSocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                onParamInputMouseDragExited(e, variableInputSocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onParamInputMouseDragReleased(e, variableInputSocket);

            });

        }

    }

    private void createParamOutputListeners(SetChangeListener.Change<? extends ParamOutput> change) {

        if (change.wasAdded()) {

            ParamOutput paramOutput = change.getElementAdded();

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

                onParamOutputMousePress(e, paramOutput);

            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.DRAG_DETECTED, e -> {

                onParamOutputDragDetected(e, paramOutput);

            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                onParamOutputMouseEntered(e, paramOutput);
            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {

                onParamOutputMouseExited(e, paramOutput);

            });
            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onParamOutputMouseDragReleased(e, paramOutput);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                onParamOutputMouseReleased(e, paramOutput);

            });

        }

    }

    private void createCallInputListeners(SetChangeListener.Change<? extends InvokeInput> change) {

        if (change.wasAdded()) {

            InvokeInput callInput = change.getElementAdded();

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                onCallInputMouseDragEntered(e, callInput);

            });

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                onCallInputMouseDragExited(e, callInput);

            });

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onCallInputMouseDragReleased(e, callInput);

            });

        }

    }

    private void createCallOutputListeners(SetChangeListener.Change<? extends InvokeOutput> change) {

        if (change.wasAdded()) {

            InvokeOutput callOutput = change.getElementAdded();

            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

                onCallOutputMousePressed(e, callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> {

                onCallOutputDragDetected(e, callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                onCallOutputMouseEntered(e, callOutput);
            });

            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {

                onCallOutputMouseExited(e, callOutput);

            });
            callOutput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onCallOutputMouseDragReleased(e, callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                onCallOutputMouseReleased(e, callOutput);

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

    /*
     ParamInput Listeners
     */
    private void onParamInputMouseDragEntered(MouseDragEvent e, ParamInput variableInputSocket) {

        if (draggingParamOutput == null || !variableInputSocket.isCastCompatible(draggingParamOutput)) {
            return;
        }

        UISocket inputUISocket = variableInputSocket.getUISocket();
        boolean containsKey = lineVariableMap.containsKey(variableInputSocket);
        boolean changed = containsKey && !(variableInputSocket.getOutputSource() == draggingParamOutput);

        if (changed) {

            lineVariableMap.get(variableInputSocket).getStrokeDashArray().addAll(2d, 10d);

        }

        draggingLine.bindRightSocket(inputUISocket);

    }

    private void onParamInputMouseDragExited(MouseDragEvent e, ParamInput paramInput) {

        if (draggingParamOutput == null || !paramInput.isCastCompatible(draggingParamOutput)) {
            return;
        }

        boolean containsKey = lineVariableMap.containsKey(paramInput);
        boolean changed = containsKey && !(paramInput.getOutputSource() == draggingParamOutput);

        if (changed) {

            lineVariableMap.get(paramInput).getStrokeDashArray().clear();

        }

        draggingLine.startXProperty().bind(mouseX);
        draggingLine.startYProperty().bind(mouseY);

    }

    private void onParamInputMouseDragReleased(MouseDragEvent e, ParamInput paramInput) {

        if (draggingParamOutput == null || !paramInput.isCastCompatible(draggingParamOutput)) {
            return;
        }

        UISocket inputUISocket = paramInput.getUISocket();
        UISocket outputUISocket = draggingParamOutput.getUISocket();

        boolean containsKey = lineVariableMap.containsKey(paramInput);
        boolean changed = containsKey && !(paramInput.getOutputSource() == draggingParamOutput);

        if (changed) {
            paramInput.getOutputSource().getUISocket().showIdle();
            getChildren().remove(lineVariableMap.get(paramInput));
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

            lineVariableMap.put(paramInput, line);
            getChildren().addAll(line);

        }

        paramInput.connect(draggingParamOutput);

    }

    /*
     ParamOutput Listeners
     */
    private void onParamOutputDragDetected(MouseEvent e, ParamOutput paramOutput) {

        draggingParamOutput = paramOutput;
        draggingParamOutput.getUISocket().startFullDrag();
        this.setCursor(Cursor.CLOSED_HAND);

        HorizontalCurvedLine line = new HorizontalCurvedLine();
        line.bindLeftSocket(draggingParamOutput.getUISocket());
        line.setStroke(Color.LIGHTGREEN);
        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setFill(new Color(0, 0, 0, 0));
        line.setDisable(true);
        line.startXProperty().bind(mouseX);
        line.startYProperty().bind(mouseY);

        draggingLine = line;
        getChildren().add(line);

        socketSet.findAvailableInputSockets(paramOutput.getVariableType());

    }

    private void onParamOutputMousePress(MouseEvent e, ParamOutput paramOutput) {
        this.setCursor(Cursor.CLOSED_HAND);
    }

    private void onParamOutputMouseEntered(MouseEvent e, ParamOutput paramOutput) {

        this.setCursor(Cursor.OPEN_HAND);
        paramOutput.getUISocket().showAvailable();

    }

    private void onParamOutputMouseExited(MouseEvent e, ParamOutput paramOutput) {

        if (draggingParamOutput == null) {
            this.setCursor(Cursor.DEFAULT);
            socketSet.makeOutputSocketUnavailable(paramOutput);
        }

    }

    private void onParamOutputMouseDragReleased(MouseEvent e, ParamOutput paramOutput) {

        this.setCursor(Cursor.OPEN_HAND);

    }

    private void onParamOutputMouseReleased(MouseEvent e, ParamOutput paramOutput) {

        socketSet.makeInputSocketUnavailable();

        if (draggingParamOutput != null) {
            socketSet.makeOutputSocketUnavailable(draggingParamOutput);
        }

        draggingParamOutput = null;
        getChildren().remove(draggingLine);
        draggingLine = null;

    }

    /*
     CallInput Listeners
     */
    private void onCallInputMouseDragEntered(MouseEvent e, InvokeInput callInput) {

        UISocket inputUISocket = callInput.getUISocket();

        if (draggingCallOutput == null) {
            return;
        }

        if (draggingCallOutput.getConnectedCallInput() != null) {

            if (lineCallMap.containsKey(draggingCallOutput)
                    && draggingCallOutput.getConnectedCallInput() != callInput) {

                lineCallMap.get(draggingCallOutput).getStrokeDashArray().addAll(2d, 10d);
            }

        }

        draggingLine.bindRightSocket(inputUISocket);

    }

    private void onCallInputMouseDragExited(MouseEvent e, InvokeInput callInput) {

        if (draggingCallOutput == null) {
            return;
        }

        if (draggingCallOutput.getConnectedCallInput() != null) {

            if (lineCallMap.containsKey(draggingCallOutput)
                    && draggingCallOutput.getConnectedCallInput() != callInput) {

                lineCallMap.get(draggingCallOutput).getStrokeDashArray().clear();
            }

        }

        draggingLine.startXProperty().bind(mouseX);
        draggingLine.startYProperty().bind(mouseY);

    }

    private void onCallInputMouseDragReleased(MouseEvent e, InvokeInput callInput) {

        if (draggingCallOutput == null) {
            return;
        }

        if (draggingCallOutput.getConnectedCallInput() != null) {

            if (lineCallMap.containsKey(draggingCallOutput)) {

                getChildren().remove(lineCallMap.get(draggingCallOutput));
                lineCallMap.remove(draggingCallOutput);

            }

        }

        UISocket inputUISocket = callInput.getUISocket();
        UISocket outputUISocket = draggingCallOutput.getUISocket();

        HorizontalCurvedLine line = new HorizontalCurvedLine();
        line.bindLeftSocket(outputUISocket);
        line.bindRightSocket(inputUISocket);
        line.setStroke(Color.LIGHTBLUE);
        line.setStrokeWidth(2);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setFill(new Color(0, 0, 0, 0));
        lineCallMap.put(draggingCallOutput, line);

        this.getChildren().addAll(line);

        draggingCallOutput.connect(callInput);

    }

    /*
     CallOutput Listeners
     */
    private void onCallOutputMousePressed(MouseEvent e, InvokeOutput callOutput) {

        setCursor(Cursor.CLOSED_HAND);

    }

    private void onCallOutputDragDetected(MouseEvent e, InvokeOutput callOutput) {

        draggingCallOutput = callOutput;
        draggingCallOutput.getUISocket().startFullDrag();
        this.setCursor(Cursor.CLOSED_HAND);

        HorizontalCurvedLine line = new HorizontalCurvedLine();
        line.bindLeftSocket(draggingCallOutput.getUISocket());
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
    }

    private void onCallOutputMouseEntered(MouseEvent e, InvokeOutput callOutput) {

        this.setCursor(Cursor.OPEN_HAND);
        callOutput.getUISocket().showAvailable();

    }

    private void onCallOutputMouseExited(MouseEvent e, InvokeOutput callOutput) {

        if (draggingCallOutput == null) {

            if (callOutput.getConnectedCallInput() != null) {
                callOutput.getUISocket().showConnected();
            } else {
                callOutput.getUISocket().showIdle();
            }

            this.setCursor(Cursor.DEFAULT);
            socketSet.getCallInputs()
                    .stream()
                    .forEach(socket -> socket.getUISocket().showIdle());
        }

    }

    private void onCallOutputMouseDragReleased(MouseEvent e, InvokeOutput callOutput) {

        this.setCursor(Cursor.OPEN_HAND);
    }

    private void onCallOutputMouseReleased(MouseEvent e, InvokeOutput callOutput) {

        if (draggingCallOutput == null) {

            this.setCursor(Cursor.DEFAULT);
            socketSet.getCallInputs()
                    .stream()
                    .forEach(socket -> socket.getUISocket().showIdle());
        } else {

            if (draggingCallOutput.getConnectedCallInput() != null) {
                draggingCallOutput.getUISocket().showConnected();
            } else {
                draggingCallOutput.getUISocket().showIdle();
            }

        }

        if (callOutput.getConnectedCallInput() != null) {
            callOutput.getUISocket().showConnected();
        } else {
            callOutput.getUISocket().showIdle();
        }

        draggingCallOutput = null;
        getChildren().remove(draggingLine);
        draggingLine = null;

    }

    public Method getMethod() {
        return method;
    }

    public List<Class> getLoadedClasses() {
        return new ArrayList<>(loadedClasses);
    }

}
