/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import java.io.File;
import java.lang.reflect.Method;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;


public class UICanvas extends StackPane {
    
    private File file;
    
    private Method method;

    public UICanvas(File file, Method method) {
        this.file = file;
        this.method = method;
        
        
        
    }
    
    
    

    
}
