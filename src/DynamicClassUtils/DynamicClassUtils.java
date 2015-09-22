/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicClassUtils;


import Main.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Utility class that allow usage of reflexion
 * 
 * @author Ricardo José Horta Morais
 */
public class DynamicClassUtils {
    
    
    

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap<>();
    private static final Map<Class<?>, Class<?>> WRAPPERS_TO_PRIMITIVES = new HashMap<>();

    static {

        PRIMITIVES_TO_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(char.class, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(double.class, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(float.class, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(long.class, Long.class);
        PRIMITIVES_TO_WRAPPERS.put(short.class, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(void.class, Void.class);

        WRAPPERS_TO_PRIMITIVES.put(Boolean.class, boolean.class);
        WRAPPERS_TO_PRIMITIVES.put(Byte.class, byte.class);
        WRAPPERS_TO_PRIMITIVES.put(Character.class, char.class);
        WRAPPERS_TO_PRIMITIVES.put(Double.class, double.class);
        WRAPPERS_TO_PRIMITIVES.put(Float.class, float.class);
        WRAPPERS_TO_PRIMITIVES.put(Integer.class, int.class);
        WRAPPERS_TO_PRIMITIVES.put(Long.class, long.class);
        WRAPPERS_TO_PRIMITIVES.put(Short.class, short.class);
        WRAPPERS_TO_PRIMITIVES.put(Void.class, void.class);

    }     
    
    
    // safe because both Long.class and long.class are of type Class<Long>
    @SuppressWarnings("unchecked")
    public static <T> Class<T> primitiveToWrapper(Class<T> c) {
      return c.isPrimitive() ? (Class<T>) PRIMITIVES_TO_WRAPPERS.get(c) : c;
    }
    
    // safe because both Long.class and long.class are of type Class<Long>
    @SuppressWarnings("unchecked")
    public static <T> Class<T> wrapperToPrimitive(Class<T> c) {
      return c.isPrimitive() ?   c: (Class<T>)WRAPPERS_TO_PRIMITIVES.get(c);
    }
    
    
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
    
    public static List<Class> getLoadedClasses(ClassLoader classLoader) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        
        Field classes;

        classes = ClassLoader.class.getDeclaredField("classes");
        classes.setAccessible(true);

        List<Class> classList = new ArrayList((List<Class>) classes.get(classLoader));

        classes.setAccessible(false);
        
        return classList;

    }
    
    


}
