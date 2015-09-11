/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DraggableTitle extends StackPane {
    
    class Delta { double x, y; }
    final Delta dragDelta = new Delta();
    
    private final Label titleLabel = new Label();
    

    public DraggableTitle(String title,DoubleProperty xProperty,DoubleProperty yProperty) {
        
        titleLabel.setText(title);

        getChildren().add(titleLabel);
        
        setStyle("-fx-padding: 5;");
        
        setOnMousePressed(e->{
            
            dragDelta.x = xProperty.getValue() - e.getSceneX();
            dragDelta.y = yProperty.getValue() - e.getSceneY();
            setCursor(Cursor.MOVE);
            
        });
        
        setOnMouseReleased(e->{
            
            setCursor(Cursor.HAND);
            
        });
        
        setOnMouseDragged(e->{
            xProperty.set(e.getSceneX() + dragDelta.x);
            yProperty.set(e.getSceneY() + dragDelta.y);
        });
        
        setOnMouseEntered(e->{
            setCursor(Cursor.HAND);
        });
        
    }
    
    
    
    
}
