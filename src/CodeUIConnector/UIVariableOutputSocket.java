/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UIVariableOutputSocket implements Socket {

    private final Class<?> variableType;
    private final UISocket uiSocket;

    public UIVariableOutputSocket(Class<?> variableType, UISocket uiSocket) {
        this.variableType = variableType;
        this.uiSocket = uiSocket;
    }
    
    @Override
    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
    
    
    
}
