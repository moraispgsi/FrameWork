/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.SocketPane.UISocket;
import Statements.Generic.Input;

/**
 *
 * @author Morai
 */
public interface ParamInput {

    void connect(ParamOutput output);

    void disconnect();

    Input getInput();

    ParamOutput getOutputSource();

    UISocket getUISocket();

    Class<?> getVariableType();

    boolean isCastCompatible(ParamOutput paramOutput);

    void setOnConnect(ParamSocketHandler onConnect);

    void setOnDisconnect(ParamSocketHandler onDisconnect);
    
}
