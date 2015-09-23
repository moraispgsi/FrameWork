/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.ParamSockets.UI.UIParamSocket;
import CodeUIConnector.SocketPane.UISocket;
import Statements.FieldReference;


public class ParamOutput {

    private final Class<?> variableType;
    private final String name;
    private final UISocket uiSocket;
    private final FieldReference fieldReference;

    public ParamOutput(Class<?> variableType, String name,FieldReference fieldReference) {
        this.variableType = variableType;
        this.name = name;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.OUTPUT,5,name+" : "+variableType.getSimpleName());
        this.fieldReference = fieldReference;
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

    public FieldReference getFieldReference() {
        return fieldReference;
    }
    
    
}
