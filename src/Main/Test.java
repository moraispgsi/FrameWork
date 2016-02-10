/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import EventDataManager.EventDataManager;
import Statements.Generic.GeneralPurpose.FileOpenStatement;
import Statements.OutputNotAvailableException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author testing
 */
public class Test  {//extends Application {
    
   /*
    @Override
    public void start(Stage primaryStage) {
        
        
        File file = new File("C:\\Users\\Morai\\Desktop\\Project");

        EventDataManager eDataManager = new EventDataManager(primaryStage,file);
        
        Scene scene = new Scene(eDataManager, 600, 400);
        
        primaryStage.setTitle("eData Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /**
     * Main
     * @param args args
     */
    
    public static void main(String[] args) {
        String nome = "C:\\Users\\Morai\\Desktop\\Teste.txt";
        Path file = FileSystems.getDefault().getPath(nome);
        try (InputStream in = Files.newInputStream(file)) {
            
            Scanner sc = new Scanner(in);
            
            System.out.println(sc.next());
            
        } catch (IOException x) {
            System.err.println(x);
        }
        
        
    }

}
