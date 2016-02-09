/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.SocketPane.UISocket;
import Statements.Generic.Output;

/**
 *
 * @author Morai
 */
public interface ParamOutput {

    String getName();

    Output getOutput();

    UISocket getUISocket();

    Class<?> getVariableType();
    
}
