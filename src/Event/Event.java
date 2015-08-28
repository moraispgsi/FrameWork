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
 *
 * @author Ricardo José Horta Morais
 * @param <D>
 */
public interface Event <D extends EventData> {
    
    public EventType getEventType();
    public D getData();
    
    public static String getEventTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Event\\EventTemplate.txt");
            String template = new Scanner(templateFile).useDelimiter("\\Z").next();
            template = template.replaceAll("%EVENT_CLASS", className);
            template = template.replaceAll("%EVENT_PACKAGE", packageName);
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static String getEventDataTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Event\\EventDataTemplate.txt");
            String template = new Scanner(templateFile).useDelimiter("\\Z").next();
            template = template.replaceAll("%EVENT_DATA_CLASS", className);
            template = template.replaceAll("%EVENT_DATA_PACKAGE", packageName);
            
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static File makeEvent(String packageName,String className,String dirPath){
        
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
            BufferedWriter bw = new BufferedWriter(sw);
            bw.write(templateString);
            
            bw.flush();
            bw.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }
    
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
            BufferedWriter bw = new BufferedWriter(sw);
            bw.write(templateString);
            
            bw.flush();
            bw.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }

}
