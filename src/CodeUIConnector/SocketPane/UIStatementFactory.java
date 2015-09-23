/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketPane;

import CodeUIConnector.InvokeSockets.Controller.InvokeInput;
import CodeUIConnector.InvokeSockets.Controller.InvokeOutput;
import CodeUIConnector.ParamSockets.Controller.ParamInput;
import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import Statements.BranchStatement;
import Statements.FieldDeclareStatement;
import Statements.FieldInvokeStatement;
import Statements.MethodInvokeStatement;
import Statements.ParamStatement;
import Statements.ReturnStatement;
import Statements.RootStatement;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class UIStatementFactory {
    
    public static UIStatement createMethodInvoke(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName());
        
        MethodInvokeStatement statement = new MethodInvokeStatement();
        statement.setMethod(method);
        
        FieldDeclareStatement fieldDeclareStatement = new FieldDeclareStatement(statement.getReturnType(),"PREHOLDER");
        fieldDeclareStatement.setReturningStatement(statement);
        
        InvokeInput invokeInputSocket = new InvokeInput(fieldDeclareStatement);
        uiStatement.addInvokeInput(invokeInputSocket);
        
        InvokeOutput invokeOutputSocket = new InvokeOutput("Next");
        uiStatement.addInvokeOutput(invokeOutputSocket);
        
        invokeOutputSocket.setOnConnect((invokeInput,invokeOutput)->{
            
            fieldDeclareStatement.setNextStatement(invokeInput.getStatement());
            
        });
        
        invokeOutputSocket.setOnDisconnect((invokeInput,invokeOutput)->{
            
            fieldDeclareStatement.setNextStatement(null);
            
        });
        
        statement.setMethod(method);
        
        Parameter [] params = method.getParameters();
        
        for(int i=0;i<params.length;i++){

            ParamInput paramInput = new ParamInput(params[i].getType(),params[i].getName());
            uiStatement.addInputParam(paramInput);
            
            FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
            fieldInvokeStatement.setType(paramInput.getVariableType());
            statement.getArguments().add(fieldInvokeStatement);
            
            paramInput.setOnConnect((input,output)-> {
                
                fieldInvokeStatement.setFieldReference(output.getFieldReference());
                
                if(!fieldDeclareStatement.missingDependency())
                    System.out.println(fieldDeclareStatement.generateJavaCode());
                
            });
            
            paramInput.setOnDisconnect((input,output)-> {
                
                fieldInvokeStatement.setFieldReference(null);
                
            });
            
        }

        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            ParamOutput socket = new ParamOutput(returnType,"returnValue",fieldDeclareStatement);
            uiStatement.addOutputParam(socket);
        
        }
        
        return uiStatement;
        
    }
    
    public static UIStatement createEndMethod(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName()); 
        ReturnStatement statement = new ReturnStatement(method);
        
        Class<?> returnType = method.getReturnType();
        
        if(!returnType.equals(Void.TYPE)){
            
            InvokeInput invokeInputSocket = new InvokeInput(statement);
            uiStatement.addInvokeInput(invokeInputSocket);
        
            ParamInput paramInput = new ParamInput(returnType,"return");
            uiStatement.addInputParam(paramInput);
            
            FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
            fieldInvokeStatement.setType(paramInput.getVariableType());
            
            statement.setReturningStatement(fieldInvokeStatement);
            
            paramInput.setOnConnect((input,output)-> {
                
                //PLACE HOLDER
                fieldInvokeStatement.setFieldReference(output.getFieldReference());
                
                if(!statement.missingDependency())
                    System.out.println(statement.generateJavaCode());
                
            });
            
            paramInput.setOnDisconnect((input,output)-> {
                
                fieldInvokeStatement.setFieldReference(null);
                
            });
            
            
        }
        
        return uiStatement;
        
    }
    
    public static UIStatement createStartMethod(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName() + " Start"); 
        
        RootStatement statement = new RootStatement();
        
        
        InvokeOutput callOutputSocket = new InvokeOutput("Next");
        uiStatement.addInvokeOutput(callOutputSocket);

        callOutputSocket.setOnConnect((callInput, callOutput)->{
            
            statement.setNextStatement(callInput.getStatement());
            
            if(!statement.missingDependency())
                    System.out.println(statement.generateJavaCode());
        });
        callOutputSocket.setOnDisconnect((callInput, callOutput)->{
            
            statement.setNextStatement(null);
            
        });
        
        
        
        for(Parameter param : method.getParameters()){
            
            ParamStatement paramStatement = new ParamStatement(param.getType(),param.getName());
            
            ParamOutput socket = new ParamOutput(param.getType(),param.getName(),paramStatement);
            uiStatement.addOutputParam(socket);

        }
        
        return uiStatement;
        
    }
    
    public static UIStatement createCondition(){
        
        UIStatement socketPane = new UIStatement("Condition"); 
        
        BranchStatement statement = new BranchStatement();
        
        InvokeOutput trueInvokeOutput = new InvokeOutput("True");
        socketPane.addInvokeOutput(trueInvokeOutput);
        
        trueInvokeOutput.setOnConnect((callInput,callOutput)->{
            
            statement.setTrueNextStatement(callInput.getStatement());

        });
        trueInvokeOutput.setOnDisconnect((callInput,callOutput)->{
            statement.setTrueNextStatement(null);
        });
        
        
        InvokeOutput falseInvokeOutput = new InvokeOutput("False");
        socketPane.addInvokeOutput(falseInvokeOutput);
        
        falseInvokeOutput.setOnConnect((callInput,callOutput)->{
            
            statement.setFalseNextStatement(callInput.getStatement());

        });
        falseInvokeOutput.setOnDisconnect((callInput,callOutput)->{
            
            statement.setFalseNextStatement(null);

        });

        
        InvokeInput callInput = new InvokeInput(statement);
        socketPane.addInvokeInput(callInput);
        
        ParamInput conditionInput = new ParamInput(Boolean.class,"Condition");
        socketPane.addInputParam(conditionInput);
        
        FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
        fieldInvokeStatement.setType(conditionInput.getVariableType());
        statement.setConditionStatement(fieldInvokeStatement);

        conditionInput.setOnConnect((input,output)-> {

            fieldInvokeStatement.setFieldReference(output.getFieldReference());

            if(!statement.missingDependency())
                System.out.println(statement.generateJavaCode());

        });
        conditionInput.setOnDisconnect((input,output)-> {

            fieldInvokeStatement.setFieldReference(null);

        });
        
        
        return socketPane;
        
    }
}
