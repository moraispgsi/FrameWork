/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.Connectors;

import CodeUIConnector.SocketPane.UISocket;


public class ParamOutput {

    private final Class<?> variableType;
    private final UISocket uiSocket;

    public ParamOutput(Class<?> variableType, String name) {
        this.variableType = variableType;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.OUTPUT,5,name);
    }
    

    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
    
    
    
}
