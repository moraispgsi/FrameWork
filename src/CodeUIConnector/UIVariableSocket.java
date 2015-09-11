/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UIVariableSocket  extends UISocket{
    
    public static enum Type{INPUT,OUTPUT};
    
    private final Color COLOR_IDLE = Color.LIGHTGREY;
    private final Color COLOR_CONNECTED = Color.LIGHTBLUE;
    private final Color COLOR_AVAILABLE = Color.LIGHTGREEN;

    private ObjectProperty<Bounds> plugSceneBounds = new SimpleObjectProperty<>();
    private final HBox hBox = new HBox();
    private final Rectangle plug = new Rectangle();
    private final Label nameLabel = new Label();
    
    public UIVariableSocket(Type type,double spacing,String name) {
        
        
        hBox.setSpacing(spacing);
        
        nameLabel.setText(name);
        
        plug.setWidth(4);
        plug.setHeight(20);
        plug.setFill(COLOR_IDLE);
        hBox.getChildren().addAll(plug,nameLabel);
        if(type == Type.OUTPUT){
            setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        getChildren().add(hBox);
        
        Platform.runLater(()->{
            plugSceneBounds.set(plug.localToScene(plug.getBoundsInLocal()));
        });
        
        
        localToSceneTransformProperty().addListener((observable,  oldValue,  newValue)->{
            plugSceneBounds.set(plug.localToScene(plug.getBoundsInLocal()));
        });
        
    }

    @Override
    public ObjectProperty<Bounds> getPlugBoundsProperty() {
        return plugSceneBounds;
    }
    
    @Override
    public void setConnected(){
        plug.setFill(COLOR_CONNECTED);
    }
    
    @Override
    public void setIdle(){
        plug.setFill(COLOR_IDLE);
    }
    
    @Override
    public void setAvailable(){
        plug.setFill(COLOR_AVAILABLE);
    }
    
    

}