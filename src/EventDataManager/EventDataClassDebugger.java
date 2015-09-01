/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EventDataClassDebugger extends Stage{
    
    private final Class<?> className;
    
    private final BorderPane borderPane = new BorderPane();
    private final TextArea editor = new TextArea();
    private final TreeItem<Label> rootItem; 
    private final TreeView treeView = new TreeView();
    
    
    public EventDataClassDebugger(Class<?> className){
        super(StageStyle.UTILITY);
        
        this.className = className;
        
        Label rootLabel = new Label(className.getName());
        rootItem = new TreeItem<>();
        treeView.setRoot(rootItem);
        
        this.setTitle("EventData class debugger");

        borderPane.setCenter(editor);
        borderPane.setLeft(treeView);
        
        Scene scene = new Scene(borderPane);
        
        setScene(scene);
        
        show();
        
    }
    

    

}