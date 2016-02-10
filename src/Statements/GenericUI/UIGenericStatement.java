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
public class UIGenericStatement extends UIGenericIOStatement {

    public UIGenericStatement(Statement statement) {

        super(statement);
        
        InvokeInput calculateInvokeInput = new InvokeInput(null, "Run");
        this.addInvokeInput(calculateInvokeInput);

        InvokeOutput nextInvokeOutput = new InvokeOutput("Next");
        this.addInvokeOutput(nextInvokeOutput);
        

    }

}
