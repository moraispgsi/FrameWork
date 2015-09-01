/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import javafx.util.Duration;


public class ProgressBarTreeItem extends TreeItem<Node>{
    
    private final Label label = new Label();
    private final ProgressBar bar = new ProgressBar();

    public ProgressBarTreeItem(String name,Node graphic) {
        
        bar.setPrefSize(80, 3);
        
        setGraphic(graphic);
        
        label.setText(name);
        bar.setProgress(-1);
        
        setValue(label);
        
        
    }
    
    public void showProgressBar(double time,Runnable runnable){
        setValue(bar);
        
        new Timeline(
            new KeyFrame(
                    Duration.ZERO,       
                    new KeyValue(bar.progressProperty(), 0)
            ),
            new KeyFrame(
                    Duration.millis(time), 
                    new KeyValue(bar.progressProperty(), 1)
            ),
            new KeyFrame(
                Duration.millis(time),
                ae -> {
                    setValue(label);
                    if(runnable != null)
                        runnable.run();
                }
            )
        ).play();
        
    }
    
    public void showProgressBar(){
        setValue(bar);
    }
    
    public void showLabel(){
        setValue(label);
    }
    
    public void setContextMenu(ContextMenu contextMenu){
        label.setContextMenu(contextMenu);
    }
    
}
