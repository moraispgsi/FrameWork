/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UIMethod extends BorderPane{

    
    public Method method;
    public String classFileUrl;

    public UIMethod(String classFileUrl,Method method) {
        this.method = method;
        
        
        DraggableTitle titleBar = new DraggableTitle(method.getName(),layoutXProperty(),layoutYProperty());

        setStyle("-fx-border-width: 1; -fx-border-color: grey;-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 0 0;");
        
        titleBar.setStyle("-fx-padding: 5;-fx-background-color: #73BFD9;-fx-background-radius: 10 10 0 0;");
        
        setTop(titleBar);
        


        this.classFileUrl = classFileUrl;
        
        VBox centerVBox = new VBox(5);
        
        centerVBox.setStyle("-fx-padding: 5;-fx-background-color:  #E1F2F7;");
        
        StackPane spacer = new StackPane();
        spacer.setPrefWidth(20);
        centerVBox.getChildren().add(spacer);
        
        setCenter(centerVBox);
        
        VBox leftVBox = new VBox(5);
        
        leftVBox.setStyle("-fx-padding: 5;-fx-background-color:  #E1F2F7;-fx-background-radius: 0 0 0 10;");
        
        for(Parameter param : method.getParameters()){
           
            HBox hBox = new HBox(10);
            hBox.setAlignment(Pos.BASELINE_LEFT);
            
            RadioButton circle = new RadioButton();
            Label paramTypeLabel = new Label(param.getName()+":"+param.getType().getSimpleName());
            
            hBox.getChildren().addAll(circle,paramTypeLabel);
            
            leftVBox.getChildren().add(hBox);
            
        }
        setLeft(leftVBox);
        
        VBox rightVBox = new VBox(5);
        
        rightVBox.setStyle("-fx-padding: 5; -fx-background-color:  #E1F2F7;-fx-background-radius: 0 0 10 0;");
        
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        if(!method.getReturnType().equals(Void.TYPE)){
            
            Label returnType = new Label(method.getReturnType().getSimpleName());
            RadioButton circle = new RadioButton();
            hBox.getChildren().addAll(returnType,circle);
            
        }
        rightVBox.getChildren().addAll(hBox);
        setRight(rightVBox);
        
        
        
    }

}
