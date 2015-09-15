/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class VariableInputSocket implements Socket {
    
    private final Class<?> variableType;
    private final UISocket uiSocket;
    private VariableOutputSocket outputSource;

    public VariableInputSocket(Class<?> variableType, String name) {
        this.variableType = variableType;
        this.uiSocket = new UIVariableSocket(UIVariableSocket.Type.INPUT,5,name);
    }

    @Override
    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    public VariableOutputSocket getOutputSource() {
        return outputSource;
    }

    public void setOutputSource(VariableOutputSocket output) {
        if(output == null)
            return;
        
        this.outputSource = output;
        
        uiSocket.showConnected(); 
        output.getUISocket().showConnected();
    }
    
    
    

}
