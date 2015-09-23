/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.InvokeSockets.Controller;

import CodeUIConnector.CallSockets.UI.UICallSocket;
import CodeUIConnector.SocketPane.UISocket;

/**
 * Represents a logical call output socket.
 *
 * @author Ricardo José Horta Morais
 */
public class InvokeOutput {

    private final UISocket uiSocket;
    private InvokeInput connectSocket;
    private InvokeSocketHandler onConnect;
    private InvokeSocketHandler onDisconnect;

    /**
     * Constructor
     *
     * @param name name that will be displayed in its UI representation
     */
    public InvokeOutput(String name) {

        this.uiSocket = new UICallSocket(UICallSocket.Type.OUTPUT, 5, name);

    }

    /**
     * Connects to an CallInput.
     *
     * @param callInput call input that will be executed after this call output
     */
    public void connect(InvokeInput callInput) {

        disconnect();

        this.connectSocket = callInput;

        if (onConnect != null) {
            onConnect.handle(callInput, this);
        }
    }

    /**
     * Disconnects from a call input if any is connected
     */
    public void disconnect() {

        if (connectSocket == null) {
            return;
        }

        if (onDisconnect != null) {
            onDisconnect.handle(connectSocket, this);
        }

        this.connectSocket = null;

    }

    /**
     * Getter for the connected call input
     *
     * @return the call input
     */
    public InvokeInput getConnectedCallInput() {
        return connectSocket;
    }

    /**
     * Getter for the UI representation of the CallOutput
     *
     * @return the UI representation of the CallOuput
     */
    public UISocket getUISocket() {
        return uiSocket;
    }

    /**
     * Setter for the handler that will execute after a connection
     *
     * @param onConnect on connect handler
     */
    public void setOnConnect(InvokeSocketHandler onConnect) {
        this.onConnect = onConnect;
    }

    /**
     * Setter for the handler that will execute after a disconnection
     *
     * @param onDisconnect on disconnect handler_
     */
    public void setOnDisconnect(InvokeSocketHandler onDisconnect) {
        this.onDisconnect = onDisconnect;
    }

}
