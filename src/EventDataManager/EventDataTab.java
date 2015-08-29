/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;


public class EventDataTab extends Tab {
    
    private final TextArea editor = new TextArea();
    private final String filePath;

    public EventDataTab(String filePath) {
        
        
        try {
            
            this.filePath = filePath;
            
            File file = new File(filePath);
            this.setText(file.getName());
            
            
            String code = new Scanner(file).useDelimiter("\\Z").next();
            
            
            editor.setOnKeyPressed(e->{
                
                if(e.isControlDown() && e.getCode().equals(KeyCode.S)){
                    
                    save();
                    
                }
            
            });
            
            editor.setText(code);
            
            editor.setPrefHeight(Integer.MAX_VALUE);
            
            setContent(editor);
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File path must exist.");
        }

        
    }
    
    public void save(){
        
        File file = new File(filePath);
        
        if(file.canWrite()){
            

            try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
                
                br.write(editor.getText());
                
                
            } catch (IOException ex) {
                Logger.getLogger(EventDataTab.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }        
        
    }
    
}
