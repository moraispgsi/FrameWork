/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.CallSockets;

import CodeUIConnector.SocketPane.UISocket;



public class CallOutput {
    
    private final UISocket uiSocket;
    private CallInput connectSocket;
    
    
    
    public CallOutput(String name) {

        this.uiSocket = new UICallSocket(UICallSocket.Type.OUTPUT,5,name);
        
        
    }

    public void setConnect(CallInput connectSocket) {
        this.connectSocket = connectSocket;
    }

    public CallInput getConnectSocket() {
        return connectSocket;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    
}