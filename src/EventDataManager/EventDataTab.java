/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;


public class EventDataTab extends Tab {
    
    private final TextArea editor = new TextArea();
    private final String filePath;

    public EventDataTab(String filePath) {
        
        
        try {
            
            this.filePath = filePath;
            
            File file = new File(filePath);
            this.setText(file.getName());
            
            
            String code = new Scanner(file).useDelimiter("\\Z").next();
            
            editor.setText(code);
            
            editor.setPrefHeight(Integer.MAX_VALUE);
            
            setContent(editor);
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File path must exist.");
        }

        
    }
    
    
    
}
