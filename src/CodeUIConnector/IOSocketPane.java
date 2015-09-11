/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class IOSocketPane extends Region implements IOSocketPluggable {
    
    private final IOSocketSet socketSet = new IOSocketSet();
    
    private final BorderPane rootBorderPane = new BorderPane();
    private final DraggableTitle titleBar;
    private final double IO_VERTICAL_SPACING = 5;
    
    private final VBox centerVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox inputSocketCallVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox outputSocketCallVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox inputSocketVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox outputSocketVBox = new VBox(IO_VERTICAL_SPACING);
    
     

    public IOSocketPane(String title) {
  

        titleBar = new DraggableTitle(title,layoutXProperty(),layoutYProperty());

        //setStyle("-fx-border-width: 1; -fx-border-color: grey;-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 0 0;");
        
        titleBar.setStyle("-fx-padding: 5;-fx-background-color: rgba(144, 195, 212, .7);");
        centerVBox.setStyle(" -fx-background-color: rgba(255, 255, 255, .7);");
        
        rootBorderPane.setTop(titleBar);
        rootBorderPane.setCenter(centerVBox);
        
        outputSocketCallVBox.setAlignment(Pos.CENTER_RIGHT);
        outputSocketVBox.setAlignment(Pos.CENTER_RIGHT);
        inputSocketCallVBox.setAlignment(Pos.CENTER_LEFT);
        inputSocketVBox.setAlignment(Pos.CENTER_LEFT);
        
        outputSocketVBox.getChildren().add(outputSocketCallVBox);
        inputSocketVBox.getChildren().add(inputSocketCallVBox);

        inputSocketVBox.setStyle("-fx-padding: 5;-fx-background-color:  rgba(255, 255, 255, .7);");
        outputSocketVBox.setStyle("-fx-padding: 5;-fx-background-color: rgba(255, 255, 255, .7);");
        inputSocketCallVBox.setStyle("-fx-background-color:  rgba(255, 255, 255, .7);");
        outputSocketCallVBox.setStyle("-fx-background-color: rgba(255, 255, 255, .7);");
        
        rootBorderPane.setLeft(inputSocketVBox);
        rootBorderPane.setRight(outputSocketVBox);
 
        getChildren().add(rootBorderPane);
        
    }
    

    public void addInputSocket(VariableInputSocket socket){
        
        removeInputSocket(socket);
        inputSocketVBox.getChildren().add(socket.getUISocket());
        getInputSockets().add(socket);

    }
    
    public void removeInputSocket(VariableInputSocket socket){

        inputSocketVBox.getChildren().remove(socket.getUISocket());
        getInputSockets().remove(socket);
    }
    
    public void addOutputSocket(VariableOutputSocket socket){
        
        removeOutputSocket(socket);
        outputSocketVBox.getChildren().add(socket.getUISocket());
        getOutputSockets().add(socket);

    }
    public void removeOutputSocket(VariableOutputSocket socket){

        outputSocketVBox.getChildren().remove(socket.getUISocket());
        getOutputSockets().remove(socket);

    }
    
    public void addInputCallSocket(UIInputCallSocket socket){
        
        removeInputCallSocket(socket);
        inputSocketCallVBox.getChildren().add((Node)socket.getUISocket());
        getInputCallSockets().add(socket);

    }
    
    public void removeInputCallSocket(UIInputCallSocket socket){

        inputSocketCallVBox.getChildren().remove(socket.getUISocket());
        getInputCallSockets().remove(socket);
    }
    
    public void addOutputCallSocket(UIOutputCallSocket socket){
        
        removeOutputCallSocket(socket);
        outputSocketCallVBox.getChildren().add(socket.getUISocket());
        getOutputCallSockets().add(socket);

    }
    public void removeOutputCallSocket(UIOutputCallSocket socket){

        outputSocketCallVBox.getChildren().remove(socket.getUISocket());
        getOutputCallSockets().remove(socket);

    }
    
    
    @Override
    public ObservableSet<VariableInputSocket> getInputSockets() {
        return socketSet.getInputSockets();
    }

    @Override
    public ObservableSet<VariableOutputSocket> getOutputSockets() {
        return socketSet.getOutputSockets();
    }
    
    @Override
    public ObservableSet<UIInputCallSocket> getInputCallSockets() {
        return socketSet.getInputCallSockets();
    }

    @Override
    public ObservableSet<UIOutputCallSocket> getOutputCallSockets() {
        return socketSet.getOutputCallSockets();
    }
    

}
