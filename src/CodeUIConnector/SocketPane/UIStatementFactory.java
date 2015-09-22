/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketPane;

import CodeUIConnector.CallSockets.CallInput;
import CodeUIConnector.CallSockets.CallOutput;
import CodeUIConnector.ParamSockets.ParamInput;
import CodeUIConnector.ParamSockets.ParamOutput;
import Statements.FieldStatement;
import Statements.MethodCallStatement;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author Morais
 */
public class UIStatementFactory {
    
    public static UIStatement createMethodCall(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName());
        
        MethodCallStatement statement = new MethodCallStatement();
        
        CallInput callInputSocket = new CallInput();
        uiStatement.addCallInput(callInputSocket);
        
        CallOutput callOutputSocket = new CallOutput("Next");
        uiStatement.addCallOutput(callOutputSocket);
        
        statement.setMethod(method);
        
        Parameter [] params = method.getParameters();
        
        for(int i=0;i<params.length;i++){

            ParamInput paramInput = new ParamInput(params[i].getType(),params[i].getName());
            uiStatement.addInputParam(paramInput);
            FieldStatement fieldStatement = new FieldStatement();
            fieldStatement.setType(paramInput.getVariableType());
            statement.getArguments().add(fieldStatement);
            
            paramInput.setOnConnect((input,output)-> {
                
                fieldStatement.setName(output.getName());
                System.out.println("Missing dependency: "+statement.missingDependency());

            });
        }

        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            ParamOutput socket = new ParamOutput(returnType,returnType.getSimpleName());
            uiStatement.addOutputParam(socket);
        
        }
        
        return uiStatement;
        
    }
    
    public static UIStatement createEndMethod(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName()); 

        Class<?> returnType = method.getReturnType();
        
        if(!returnType.equals(Void.TYPE)){
            
            CallInput callInputSocket = new CallInput();
            uiStatement.addCallInput(callInputSocket);
        
            ParamInput socket = new ParamInput(returnType,"return");
            uiStatement.addInputParam(socket);
            
        }
        
        return uiStatement;
        
    }
    
    public static UIStatement createStartMethod(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName() + " Start"); 
    
        CallOutput callOutputSocket = new CallOutput("Next");
        uiStatement.addCallOutput(callOutputSocket);
        
        for(Parameter param : method.getParameters()){

            ParamOutput socket = new ParamOutput(param.getType(),param.getName());
            uiStatement.addOutputParam(socket);
            
        }
        
     return uiStatement;
        
    }
    
    public static UIStatement createCondition(){
        
        UIStatement socketPane = new UIStatement("Condition"); 
        
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
