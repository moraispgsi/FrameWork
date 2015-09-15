/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


public class UIStartMethod extends UISocketPane {

    public UIStartMethod(String classFileUrl,Method method) {
        super(method.getName() + " Start");
        
        
        OutputCallSocket callOutputSocket = new OutputCallSocket("Next");
        addOutputCallSocket(callOutputSocket);
        
        for(Parameter param : method.getParameters()){

            VariableOutputSocket socket = new VariableOutputSocket(param.getType(),param.getName()+":"+param.getType().getSimpleName());
            addOutputSocket(socket);
            
        }

    }

}
