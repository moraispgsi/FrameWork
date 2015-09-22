/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets;

import CodeUIConnector.SocketPane.UISocket;


public class ParamOutput {

    private final Class<?> variableType;
    private final String name;
    private final UISocket uiSocket;

    public ParamOutput(Class<?> variableType, String name) {
        this.variableType = variableType;
        this.name = name;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.OUTPUT,5,name+" : "+variableType.getSimpleName());
    }
    
    public Class<?> getVariableType() {
        return variableType;
    }

    public String getName() {
        return name;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
}
