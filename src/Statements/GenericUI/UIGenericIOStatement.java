/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.GenericUI;

import CodeUIConnector.InvokeSockets.Controller.InvokeInput;
import CodeUIConnector.InvokeSockets.Controller.InvokeOutput;
import CodeUIConnector.ParamSockets.Controller.ParamInput;
import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import CodeUIConnector.ParamSockets.Controller.ParamSimpleInput;
import CodeUIConnector.ParamSockets.Controller.ParamSimpleOutput;
import CodeUIConnector.SocketPane.UIStatement;
import Statements.EndOutput;
import Statements.Input;
import Statements.Output;
import Statements.OutputNotAvailableException;
import Statements.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class UIGenericIOStatement extends UIStatement {

    public UIGenericIOStatement(Statement statement) {

        super(statement.getName());

        for (Input input : statement.getInputs()) {

            ParamInput paramInput = new ParamSimpleInput(input);
            this.addInputParam(paramInput);
            paramInput.setOnConnect((i,o)->{
                i.getInput().setOutput(o.getOutput());
            });

        }
        
        for (Output output : statement.getOutputs()) {

            ParamOutput paramOutput = new ParamSimpleOutput(output);
            this.addOutputParam(paramOutput);
            paramOutput.getUISocket().setOnMouseClicked((e)->{
                
                
                try {
                    if(output.getType() == EndOutput.class)
                        output.getExecutionInstance().getValue();
                    else
                        System.out.println(output.getExecutionInstance().getValue());
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(UIGenericIOStatement.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            
            });
        }

    }

}
