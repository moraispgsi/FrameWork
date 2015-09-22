/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketPane;

import CodeUIConnector.DraggableTitle;
import CodeUIConnector.SocketSets.Pluggable;
import CodeUIConnector.SocketSets.SocketSet;
import CodeUIConnector.CallSockets.CallInput;
import CodeUIConnector.CallSockets.CallOutput;
import CodeUIConnector.ParamSockets.ParamInput;
import CodeUIConnector.ParamSockets.ParamOutput;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class UIStatement extends Region implements Pluggable {
    
    private final SocketSet socketSet = new SocketSet();
    
    private final BorderPane rootBorderPane = new BorderPane();
    private final DraggableTitle titleBar;
    private final double IO_VERTICAL_SPACING = 5;
    
    private final VBox centerVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox callInputVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox callOutputVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox paramInputVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox paramOutputVBox = new VBox(IO_VERTICAL_SPACING);
    
     

    public UIStatement(String title) {
  

        titleBar = new DraggableTitle(title,layoutXProperty(),layoutYProperty());

        //setStyle("-fx-border-width: 1; -fx-border-color: grey;-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 0 0;");
        
        titleBar.setStyle("-fx-padding: 5;-fx-background-color: rgba(144, 195, 212, .7);");
        centerVBox.setStyle(" -fx-background-color: rgba(255, 255, 255, .7);");
        
        rootBorderPane.setTop(titleBar);
        rootBorderPane.setCenter(centerVBox);
        
        
        callOutputVBox.setAlignment(Pos.CENTER_RIGHT);
        paramOutputVBox.setAlignment(Pos.CENTER_RIGHT);
        callInputVBox.setAlignment(Pos.CENTER_LEFT);
        paramInputVBox.setAlignment(Pos.CENTER_LEFT);
        
        paramOutputVBox.getChildren().add(callOutputVBox);
        paramInputVBox.getChildren().add(callInputVBox);
        
        paramInputVBox.setStyle("-fx-padding: 5;-fx-background-color:  rgba(255, 255, 255, .7);");
        paramOutputVBox.setStyle("-fx-padding: 5;-fx-background-color: rgba(255, 255, 255, .7);");
        
        rootBorderPane.setLeft(paramInputVBox);
        rootBorderPane.setRight(paramOutputVBox);
 
        getChildren().add(rootBorderPane);
        

    }
    

    public void addInputParam(ParamInput socket){
        
        removeInputParam(socket);
        paramInputVBox.getChildren().add(socket.getUISocket());
        getInputParams().add(socket);

    }
    
    public void removeInputParam(ParamInput socket){

        paramInputVBox.getChildren().remove(socket.getUISocket());
        getInputParams().remove(socket);
    }
    
    public void addOutputParam(ParamOutput socket){
        
        removeOutputParam(socket);
        paramOutputVBox.getChildren().add(socket.getUISocket());
        getOutputParams().add(socket);

    }
    public void removeOutputParam(ParamOutput socket){

        paramOutputVBox.getChildren().remove(socket.getUISocket());
        getOutputParams().remove(socket);

    }
    
    public void addCallInput(CallInput socket){
        
        removeCallInput(socket);
        callInputVBox.getChildren().add((Node)socket.getUISocket());
        getCallInputs().add(socket);

    }
    
    public void removeCallInput(CallInput socket){

        callInputVBox.getChildren().remove(socket.getUISocket());
        getCallInputs().remove(socket);
    }
    
    public void addCallOutput(CallOutput socket){
        
        removeCallOutput(socket);
        callOutputVBox.getChildren().add(socket.getUISocket());
        getCallOutputs().add(socket);

    }
    public void removeCallOutput(CallOutput socket){

        callOutputVBox.getChildren().remove(socket.getUISocket());
        getCallOutputs().remove(socket);

    }
    
    
    @Override
    public ObservableSet<ParamInput> getInputParams() {
        return socketSet.getInputParams();
    }

    @Override
    public ObservableSet<ParamOutput> getOutputParams() {
        return socketSet.getOutputParams();
    }
    
    @Override
    public ObservableSet<CallInput> getCallInputs() {
        return socketSet.getCallInputs();
    }

    @Override
    public ObservableSet<CallOutput> getCallOutputs() {
        return socketSet.getCallOutputs();
    }
    

}
