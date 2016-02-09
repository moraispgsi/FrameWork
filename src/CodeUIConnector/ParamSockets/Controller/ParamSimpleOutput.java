/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.ParamSockets.UI.UIParamSocket;
import CodeUIConnector.SocketPane.UISocket;
import Statements.Generic.Output;


public class ParamSimpleOutput implements ParamOutput {

    private final Output output;
    private final UISocket uiSocket;


    public ParamSimpleOutput(Output output) {
        
        this.output = output;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.OUTPUT,5,output.getName()+" : "+output.getType().getSimpleName());

    }

    @Override
    public Output getOutput() {
        return output;
    }

    @Override
    public Class<?> getVariableType() {
        return output.getType();
    }

    @Override
    public String getName() {
        return output.getName();
    }

    @Override
    public UISocket getUISocket() {
        return uiSocket;
    }


    
    
}
