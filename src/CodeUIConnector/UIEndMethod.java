/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import javafx.collections.ObservableSet;


public class UIEndMethod extends IOSocketPane implements IOSocketPluggable{
    
    private final IOSocketSet socketSet = new IOSocketSet();
    
    public UIEndMethod(String classFileUrl,Method method) {
        super(method.getName() + " End");

        Class<?> returnType = method.getReturnType();
        
        if(!returnType.equals(Void.TYPE)){
            
            
            UIVariableInputSocket socket = new UIVariableInputSocket(returnType,addInput(returnType.getSimpleName()));
            socketSet.getInputSockets().add(socket);
            
        }

    }
    
    @Override
    public ObservableSet<UIVariableInputSocket> getInputSockets() {
        return socketSet.getInputSockets();
    }

    @Override
    public ObservableSet<UIVariableOutputSocket> getOutputSockets() {
        return socketSet.getOutputSockets();
    }
    
}
