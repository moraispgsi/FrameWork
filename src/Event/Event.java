/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Represents an event
 * @author Ricardo José Horta Morais
 * @param <D> EventData subclass
 */
public interface Event <D extends EventData> {
    
    /**
     * Gets the type of the event
     * @return type of the event
     */
    public EventType getEventType();
    
    /**
     * Gets the data of the event
     * @return data of the event
     */
    public D getData();
    
    /**
     * Gets an event template by reading a file
     * @param packageName package name
     * @param className class name
     * @return event template
     */
    public static String getEventTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Event\\EventTemplate.txt");
            String template;
            try (Scanner scanner = new Scanner(templateFile)) {
                template = scanner.useDelimiter("\\Z").next();
            }
            template = template.replaceAll("%EVENT_CLASS", className);
            template = template.replaceAll("%EVENT_PACKAGE", packageName);
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Gets an event data template by reading a file
     * 
     * @param packageName package name
     * @param className class name
     * @return event data template
     */
    public static String getEventDataTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Event\\EventDataTemplate.txt");
            String template;
            try (Scanner scanner = new Scanner(templateFile)) {
                template = scanner.useDelimiter("\\Z").next();
            }
            template = template.replaceAll("%EVENT_DATA_CLASS", className);
            template = template.replaceAll("%EVENT_DATA_PACKAGE", packageName);
            
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    /**
     * Creates an event file in a directory path 
     * 
     * @param packageName package name
     * @param className class name
     * @param dirPath directory path where the file will be made
     * @return file to the event
     */
    public static File createEventFile(String packageName,String className,String dirPath){
        
        String regex = "^[a-zA-Z]+$";
        if (!className.matches(regex)) { 
             throw new RuntimeException("Class name should have only alphabete characters.");
        }
        if (!packageName.matches(regex)) { 
             throw new RuntimeException("Package name should have only alphabete characters.");
        }
        
        File directory = new File(dirPath);
        
        directory.mkdirs();
        
        if(!directory.exists() || !directory.isDirectory())
            throw new RuntimeException("Directory path must point to a directory.");
        
        File packageFolder = new File(dirPath + "\\"+ packageName);
        packageFolder.mkdir();
        
        File filePath = new File(dirPath + "\\"+ packageName+"\\"+className+".java");
        
        try{
                 
            String templateString = getEventTemplate(packageName,className);

            FileWriter sw = new FileWriter(filePath);
            try (BufferedWriter bw = new BufferedWriter(sw)) {
                bw.write(templateString);
                
                bw.flush();
            }
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }
    
    /**
     * Creates an event data file in a directory path 
     * 
     * @param packageName package name
     * @param className class name
     * @param dirPath directory path where the file will be made
     * @return file to the event
     */
    public static File makeEventData(String packageName,String className,String dirPath){
        
        String regex = "^[a-zA-Z_$][a-zA-Z\\d_$]*$";
        if (!className.matches(regex)) { 
             throw new RuntimeException("Class name invalid.");
        }
        if (!packageName.matches(regex)) { 
             throw new RuntimeException("Package name invalid.");
        }
        
        File directory = new File(dirPath);
        
        directory.mkdirs();
        
        if(!directory.exists() || !directory.isDirectory())
            throw new RuntimeException("Directory path must point to a directory.");
        
        File packageFolder = new File(dirPath + "\\"+ packageName);
        packageFolder.mkdir();
        
        File filePath = new File(dirPath + "\\"+ packageName+"\\"+className+".java");
        
        try{
                 
            String templateString = getEventDataTemplate(packageName,className);

            FileWriter sw = new FileWriter(filePath);
            try (BufferedWriter bw = new BufferedWriter(sw)) {
                bw.write(templateString);
                
                bw.flush();
            }
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }

}
