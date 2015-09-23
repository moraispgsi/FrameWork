/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.UI;


import CodeUIConnector.SocketPane.UISocket;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UIParamSocket  extends UISocket{
    
    public static enum Type{INPUT,OUTPUT};
    
    private final Color COLOR_IDLE = Color.LIGHTGREY;
    private final Color COLOR_CONNECTED = Color.LIGHTBLUE;
    private final Color COLOR_AVAILABLE = Color.LIGHTGREEN;

    private ObjectProperty<Bounds> plugSceneBounds = new SimpleObjectProperty<>();
    private final HBox hBox = new HBox();
    private final Rectangle plug = new Rectangle();
    private final Label nameLabel = new Label();
    
    public UIParamSocket(Type type,double spacing,String name) {
        
        
        hBox.setSpacing(spacing);
        hBox.setAlignment(Pos.CENTER);
        
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
    public void showConnected(){
        super.showConnected();
        plug.setFill(COLOR_CONNECTED);
        
        super.showConnected();
    }

    @Override
    public void showAvailable(){
        plug.setFill(COLOR_AVAILABLE);
        
        super.showAvailable();
    }
    
    @Override
    public void showIdle(){
        plug.setFill(COLOR_IDLE);
        
        super.showIdle();
    }

    
    

}
