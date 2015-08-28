/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import EventDataManager.EventDataManager;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Test  extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        DirectoryChooser dirChooser = new DirectoryChooser();
        File file = dirChooser.showDialog(primaryStage);

        EventDataManager eDataManager = new EventDataManager(primaryStage,file);
        
        Scene scene = new Scene(eDataManager, 600, 400);
        
        primaryStage.setTitle("eData Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
