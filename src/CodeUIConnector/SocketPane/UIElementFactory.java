/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketPane;

import CodeUIConnector.Connectors.CallInput;
import CodeUIConnector.Connectors.CallOutput;
import CodeUIConnector.Connectors.ParamInput;
import CodeUIConnector.Connectors.ParamOutput;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author Morais
 */
public class UIElementFactory {
    
    public static UIElement createMethodCall(Method method){
        
        UIElement socketPane = new UIElement(method.getName());

        for(Parameter param : method.getParameters()){

            ParamInput socket = new ParamInput(param.getType(),param.getName()+":"+param.getType().getSimpleName());
            socketPane.addInputParam(socket);
 
        }

        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            ParamOutput socket = new ParamOutput(returnType,returnType.getSimpleName());
            socketPane.addOutputParam(socket);
        
        }
        
        return socketPane;
        
    }
    
    public static UIElement createEndMethod(Method method){
        
        UIElement socketPane = new UIElement(method.getName()); 

        Class<?> returnType = method.getReturnType();
        
        if(!returnType.equals(Void.TYPE)){
            
            CallInput callInputSocket = new CallInput();
            socketPane.addCallInput(callInputSocket);
        
            ParamInput socket = new ParamInput(returnType,returnType.getSimpleName());
            socketPane.addInputParam(socket);
            
        }
        
        return socketPane;
        
    }
    
    public static UIElement createStartMethod(Method method){
        
        UIElement socketPane = new UIElement(method.getName() + " Start"); 
    
        CallOutput callOutputSocket = new CallOutput("Next");
        socketPane.addCallOutput(callOutputSocket);
        
        for(Parameter param : method.getParameters()){

            ParamOutput socket = new ParamOutput(param.getType(),param.getName()+":"+param.getType().getSimpleName());
            socketPane.addOutputParam(socket);
            
        }
        
     return socketPane;
        
    }
    
    public static UIElement createCondition(){
        
        UIElement socketPane = new UIElement("Condition"); 
        
        CallOutput trueCallSocket = new CallOutput("True");
        socketPane.addCallOutput(trueCallSocket);
        
        CallOutput falseCallSocket = new CallOutput("False");
        socketPane.addCallOutput(falseCallSocket);
        
        CallInput callInputSocket = new CallInput();
        socketPane.addCallInput(callInputSocket);
        
        ParamInput socket = new ParamInput(Boolean.class,"Condition");
        socketPane.addInputParam(socket);
        
        return socketPane;
        
    }
}
