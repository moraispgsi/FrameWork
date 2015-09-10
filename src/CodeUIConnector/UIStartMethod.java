/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javafx.collections.ObservableSet;


public class UIStartMethod extends IOSocketPane implements IOSocketPluggable{
    
    private final IOSocketSet socketSet = new IOSocketSet();
    
    public UIStartMethod(String classFileUrl,Method method) {
        super(method.getName() + " Start");
        
        
        for(Parameter param : method.getParameters()){

            UIVariableOutputSocket socket = new UIVariableOutputSocket(param.getType(),addOutput(param.getName()+":"+param.getType().getSimpleName()));
            socketSet.getOutputSockets().add(socket);

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
