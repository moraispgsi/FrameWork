/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class IOSocketPane extends Pane {
    
    private final IOSocketSet socketSet = new IOSocketSet();
    
    private final BorderPane rootBorderPane = new BorderPane();
    private final DraggableTitle titleBar;
    private final double IO_VERTICAL_SPACING = 5;
    private final double IO_HORIZONTAL_SPACING = 5;
    
    private final VBox centerVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox leftVBox = new VBox(IO_VERTICAL_SPACING);
    private final VBox rightVBox = new VBox(IO_VERTICAL_SPACING);
    
    private final Map<String,Node> inputSockets = new HashMap<>();
    private final Map<String,Node> outputSockets = new HashMap<>();
   
    public IOSocketPane(String title) {
  
        
        
        titleBar = new DraggableTitle(title,layoutXProperty(),layoutYProperty());

        //setStyle("-fx-border-width: 1; -fx-border-color: grey;-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 0 0;");
        
        titleBar.setStyle("-fx-padding: 5;-fx-background-color: rgba(144, 195, 212, .7);");
        
        rootBorderPane.setTop(titleBar);

        centerVBox.setStyle("-fx-padding: 5; -fx-background-color: rgba(255, 255, 255, .7);");

        rootBorderPane.setCenter(centerVBox);
        
        leftVBox.setStyle("-fx-padding: 5; -fx-background-color:  rgba(255, 255, 255, .7);");
        
        rootBorderPane.setLeft(leftVBox);

        rightVBox.setStyle("-fx-padding: 5; -fx-background-color: rgba(255, 255, 255, .7);");

        rootBorderPane.setRight(rightVBox);
        
        getChildren().add(rootBorderPane);
        
    }
    

    public UISocket addInput(String name){
        
        removeInput(name);

        HBox hBox = new HBox(IO_HORIZONTAL_SPACING);
        hBox.setAlignment(Pos.CENTER);
        
        UISocket socket = new UISocket();
        Label nameLabel = new Label(name);
        
        hBox.getChildren().addAll(socket,nameLabel);
        
        leftVBox.getChildren().add(hBox);
        
        inputSockets.put(name, hBox);
        
        return socket;
        
    }
    
    public void removeInput(String name){
        if(inputSockets.containsKey(name)){
            leftVBox.getChildren().remove(inputSockets.get(name));
            inputSockets.remove(name);
        }
    }
    
    public UISocket addOutput(String name){
        
        removeOutput(name);
        
        HBox hBox = new HBox(IO_HORIZONTAL_SPACING);
        hBox.setAlignment(Pos.CENTER);
        
        Label nameLabel = new Label(name);
        UISocket socket = new UISocket();

        hBox.getChildren().addAll(nameLabel,socket);
        
        rightVBox.getChildren().add(hBox);
        
        outputSockets.put(name, hBox);
        
        return socket;
        
    }
    public void removeOutput(String name){
        
        if(outputSockets.containsKey(name)){
            leftVBox.getChildren().remove(outputSockets.get(name));
            outputSockets.remove(name);
        }
        
    }
    

}
