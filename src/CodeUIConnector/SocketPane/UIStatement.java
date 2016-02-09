
package CodeUIConnector.SocketPane;

import CodeUIConnector.DraggableTitle;
import CodeUIConnector.SocketSets.Pluggable;
import CodeUIConnector.SocketSets.SocketSet;
import CodeUIConnector.InvokeSockets.Controller.InvokeInput;
import CodeUIConnector.InvokeSockets.Controller.InvokeOutput;
import CodeUIConnector.ParamSockets.Controller.ParamInput;
import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import javafx.collections.ObservableSet;
import javafx.geometry.Insets;
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
        
        centerVBox.setPadding(new Insets(5, 10, 5, 10));
        
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
    
    public void addInvokeInput(InvokeInput socket){
        
        removeCallInput(socket);
        callInputVBox.getChildren().add((Node)socket.getUISocket());
        getInvokelInputs().add(socket);

    }
    
    public void removeCallInput(InvokeInput socket){

        callInputVBox.getChildren().remove(socket.getUISocket());
        getInvokelInputs().remove(socket);
    }
    
    public void addInvokeOutput(InvokeOutput socket){
        
        removeCallOutput(socket);
        callOutputVBox.getChildren().add(socket.getUISocket());
        getInvokeOutputs().add(socket);

    }
    public void removeCallOutput(InvokeOutput socket){

        callOutputVBox.getChildren().remove(socket.getUISocket());
        getInvokeOutputs().remove(socket);

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
    public ObservableSet<InvokeInput> getInvokelInputs() {
        return socketSet.getInvokelInputs();
    }

    @Override
    public ObservableSet<InvokeOutput> getInvokeOutputs() {
        return socketSet.getInvokeOutputs();
    }
    
    
}
