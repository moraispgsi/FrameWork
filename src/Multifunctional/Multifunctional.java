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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moraisPGSI
 */
public interface Multifunctional extends EventDispatcher, CommandCenter {

    public void addFunctionality (Functionality functionality);
    
    public void removeFunctionality (Class<? extends Functionality> functionality);
    
    public boolean hasFunctionality (Class<? extends Functionality>  functionality);

    public <T extends Functionality> T as(Class<T> functionality);
    
    public static String getFunctionalityTemplate(String packageName,String className){
        
        try {
            File templateFile = new File("src\\Multifunctional\\FunctionalityTemplate.txt");
            String template = new Scanner(templateFile).useDelimiter("\\Z").next();
            template = template.replaceAll("%CLASS_NAME", className);
            template = template.replaceAll("%PACKAGE_NAME", packageName);
            return template;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static File makeFunctionality(String packageName,String className,String dirPath){
        
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
                 
            String templateString = getFunctionalityTemplate(packageName,className);

            FileWriter sw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(sw);
            bw.write(templateString);
            
            bw.flush();
            bw.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Multifunctional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filePath;
        
    }
    
}
