/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.InvokeSockets.Controller;

import CodeUIConnector.InvokeSockets.UI.UIInvokeSocket;
import CodeUIConnector.SocketPane.UISocket;
import Statements.Generic.Statement;


/**
 * Represents a logical call input socket. 
 * @author Ricardo José Horta Morais
 */
public class InvokeInput {
    
    private final UISocket uiSocket;
    private final Statement statement;
            
    
    /**
     * Constructor
     * Builds the UISocket
     * @param statement statement
     */
    public InvokeInput(Statement statement) {
        this.uiSocket = new UIInvokeSocket(UIInvokeSocket.Type.INPUT,5,"Executar");
        this.statement = statement;
    }
    
    /**
     * Constructor
     * Builds the UISocket
     * @param statement statement
     * @param name name of the invoke
     */
    public InvokeInput(Statement statement,String name) {
        this.uiSocket = new UIInvokeSocket(UIInvokeSocket.Type.INPUT,5,name);
        this.statement = statement;
    }
    /**
     * Getter for the UISocket
     * @return UISocket UI representation of the CallInput
     */
    public UISocket getUISocket() {
        return uiSocket;
    }

    public Statement getStatement() {
        return statement;
    }
    
    
    
}
