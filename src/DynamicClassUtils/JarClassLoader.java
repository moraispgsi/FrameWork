package DynamicClassUtils;


import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;


public class JarClassLoader extends URLClassLoader {
    
    private URL url;
    private Set<Class> classes;

    public JarClassLoader(URL url,ClassLoader cl) throws MalformedURLException {
        super(new URL[] { url },cl);
        this.url = url;
        classes = new HashSet();
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        
        Class<?> classValue = super.loadClass(name);
        classes.add(classValue);
        return classValue;
        
    }

    public Set<Class> getClasses() {
        return new HashSet(classes);
    }

}