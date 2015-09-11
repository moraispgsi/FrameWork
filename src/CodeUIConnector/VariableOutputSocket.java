/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class VariableOutputSocket implements Socket {

    private final Class<?> variableType;
    private final UISocket uiSocket;

    public VariableOutputSocket(Class<?> variableType, String name) {
        this.variableType = variableType;
        this.uiSocket = new UIVariableSocket(UIVariableSocket.Type.OUTPUT,5,name);
    }
    
    @Override
    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
    
    
    
}
