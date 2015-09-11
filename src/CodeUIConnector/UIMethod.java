/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class UIMethod extends IOSocketPane{

    public Method method;
    public String classFileUrl;

    public UIMethod(String classFileUrl,Method method) {
        super(method.getName());
        
        this.method = method;

        this.classFileUrl = classFileUrl;

        for(Parameter param : method.getParameters()){

            VariableInputSocket socket = new VariableInputSocket(param.getType(),param.getName()+":"+param.getType().getSimpleName());
            addInputSocket(socket);
 
        }
     
        
        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            VariableOutputSocket socket = new VariableOutputSocket(returnType,returnType.getSimpleName());
            addOutputSocket(socket);

        }

    }

}
