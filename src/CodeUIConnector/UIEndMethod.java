/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;



public class UIEndMethod extends IOSocketPane implements IOSocketPluggable{
    

    public UIEndMethod(String classFileUrl,Method method) {
        super(method.getName() + " End");

        Class<?> returnType = method.getReturnType();
        
        
        
        if(!returnType.equals(Void.TYPE)){
            
            UIInputCallSocket callInputSocket = new UIInputCallSocket();
            addInputCallSocket(callInputSocket);
        
            VariableInputSocket socket = new VariableInputSocket(returnType,returnType.getSimpleName());
            addInputSocket(socket);
            
        }

    }
    
  
}
