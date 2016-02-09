/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.UIToolBox;

import CodeUIConnector.UIMethodFlowBoard;
import java.lang.reflect.Method;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Joana
 */
public class FunctionInvokeTool extends Stage implements Tool {

    private Pane pane;
    
    public FunctionInvokeTool() {
        super(StageStyle.UTILITY);
        setTitle("Function invoke creator");
        
        pane = new Pane();
        pane.setPrefSize(400,600);
        Scene scene = new Scene(pane);
        
        
        
        
        
        setScene(scene);
    }

    
    
    @Override
    public String getDisplayName() {
        return "Invoke Function";
    }

    @Override
    public void execute(UIMethodFlowBoard uiMethodFlowBoard) {
        
        VBox vBox = new VBox();
        pane.getChildren().add(vBox);
        
        
        Label label1 = new Label("Select Method");
        ComboBox methodsBox = new ComboBox();
        
        HBox hBox1 = new HBox(label1,methodsBox);
        
        vBox.getChildren().addAll(hBox1);
        
        Class<?> className = uiMethodFlowBoard.getMethod().getDeclaringClass();
        
        for(Method method : className.getDeclaredMethods())
            methodsBox.getItems().add(method.getName());
        
        
        
        
        this.showAndWait();

    }
    
    
    
}
