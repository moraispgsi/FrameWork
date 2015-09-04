/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicClassUtils;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Utility class that allow usage of reflexion
 * 
 * @author Ricardo José Horta Morais
 */
public class DynamicClassUtils {

    /**
     * Compiles a source file and return a class file
     * @param sourceFile source file to compile
     * @return class file generated
     */
    public static File compileSource(File sourceFile) {

        if (sourceFile == null) {
            throw new RuntimeException("File cannot be null.");
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        
        if (compiler.run(null, null, null, sourceFile.getPath(),"-parameters") != 0) {
            throw new RuntimeException("Error compiling.");
        }

        String fileName = "";

        int i = sourceFile.getName().lastIndexOf('.');
        if (i > 0) {
            fileName = sourceFile.getName().substring(0, i);
        }

        File classFile = new File(sourceFile.getParent()
                .concat("\\")
                .concat(fileName)
                .concat(".class"));

        return classFile;

    }
    
    /**
     * Loads a class file to the default classLoader and returns its class
     * @param file class file
     * @return Class extracted from the file
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    public static Class<?> loadClassFile(File file) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        String fileName = "";

        int i = file.getName().lastIndexOf('.');
        
        if (i > 0) {
            fileName = file.getName().substring(0, i);
        }
        

        // Load and instantiate compiled class.
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.getParentFile().getParentFile().toURI().toURL()});
        Class<?> cls = Class.forName(file.getParentFile().getName() + "." + fileName, true, classLoader);
        return cls;
    }

}
