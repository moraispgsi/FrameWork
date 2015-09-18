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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.event.EventHandler;
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

                    createParamInputListeners(change);
                });

        socketSet.getOutputParams()
                .addListener((SetChangeListener.Change<? extends ParamOutput> change) -> {

                    createParamOutputListeners(change);
                });

        socketSet.getCallInputs()
                .addListener((SetChangeListener.Change<? extends CallInput> change) -> {

                    createCallInputListeners(change);
                });

        socketSet.getCallOutputs()
                .addListener((SetChangeListener.Change<? extends CallOutput> change) -> {

                    createCallOutputListeners(change);
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

    private void createParamInputListeners(SetChangeListener.Change<? extends ParamInput> change) {

        if (change.wasAdded()) {

            ParamInput variableInputSocket = change.getElementAdded();
            
            
            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                
                onParamInputMouseDragEntered(e,variableInputSocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                onParamInputMouseDragExited(e,variableInputSocket);

            });

            variableInputSocket.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onParamInputMouseDragReleased(e,variableInputSocket);
                
            });

        }

    }

    private void createParamOutputListeners(SetChangeListener.Change<? extends ParamOutput> change) {

        if (change.wasAdded()) {
            
            ParamOutput paramOutput = change.getElementAdded();

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                
                onParamOutputMousePress(e,paramOutput);

            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.DRAG_DETECTED, e -> {

                onParamOutputDragDetected(e,paramOutput);

            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                onParamOutputMouseEntered(e,paramOutput);
            });

            paramOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {

                onParamOutputMouseExited(e,paramOutput);

            });
            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onParamOutputMouseDragReleased(e,paramOutput);

            });

            change.getElementAdded().getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                onParamOutputMouseReleased(e,paramOutput);

            });

        }

    }

    private void createCallInputListeners(SetChangeListener.Change<? extends CallInput> change) {

        if (change.wasAdded()) {

            CallInput callInput = change.getElementAdded();

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {

                onCallInputMouseDragEntered(e,callInput);

            });

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_EXITED, e -> {

                onCallInputMouseDragExited(e,callInput);

            });

            callInput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onCallInputMouseDragReleased(e,callInput);

            });

        }

    }

    private void createCallOutputListeners(SetChangeListener.Change<? extends CallOutput> change) {

        if (change.wasAdded()) {

            CallOutput callOutput = change.getElementAdded();
            
            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                
                onCallOutputMousePressed(e,callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> {

                onCallOutputDragDetected(e,callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {

                onCallOutputMouseEntered(e,callOutput);
            });

            callOutput.getUISocket().addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
           
                onCallOutputMouseExited(e,callOutput);

            });
            callOutput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {

                onCallOutputMouseDragReleased(e,callOutput);

            });

            callOutput.getUISocket().addEventHandler(MouseDragEvent.MOUSE_RELEASED, e -> {

                onCallOutputMouseReleased(e,callOutput);

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
    
    private void onParamInputMouseDragEntered(MouseDragEvent e,ParamInput variableInputSocket){
        
        
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
        
    }
    
    private void onParamInputMouseDragExited(MouseDragEvent e,ParamInput paramInput){
        
        if (draggingSocket == null || !paramInput.isCastCompatible(draggingSocket)) {
            return;
        }


        boolean containsKey = lineVariableMap.containsKey(paramInput);
        boolean changed = containsKey && !(paramInput.getOutputSource() == draggingSocket);

        if (changed) {

            lineVariableMap.get(paramInput).getStrokeDashArray().clear();

        }

        draggingLine.startXProperty().bind(mouseX);
        draggingLine.startYProperty().bind(mouseY);
        
    }
    
    private void onParamInputMouseDragReleased(MouseDragEvent e,ParamInput paramInput){
        
        if (draggingSocket == null || !paramInput.isCastCompatible(draggingSocket)) {
            return;
        }

        UISocket inputUISocket = paramInput.getUISocket();
        UISocket outputUISocket = draggingSocket.getUISocket();

        boolean containsKey = lineVariableMap.containsKey(paramInput);
        boolean changed = containsKey && !(paramInput.getOutputSource() == draggingSocket);

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

        paramInput.setOutputSource(draggingSocket);

   
    }
    
    private void onParamOutputDragDetected(MouseEvent e,ParamOutput paramOutput){
        
        draggingSocket = paramOutput;
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

        socketSet.findAvailableInputSockets(paramOutput.getVariableType());
        
    }
    
    private void onParamOutputMousePress(MouseEvent e,ParamOutput paramOutput){
        this.setCursor(Cursor.CLOSED_HAND);
    }
    
    private void onParamOutputMouseEntered(MouseEvent e,ParamOutput paramOutput){
        
        this.setCursor(Cursor.OPEN_HAND);
        paramOutput.getUISocket().showAvailable();
        
    }
    
    private void onParamOutputMouseExited(MouseEvent e,ParamOutput paramOutput){
        
        if (draggingSocket == null) {
            this.setCursor(Cursor.DEFAULT);
            socketSet.makeOutputSocketUnavailable(paramOutput);
        }
        
    }
    
    private void onParamOutputMouseDragReleased(MouseEvent e,ParamOutput paramOutput){
        
        this.setCursor(Cursor.OPEN_HAND);

    }
    
    private void onParamOutputMouseReleased(MouseEvent e,ParamOutput paramOutput){
    
        socketSet.makeInputSocketUnavailable();

        if (draggingSocket != null) {
            socketSet.makeOutputSocketUnavailable(draggingSocket);
        }

        draggingSocket = null;
        getChildren().remove(draggingLine);
        draggingLine = null;
        
    }
    
    private void onCallInputMouseDragEntered(MouseEvent e,CallInput callInput){
        
        UISocket inputUISocket = callInput.getUISocket();

        if (draggingCallSocket == null) {
            return;
        }

        if (draggingCallSocket.getConnectSocket() != null) {

            if (lineCallMap.containsKey(draggingCallSocket)
                    && draggingCallSocket.getConnectSocket() != callInput) {

                lineCallMap.get(draggingCallSocket).getStrokeDashArray().addAll(2d, 10d);
            }

        }

        draggingLine.bindRightSocket(inputUISocket);
        
    }
    
    private void onCallInputMouseDragExited(MouseEvent e,CallInput callInput){
        
         if (draggingCallSocket == null) {
            return;
        }

        if (draggingCallSocket.getConnectSocket() != null) {

            if (lineCallMap.containsKey(draggingCallSocket)
                    && draggingCallSocket.getConnectSocket() != callInput) {

                lineCallMap.get(draggingCallSocket).getStrokeDashArray().clear();
            }

        }

        draggingLine.startXProperty().bind(mouseX);
        draggingLine.startYProperty().bind(mouseY);
        
    }
    
    private void onCallInputMouseDragReleased(MouseEvent e,CallInput callInput){
        
        if (draggingCallSocket == null) {
            return;
        }

        if (draggingCallSocket.getConnectSocket() != null) {

            if (lineCallMap.containsKey(draggingCallSocket)) {

                getChildren().remove(lineCallMap.get(draggingCallSocket));
                lineCallMap.remove(draggingCallSocket);

            }

        }

        UISocket inputUISocket = callInput.getUISocket();
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

        draggingCallSocket.setConnect(callInput);
        
    }
    
    private void onCallOutputMousePressed(MouseEvent e,CallOutput callOutput){
        
        setCursor(Cursor.CLOSED_HAND);
        
    }
    
    private void onCallOutputDragDetected(MouseEvent e,CallOutput callOutput){
        
        draggingCallSocket = callOutput;
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
    }
    
    private void onCallOutputMouseEntered(MouseEvent e,CallOutput callOutput){
        
        this.setCursor(Cursor.OPEN_HAND);
        callOutput.getUISocket().showAvailable();
        
    }
    
    private void onCallOutputMouseExited(MouseEvent e,CallOutput callOutput){
        
        if (draggingCallSocket == null) {
                    
            if(callOutput.getConnectSocket() != null)
                callOutput.getUISocket().showConnected();
            else
                callOutput.getUISocket().showIdle();


            this.setCursor(Cursor.DEFAULT);
            socketSet.getCallInputs()
                    .stream()
                    .forEach(socket -> socket.getUISocket().showIdle());
        }
        
    }
    
    private void onCallOutputMouseDragReleased(MouseEvent e,CallOutput callOutput){
        
        this.setCursor(Cursor.OPEN_HAND);
    }
    
    private void onCallOutputMouseReleased(MouseEvent e,CallOutput callOutput){
        
        if (draggingCallSocket == null) {

            this.setCursor(Cursor.DEFAULT);
            socketSet.getCallInputs()
                    .stream()
                    .forEach(socket -> socket.getUISocket().showIdle());
        }
        else{

            if(draggingCallSocket.getConnectSocket() != null)
                draggingCallSocket.getUISocket().showConnected();
            else
                draggingCallSocket.getUISocket().showIdle();

        }

        if(callOutput.getConnectSocket() != null)
              callOutput.getUISocket().showConnected();
        else
            callOutput.getUISocket().showIdle();


        draggingCallSocket = null;
        getChildren().remove(draggingLine);
        draggingLine = null;
        
    }
    
}
