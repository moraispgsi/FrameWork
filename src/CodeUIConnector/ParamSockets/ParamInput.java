/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets;

import CodeUIConnector.SocketPane.UISocket;
import DynamicClassUtils.DynamicClassUtils;

public class ParamInput {

    private final Class<?> variableType;
    private final UISocket uiSocket;

    private ParamOutput outputSource;

    private ParamSocketHandler onConnect;

    public ParamInput(Class<?> variableType, String name) {

        this.variableType = variableType;
        this.uiSocket = new UIParamSocket(UIParamSocket.Type.INPUT, 5, name+" : "+variableType.getSimpleName());

    }

    public void setOnConnect(ParamSocketHandler onConnect) {

        this.onConnect = onConnect;

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

        if (output == null) {
            return;
        }

        this.outputSource = output;

        uiSocket.showConnected();
        output.getUISocket().showConnected();

        if (onConnect != null) {
            onConnect.handle(this, output);
        }

    }

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
