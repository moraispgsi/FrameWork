/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import CodeUIConnector.UICanvas;
import CodeUIConnector.UIEndMethod;
import CodeUIConnector.UIMethod;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EventDataClassDebugger extends Stage{
    
    private final Class<?> className;
    
    private final BorderPane borderPane = new BorderPane();
    private final TextArea editor = new TextArea();
    private final TreeItem<Node> rootItem; 
    private final TreeView treeView = new TreeView();
    
    private Object object;
    
    
    public EventDataClassDebugger(Class<?> className){
        super(StageStyle.UTILITY);
        setTitle("EventData class debugger");
        
        this.className = className;

        Label rootLabel = new Label(className.getName());
        rootItem = new TreeItem<>(rootLabel);
        treeView.setRoot(rootItem);
        
        buildTreeView();
        
        
        borderPane.setLeft(treeView);
        
        Scene scene = new Scene(borderPane);
        
        setScene(scene);
        
        show();
        
    }
    
    private void buildTreeView(){
        
        
        Pane pane = new Pane();
        for(Method method : className.getDeclaredMethods()){
            
            
            Label methodLabel = new Label(method.toGenericString());
            TreeItem<Node> methodItem = new TreeItem<>(methodLabel);
            rootItem.getChildren().add(methodItem);
            
            pane.setPrefSize(500,500);
            
            Region canvas = new UICanvas("",method);
            canvas.setPrefSize(200,200);
            pane.getChildren().add(canvas);

        }
        borderPane.setCenter(pane);
        
        for(Field field : className.getDeclaredFields()){

            Label fieldLabel = new Label(field.toGenericString());
            TreeItem<Node> fieldItem = new TreeItem<>(fieldLabel);
            rootItem.getChildren().add(fieldItem);
        }

        for(Constructor constructor : className.getDeclaredConstructors()){

            Label constructorLabel = new Label(constructor.toGenericString());
            TreeItem<Node> constructorItem = new TreeItem<>(constructorLabel);
            rootItem.getChildren().add(constructorItem);

        }
        
    }
    

}