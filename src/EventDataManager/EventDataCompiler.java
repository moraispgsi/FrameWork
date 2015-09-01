/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ricado José Horta Morais
 */
public class EventDataCompiler extends Stage {
    
    private ProgressBar progressBar = new ProgressBar();

    public EventDataCompiler(String filePath) {
        super(StageStyle.TRANSPARENT);
        
        
        
        Platform.runLater(()->{
                
                File file = DynamicClassUtils.DynamicClassUtils.compileSource(new File(filePath));
                
                try{
                    Class<?> className = DynamicClassUtils.DynamicClassUtils.loadClassFile(file);
                    
                }   catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException ex) {
                        Logger.getLogger(EventDataCompiler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                progressBar.setProgress(1);
                
        });
        
        progressBar.setProgress(-1);
        
        
        Scene scene = new Scene(progressBar);
        setScene(scene);
        show();
        
    }
    
    
    
    
}
