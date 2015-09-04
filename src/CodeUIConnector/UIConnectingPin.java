/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.scene.control.RadioButton;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;



public class UIConnectingPin extends RadioButton{
    
    private final static DataFormat connectingPin = new DataFormat("UIConnectingPin");
    
    public UIConnectingPin() {
        
        setOnDragDetected(e->{
   
            
        
        });
        
        
        setOnDragOver(e->{
   
            
        
        });
        
        
        
    }
    
    
    
}
