/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.Connectors;

import CodeUIConnector.SocketPane.UISocket;
import DynamicClassUtils.DynamicClassUtils;


public class ParamInput {
    
    private final Class<?> variableType;
    private final UISocket uiSocket;
    private ParamOutput outputSource;

    public ParamInput(Class<?> variableType, String name) {
        this.variableType = variableType;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.INPUT,5,name);
    }


    public Class<?> getVariableType() {
        return variableType;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    public ParamOutput getOutputSource() {
        return outputSource;
    }

    public void setOutputSource(ParamOutput output) {
        if(output == null)
            return;
        
        this.outputSource = output;
        
        uiSocket.showConnected(); 
        output.getUISocket().showConnected();
    }
    
    public boolean isCastCompatible(ParamOutput paramOutput){
        
        return !DynamicClassUtils
                .primitiveToWrapper(
                        getVariableType()
                )
                .isAssignableFrom(
                        DynamicClassUtils
                                .primitiveToWrapper(
                                        paramOutput.getVariableType()
                                )
                );
                
    }    
        
    
    

}
