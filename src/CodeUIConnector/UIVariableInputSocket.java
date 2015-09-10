/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UIVariableInputSocket implements Socket {
    
    private final Class<?> variableType;
    private final UISocket uiSocket;
    private UIVariableOutputSocket output;

    public UIVariableInputSocket(Class<?> variableType, UISocket uiSocket) {
        this.variableType = variableType;
        this.uiSocket = uiSocket;
    }
    
    public void setInput(UIVariableOutputSocket output){
        
        this.output = output;
        
    }

    @Override
    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    public UIVariableOutputSocket getOutput() {
        return output;
    }

    public void setOutput(UIVariableOutputSocket output) {
        this.output = output;
    }
    
    
    

}
