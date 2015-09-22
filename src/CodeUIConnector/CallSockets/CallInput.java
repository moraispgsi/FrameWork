/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.CallSockets;

import CodeUIConnector.SocketPane.UISocket;




public class CallInput {
    
    private final UISocket uiSocket;

    public CallInput() {
        this.uiSocket = new UICallSocket(UICallSocket.Type.INPUT,5,"Executar");
        
        
        
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
}
