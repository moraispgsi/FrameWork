/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.InvokeSockets.Controller;

/**
 *  Represents a handler for a call socket connection
 *  @author Ricardo José Horta Morais
 */
public interface InvokeSocketHandler {
    /**
     * Generic Handle
     * @param callInput call input 
     * @param callOutput call output 
     */
    public void handle(InvokeInput callInput, InvokeOutput callOutput);
    
}
