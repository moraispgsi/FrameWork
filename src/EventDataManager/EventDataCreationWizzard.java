/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Catarina
 */
public class EventDataCreationWizzard extends Stage{

    private Label packageNameLabel = new Label("Package name:");
    private Label classNameLabel = new Label("Class name:");

    private TextField packageNameField = new TextField();
    private TextField classNameField = new TextField();
    private final String javaKeywords;
    
    
    private EventDataCreationWizzard(){
        super(StageStyle.UTILITY);
        try {
            javaKeywords = new Scanner(new File("src\\EventDataManager\\JavaKeywords.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Cant load java keywords file."); 
        }
    }        
            
    public EventDataCreationWizzard(File projectFile) {
        this();

        this.setTitle("Create Event Data");
        
        buildStage(projectFile);

    }
    
    public EventDataCreationWizzard(File projectFile,String packageName) {
        this();
        
        this.setTitle("Create Event Data");
        
        packageNameField.setText(packageName);
        
        buildStage(projectFile);

    }
    
    
    private void buildStage(File projectFile){
        
        
        VBox vBox = new VBox();
        
        GridPane gridPane = new GridPane();

        gridPane.setStyle("-fx-padding: 10 5 10 5;");

        
        Button genEventDataBtn = new Button("Generate eData");

        gridPane.add(packageNameLabel, 0, 0);
        gridPane.add(packageNameField, 1, 0);
        
        gridPane.add(classNameLabel, 0, 1);
        gridPane.add(classNameField, 1, 1);
        
        gridPane.add(genEventDataBtn, 1, 2);
        
        vBox.getChildren().addAll(gridPane);
        
        genEventDataBtn.prefWidthProperty().bind(packageNameField.widthProperty());
        
        Stage stage = this;
        
        genEventDataBtn.setOnAction(e->{
            
            if(!isValidClass(classNameField.getText()) ||
                    !isValidPackage(packageNameField.getText()))
                return;
            
            Event.Event.makeEventData(packageNameField.getText(), classNameField.getText(), projectFile.getAbsolutePath());
            stage.close();
        });
        
        
        Scene scene = new Scene(vBox);
        
        setScene(scene);
    }
    
    private boolean isJavaKeyword(String string){
        
        Pattern findJavaKeywords = Pattern.compile("("+string+"$|"+string+"[\\s\\n]|^"+string+"[\\s\\n])");
        
        Matcher match1 = findJavaKeywords.matcher(javaKeywords);        

        return match1.find();
        
    }
    
    private boolean isValidClass(String className){

        
        if(isJavaKeyword(className))
           return false; 
        
        Pattern findExtension = Pattern.compile("[a-zA-Z_$][a-zA-Z\\d_$]*");
        
        Matcher match2 = findExtension.matcher(className);

        return match2.matches();

    }
    
    private boolean isValidPackage(String packageName){
        
        if(isJavaKeyword(packageName))
           return false; 
        
        Pattern findExtension = Pattern.compile("[a-zA-Z_$][a-zA-Z\\d_$]*");

        Matcher match = findExtension.matcher(packageName);

        return match.matches();

    }
    
 
    
    
}
