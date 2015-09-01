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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class EventDataTab extends Tab {
    
    private final File eventDataIconFile = new File("src/Main/EventData.png");
    private final Image eventDataIconImage = new Image(eventDataIconFile.toURI().toString());

    private final TextArea editor = new TextArea();
    private final String filePath;
    private final String fileName;
    private boolean wasEdited = false;

    public EventDataTab(String filePath) {

        try {
   
            File file = new File(filePath);
            
            if(!file.exists())
                throw new RuntimeException("File must exist.");
                
            this.filePath = filePath;
            
            fileName = file.getName();
            setText(fileName);
            

            setGraphic(new ImageView(eventDataIconImage));
            String code;
            try (Scanner scanner = new Scanner(file)) {
                code = scanner.useDelimiter("\\Z").next();
            }

            initEditor(code);

            setContent(editor);
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File path must exist.");
        }

    }

    
    private void initEditor(String code){
        
        editor.setPrefHeight(Integer.MAX_VALUE);
        editor.setText(code);
        editor.textProperty().addListener(e->{

            setText(fileName + "*");
            setStyle("-fx-font-weight: bold;"); 
            wasEdited = true;
        });
        
        editor.setOnKeyPressed(e->{
                
            if(e.isControlDown() && e.getCode().equals(KeyCode.S)){

                save();

            }

        });
        
    }
    
    
    public void save(){
        
        File file = new File(filePath);
        
        if(file.canWrite()){
            

            try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
                
                br.write(editor.getText());
                
                
            } catch (IOException ex) {
                Logger.getLogger(EventDataTab.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setText(fileName);
            setStyle("");
            wasEdited = false;
            
        }        
        
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isWasEdited() {
        return wasEdited;
    }
    
    
}
