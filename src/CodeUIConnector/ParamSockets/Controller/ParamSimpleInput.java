/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.ParamSockets.UI.UIParamSocket;
import CodeUIConnector.SocketPane.UISocket;
import DynamicClassUtils.DynamicClassUtils;
import Statements.Generic.Input;

/**
 * Represents a param input
 * @author Ricardo José Horta Morais
 */
public class ParamSimpleInput implements ParamInput {

    private final Input input;
    private final UISocket uiSocket;

    private ParamOutput outputSource;

    private ParamSocketHandler onConnect;
    private ParamSocketHandler onDisconnect;

    public ParamSimpleInput(Input input) {
        
        this.input = input;        
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.INPUT, 5, input.getName()+" : "+input.getType().getSimpleName());

    }

    @Override
    public void setOnConnect(ParamSocketHandler onConnect) {

        this.onConnect = onConnect;

    }

    @Override
    public void setOnDisconnect(ParamSocketHandler onDisconnect) {
        this.onDisconnect = onDisconnect;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public Class<?> getVariableType() {
        return input.getType();
    }

    @Override
    public UISocket getUISocket() {
        return uiSocket;
    }

    @Override
    public ParamOutput getOutputSource() {
        return outputSource;
    }

    @Override
    public void connect(ParamOutput output) {

        if (output == null) {
            return;
        }
        
        disconnect();

        this.outputSource = output;

        uiSocket.showConnected();
        output.getUISocket().showConnected();

        if (onConnect != null) {
            onConnect.handle(this, output);
        }

    }
    
    @Override
    public void disconnect(){
        
        if(outputSource == null)
            return;
            
        if(onDisconnect != null)
            onDisconnect.handle(this, outputSource);
         
        this.outputSource = null;
        
    }
    

    @Override
    public boolean isCastCompatible(ParamOutput paramOutput) {

        return DynamicClassUtils
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
