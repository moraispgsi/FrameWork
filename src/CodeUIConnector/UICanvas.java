/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;



import java.lang.reflect.Method;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;


public class UICanvas extends Pane {
    
    private final UISocketCanvasSet socketSet = new UISocketCanvasSet();
    private String file;
    private Method method;
    private ObjectProperty<Point2D> sceneBoundsProperty = new SimpleObjectProperty<>();
    
   
    
    private UIVariableOutputSocket draggingSocket;
    
    public UICanvas(String file, Method method) {
        this.file = file;
        this.method = method;
        
        socketSet.getInputSockets()
                .addListener((SetChangeListener.Change<? extends UIVariableInputSocket> change) -> {
                    
                    addInputSocketListener(change);
                });
        
        socketSet.getOutputSockets()
                .addListener((SetChangeListener.Change<? extends UIVariableOutputSocket> change) -> {

                    addOutputSocketListener(change);
                });
        
        
        localToSceneTransformProperty().addListener((observable,  oldValue,  newValue)->{
            sceneBoundsProperty.setValue(this.localToScene(0,0));
        });
        
    
        getChildren().addListener((ListChangeListener.Change<? extends Node> change) ->{
            childrenChangeListener(change);
        }); 
        
        UIStartMethod startUI = new UIStartMethod(file,method);
        getChildren().add(startUI);

        if(!method.getReturnType().equals(Void.TYPE)){
            
            UIEndMethod endUI = new UIEndMethod(file,method);
            getChildren().add(endUI);
        
        }
        
    }
    


    private void addInputSocketListener(SetChangeListener.Change<? extends UIVariableInputSocket> change) {

        if (change.wasAdded()) {
            
            change.getElementAdded().getUISocket().setOnDragOver(e->{
                    
                e.acceptTransferModes(TransferMode.ANY);
                e.consume();
            });
            
            change.getElementAdded().getUISocket().setOnDragDropped(e -> {

                if (draggingSocket != null) {

                    UISocket inputUISocket = change.getElementAdded().getUISocket();
                    UISocket outputUISocket = draggingSocket.getUISocket();
                   
                    
                    DoubleBinding startX = new DoubleBinding(){
                        
                        {
                            bind(sceneBoundsProperty,inputUISocket.getBoundsProperty());
                        }
                        
                        @Override
                        protected double computeValue() {
                            double socketMax = inputUISocket.getBoundsProperty().getValue().getMaxX();
                            double socketMin = inputUISocket.getBoundsProperty().getValue().getMinX();
                            double center = socketMin + (socketMax - socketMin)/2;
                 
                            
                            return center - sceneBoundsProperty.getValue().getX();
                            
                        }

                    };
                    
                    DoubleBinding startY = new DoubleBinding(){
                        
                        {
                            bind(sceneBoundsProperty,inputUISocket.getBoundsProperty());
                        }
                        
                        @Override
                        protected double computeValue() {
                            double socketMax = inputUISocket.getBoundsProperty().getValue().getMaxY();
                            double socketMin = inputUISocket.getBoundsProperty().getValue().getMinY();
                            double center = socketMin + (socketMax - socketMin)/2;
          
                            return center - sceneBoundsProperty.getValue().getY();
                        }

                    };
                    
                    DoubleBinding endX = new DoubleBinding(){
                        
                        {
                            bind(sceneBoundsProperty,outputUISocket.getBoundsProperty());
                        }
                        
                        @Override
                        protected double computeValue() {
                            
                            double socketMax = outputUISocket.getBoundsProperty().getValue().getMaxX();
                            double socketMin = outputUISocket.getBoundsProperty().getValue().getMinX();
                            double center = socketMin + (socketMax - socketMin)/2;
                            
                            return center - sceneBoundsProperty.getValue().getX();
                        }

                    };
                    
                    DoubleBinding endY = new DoubleBinding(){
                        
                        {
                            bind(sceneBoundsProperty,outputUISocket.getBoundsProperty());
                        }
                        
                        @Override
                        protected double computeValue() {
                            double socketMax = outputUISocket.getBoundsProperty().getValue().getMaxY();
                            double socketMin = outputUISocket.getBoundsProperty().getValue().getMinY();
                            double center = socketMin + (socketMax - socketMin)/2;
            
                            return center - sceneBoundsProperty.getValue().getY();
                        }

                    };
                    
                    
                    HorizontalCurvedLine line = new HorizontalCurvedLine();
                    line.startXProperty().bind(startX);
                    line.startYProperty().bind(startY);
                    line.endXProperty().bind(endX);
                    line.endYProperty().bind(endY);
                    line.setStroke(Color.LIGHTBLUE);
                    line.setStrokeWidth(2);
                    line.setStrokeLineCap(StrokeLineCap.ROUND);
                    line.setFill(new Color(0, 0,0, 0));
                    
                    this.getChildren().addAll(line);
                    
                    e.consume();
                    
                    
                }

            });

            

        }

    }

    private void addOutputSocketListener(SetChangeListener.Change<? extends UIVariableOutputSocket> change) {

      
        if (change.wasAdded()) {

            change.getElementAdded().getUISocket().setOnDragDetected(e -> {

               
                draggingSocket = change.getElementAdded();

                Dragboard db = change.getElementAdded().getUISocket().startDragAndDrop(TransferMode.ANY);
                
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
                        
                e.consume();

            });

        }
    }
    
    private void childrenChangeListener(ListChangeListener.Change<? extends Node> change){
        
        while(change.next()){
            
                if(change.wasAdded() || change.wasReplaced()){

                    for(Node node : change.getAddedSubList()){

                        if(node instanceof IOSocketPluggable){
                            
                            socketSet.getInputSockets().addAll(((IOSocketPluggable)node).getInputSockets());
                            socketSet.getOutputSockets().addAll(((IOSocketPluggable)node).getOutputSockets());

                        }

                    }

                }

                if(change.wasRemoved() || change.wasReplaced()){

                    for(Node node : change.getRemoved()){

                        if(node instanceof IOSocketPluggable){

                            socketSet.getInputSockets().removeAll(((IOSocketPluggable)node).getInputSockets());
                            socketSet.getOutputSockets().removeAll(((IOSocketPluggable)node).getOutputSockets());

                        }

                    }

                }
            }
        
    }
    
    

    
}
