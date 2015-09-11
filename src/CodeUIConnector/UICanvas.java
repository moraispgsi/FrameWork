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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;


public class UICanvas extends Region{
    
    private final UISocketCanvasSet socketSet = new UISocketCanvasSet();
    private String filePath;
    private Method method;
    private ObjectProperty<Point2D> sceneBoundsProperty = new SimpleObjectProperty<>();
    
    private VariableOutputSocket draggingSocket;
    
    public UICanvas(String filePath, Method method) {
        this.filePath = filePath;
        this.method = method;
        
        socketSet.getInputSockets()
                .addListener((SetChangeListener.Change<? extends VariableInputSocket> change) -> {
                    
                    addInputSocketListener(change);
                });
        
        socketSet.getOutputSockets()
                .addListener((SetChangeListener.Change<? extends VariableOutputSocket> change) -> {

                    addOutputSocketListener(change);
                });
        
        
        localToSceneTransformProperty().addListener((observable,  oldValue,  newValue)->{
            sceneBoundsProperty.setValue(this.localToScene(0,0));
        });
        
    
        getChildren().addListener((ListChangeListener.Change<? extends Node> change) ->{
            childrenChangeListener(change);
        }); 
        
        UIStartMethod startUI = new UIStartMethod(filePath,method);
        getChildren().add(startUI);
        
        /*
        File file = new File(filePath);
        
       
            
        String template;
        try (Scanner scanner = new Scanner(file)) {
            template = scanner.useDelimiter("\\Z").next();
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UICanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       */
        
        
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            UIEndMethod endUI = new UIEndMethod(filePath,method);
            getChildren().add(endUI);
        
        }
    }

    private void addInputSocketListener(SetChangeListener.Change<? extends VariableInputSocket> change) {

        if (change.wasAdded()) {
            
            change.getElementAdded().getUISocket().setOnDragOver(e->{
                
                if (draggingSocket == null || 
                        !change.getElementAdded().getVariableType().equals(draggingSocket.getVariableType()))
                    return;
                    
                e.acceptTransferModes(TransferMode.ANY);
                e.consume();
            });
            
            change.getElementAdded().getUISocket().setOnDragDropped(e -> {

                if (draggingSocket == null || 
                        !change.getElementAdded().getVariableType().equals(draggingSocket.getVariableType()))
                    return;
                    
                UISocket inputUISocket = change.getElementAdded().getUISocket();
                UISocket outputUISocket = draggingSocket.getUISocket();

                change.getElementAdded().setOutputSource(draggingSocket);

                DoubleBinding startX = new DoubleBinding(){

                    {
                        bind(sceneBoundsProperty,inputUISocket.getPlugBoundsProperty());
                    }

                    @Override
                    protected double computeValue() {
                        double socketMax = inputUISocket.getPlugBoundsProperty().getValue().getMaxX();
                        double socketMin = inputUISocket.getPlugBoundsProperty().getValue().getMinX();
                        double center = socketMin + (socketMax - socketMin)/2;


                        return center - sceneBoundsProperty.getValue().getX();

                    }

                };

                DoubleBinding startY = new DoubleBinding(){

                    {
                        bind(sceneBoundsProperty,inputUISocket.getPlugBoundsProperty());
                    }

                    @Override
                    protected double computeValue() {
                        double socketMax = inputUISocket.getPlugBoundsProperty().getValue().getMaxY();
                        double socketMin = inputUISocket.getPlugBoundsProperty().getValue().getMinY();
                        double center = socketMin + (socketMax - socketMin)/2;

                        return center - sceneBoundsProperty.getValue().getY();
                    }

                };

                DoubleBinding endX = new DoubleBinding(){

                    {
                        bind(sceneBoundsProperty,outputUISocket.getPlugBoundsProperty());
                    }

                    @Override
                    protected double computeValue() {

                        double socketMax = outputUISocket.getPlugBoundsProperty().getValue().getMaxX();
                        double socketMin = outputUISocket.getPlugBoundsProperty().getValue().getMinX();
                        double center = socketMin + (socketMax - socketMin)/2;

                        return center - sceneBoundsProperty.getValue().getX();
                    }

                };

                DoubleBinding endY = new DoubleBinding(){

                    {
                        bind(sceneBoundsProperty,outputUISocket.getPlugBoundsProperty());
                    }

                    @Override
                    protected double computeValue() {
                        double socketMax = outputUISocket.getPlugBoundsProperty().getValue().getMaxY();
                        double socketMin = outputUISocket.getPlugBoundsProperty().getValue().getMinY();
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


            });

            

        }

    }

    private void addOutputSocketListener(SetChangeListener.Change<? extends VariableOutputSocket> change) {

      
        if (change.wasAdded()) {

            change.getElementAdded().getUISocket().setOnDragDetected(e -> {

               
                draggingSocket = change.getElementAdded();

                Dragboard db = change.getElementAdded().getUISocket().startDragAndDrop(TransferMode.ANY);
                
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
                
                
                 socketSet
                         .getInputSockets()
                         .stream()
                         .filter(socket -> socket.getVariableType().equals(change.getElementAdded().getVariableType()))
                         .forEach(socket -> {socket.getUISocket().setAvailable();});
                
                        
                e.consume();

            });
            
            change.getElementAdded().getUISocket().setOnDragDone(e -> {
            
                    socketSet
                         .getInputSockets()
                         .stream()
                         .filter(socket -> socket.getVariableType().equals(change.getElementAdded().getVariableType()))
                         .forEach(socket -> {
                             if(socket.getOutputSource() == null)
                                socket.getUISocket().setIdle();
                             else
                                socket.getUISocket().setConnected();
                         });
                    
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
