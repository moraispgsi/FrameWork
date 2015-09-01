/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifunctional;

import Command.CommandCenter;
import Event.EventDispatcher;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * The multifunction interface identifies a class as being composed of many
 * functionalities at runtime.
 * 
 * @author Ricado José Horta Morais
 */
public interface Multifunctional extends EventDispatcher, CommandCenter {
    
    /**
     * Adds a functionality if there is no functionality of the same class present
     * @param functionality functionality to be added 
     */
    public void addFunctionality (Functionality functionality);
    
    /**
     * Removes a functionality from the multifunctional
     * @param functionalityClass functionality class to be remove
     */
    public void removeFunctionality (Class<? extends Functionality> functionalityClass);
    
    /**
     * Test if this multifunctionality has the functionality class
     * @param functionalityClass class of the functionality
     * @return true if it has this class functionality, else false
     */
    public boolean hasFunctionality (Class<? extends Functionality>  functionalityClass);
    
    /**
     * Finds a match of the class functionality to cast to and returns it as one
     * @param <T> Subclass of functionality
     * @param functionalityClass functionality class to search the multifunctional
     * @return the object casted to the functionality class
     */
    public <T extends Functionality> T as(Class<T> functionalityClass);
    
    /**
     * TODO: Use resources loaded at startup
     * Opens a template from a file of a functionality java code and inserts the class name and package name before returning it 
     * @param packageName name of the package
     * @param className class of the package
     * @return template with the name of the package and class name
     */
    public static String getFunctionalityTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Multifunctional\\FunctionalityTemplate.txt");
            String template;
            try(Scanner scanner = new Scanner(templateFile)){
                template = scanner.useDelimiter("\\Z").next();
            }
            template = template.replaceAll("%CLASS_NAME", className);
            template = template.replaceAll("%PACKAGE_NAME", packageName);
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Creates a functionality in a specific folder
     * 
     * @param packageName package name that will also create a subdirectory for the file
     * @param className class name will determine the name of the file
     * @param dirPath directory that will hold the package and the file
     * @return File that was created
     */
    public static File makeFunctionality(String packageName,String className,String dirPath){
        
        String regex = "^[a-zA-Z]+$";
        if (!className.matches(regex)) { 
             throw new RuntimeException("Class name is not a valid one.");
        }
        if (!packageName.matches(regex)) { 
             throw new RuntimeException("Package name is not a valid one.");
        }
        
        File directory = new File(dirPath);
        
        directory.mkdirs();
        
        if(!directory.exists() || !directory.isDirectory())
            throw new RuntimeException("Directory path must point to a directory.");
        
        File packageFolder = new File(dirPath + "\\"+ packageName);
        packageFolder.mkdir();
        
        File filePath = new File(dirPath + "\\"+ packageName+"\\"+className+".java");
        
        try{
                 
            String templateString = getFunctionalityTemplate(packageName,className);

            FileWriter sw = new FileWriter(filePath);
            try (BufferedWriter bw = new BufferedWriter(sw)) {
                bw.write(templateString);
                
                bw.flush();
            }
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Multifunctional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }
    
}
