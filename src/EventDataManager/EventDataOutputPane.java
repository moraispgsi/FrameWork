/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;




public class EventDataOutputPane extends BorderPane{
    
    private final TextArea output = new TextArea();
    private final Label title = new Label("Output");
    
    public EventDataOutputPane(){
        
        setTop(title);
        
        output.setEditable(false);
        setCenter(output);
        
        output.textProperty().addListener(e->{
        
            output.setScrollTop(Double.MAX_VALUE);
        
        
        });
        
        
    }
    
    public void println(String msg){

        output.appendText(msg+"\n");
        
    }
    
    
}
