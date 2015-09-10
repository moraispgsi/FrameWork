/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.RED;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class UISocket extends Rectangle{


    
    private ObjectProperty<Bounds> sceneBounds = new SimpleObjectProperty<>();
    
    public UISocket() {
        
        this.setWidth(4);
        this.setHeight(20);
        this.setFill(LIGHTBLUE);
        
        localToSceneTransformProperty().addListener((observable,  oldValue,  newValue)->{
            sceneBounds.set(this.localToScene(this.getBoundsInLocal()));
        });
        
    }

    public ObjectProperty<Bounds> getBoundsProperty() {
        return sceneBounds;
    }
    
    

}
