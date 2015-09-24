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
import javafx.scene.input.MouseButton;

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
            
            final int index = i;
            
            paramInput.setOnConnect((input,output)->{

                FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
                fieldInvokeStatement.setType(paramInput.getVariableType());
                fieldInvokeStatement.setFieldReference(output.getFieldReference());
                
                statement.setArgument(index, fieldInvokeStatement);
                
                
            });
            paramInput.setOnDisconnect((input,output)->{

                statement.setArgument(index, null);

            });
            
        }

        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            ParamOutput paramOutput = new ParamOutput(returnType,"returnValue",fieldDeclareStatement);
            uiStatement.addOutputParam(paramOutput);

        }
        
        uiStatement.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.MIDDLE){
                if(!fieldDeclareStatement.missingDependency())
                    System.out.println(fieldDeclareStatement.generateJavaCode());
                else
                    System.out.println("Missing dependency.");
            }
            
        });
        
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

            
            paramInput.setOnConnect((input,output)->{
            
                FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
                fieldInvokeStatement.setType(paramInput.getVariableType());
                fieldInvokeStatement.setFieldReference(output.getFieldReference());
                statement.setReturningStatement(fieldInvokeStatement);
            });
            paramInput.setOnDisconnect((input,output)->{

                statement.setReturningStatement(null);

            });
            
            
        }
        
        uiStatement.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.MIDDLE)
                if(!statement.missingDependency())
                    System.out.println(statement.generateJavaCode());
            
        });
        
        return uiStatement;
        
    }
    
    public static UIStatement createStartMethod(Method method){
        
        UIStatement uiStatement = new UIStatement(method.getName() + " Start"); 
        
        RootStatement statement = new RootStatement();
        
        
        InvokeOutput callOutputSocket = new InvokeOutput("Next");
        uiStatement.addInvokeOutput(callOutputSocket);

        callOutputSocket.setOnConnect((callInput, callOutput)->{
            
            statement.setNextStatement(callInput.getStatement());
            
        });
        callOutputSocket.setOnDisconnect((callInput, callOutput)->{
            
            statement.setNextStatement(null);
            
        });
        
        
        
        for(Parameter param : method.getParameters()){
            
            ParamStatement paramStatement = new ParamStatement(param.getType(),param.getName());
            
            ParamOutput socket = new ParamOutput(param.getType(),param.getName(),paramStatement);
            uiStatement.addOutputParam(socket);

        }
        
        uiStatement.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.MIDDLE){
                if(!statement.missingDependency())
                    System.out.println(statement.generateJavaCode());
                else
                    System.out.println("Missing dependency.");
            }
            
        });
        
        return uiStatement;
        
    }
    
    public static UIStatement createCondition(){
        
        UIStatement uiStatement = new UIStatement("Condition"); 
        
        BranchStatement statement = new BranchStatement();
        
        InvokeOutput trueInvokeOutput = new InvokeOutput("True");
        uiStatement.addInvokeOutput(trueInvokeOutput);
        
        trueInvokeOutput.setOnConnect((callInput,callOutput)->{
            
            statement.setTrueNextStatement(callInput.getStatement());

        });
        trueInvokeOutput.setOnDisconnect((callInput,callOutput)->{
            statement.setTrueNextStatement(null);
        });
        
        
        InvokeOutput falseInvokeOutput = new InvokeOutput("False");
        uiStatement.addInvokeOutput(falseInvokeOutput);
        
        falseInvokeOutput.setOnConnect((callInput,callOutput)->{
            
            statement.setFalseNextStatement(callInput.getStatement());

        });
        falseInvokeOutput.setOnDisconnect((callInput,callOutput)->{
            
            statement.setFalseNextStatement(null);

        });

        
        InvokeInput callInput = new InvokeInput(statement);
        uiStatement.addInvokeInput(callInput);
        
        ParamInput conditionInput = new ParamInput(Boolean.class,"Condition");
        uiStatement.addInputParam(conditionInput);
        
        conditionInput.setOnConnect((input,output)->{
            
            FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
            fieldInvokeStatement.setType(conditionInput.getVariableType());
            fieldInvokeStatement.setFieldReference(output.getFieldReference());
            statement.setConditionStatement(fieldInvokeStatement);
        });
        conditionInput.setOnDisconnect((input,output)->{
        
            statement.setConditionStatement(null);

        });

       
        
        uiStatement.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.MIDDLE){
                if(!statement.missingDependency())
                    System.out.println(statement.generateJavaCode());
                else
                    System.out.println("Missing dependency.");
            }
            
        });
        
        
        
        return uiStatement;
        
    }
    
    public static UIStatement createFieldDeclaration(Class<?> type,String name){
        
        UIStatement uiStatement = new UIStatement("Declare " + name + " : " + type.getSimpleName()); 
        
        

        FieldDeclareStatement fieldDeclareStatement = new FieldDeclareStatement(type,name);
        //fieldDeclareStatement.setReturningStatement();
        InvokeInput invokeInputSocket = new InvokeInput(fieldDeclareStatement);
        uiStatement.addInvokeInput(invokeInputSocket);
        
        InvokeOutput invokeOutputSocket = new InvokeOutput("Next");
        uiStatement.addInvokeOutput(invokeOutputSocket);
        
        invokeOutputSocket.setOnConnect((input,output) -> {
            
            fieldDeclareStatement.setNextStatement(input.getStatement());
            
        });
        
        invokeOutputSocket.setOnDisconnect((input,output) -> {
            
            fieldDeclareStatement.setNextStatement(null);
            
        });
        
        ParamInput paramInput = new ParamInput(type,name);
        uiStatement.addInputParam(paramInput);

        paramInput.setOnConnect((input,output)->{
            
            FieldInvokeStatement fieldInvokeStatement = new FieldInvokeStatement();
            fieldInvokeStatement.setType(paramInput.getVariableType());
            fieldInvokeStatement.setFieldReference(output.getFieldReference());
            fieldDeclareStatement.setReturningStatement(fieldInvokeStatement);
        });
        paramInput.setOnDisconnect((input,output)->{
        
            fieldDeclareStatement.setReturningStatement(null);

        });

        ParamOutput socket = new ParamOutput(type,"name",fieldDeclareStatement);
        uiStatement.addOutputParam(socket);
        
        uiStatement.setOnMouseClicked(e -> {
            
            if(e.getButton() == MouseButton.MIDDLE){
                if(!fieldDeclareStatement.missingDependency())
                    System.out.println(fieldDeclareStatement.generateJavaCode());
                else
                    System.out.println("Missing dependency.");
            }
                
        });
        

        return uiStatement;
    }
    
}
