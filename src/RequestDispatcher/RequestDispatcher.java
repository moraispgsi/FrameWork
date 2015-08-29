/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;


/**
 *
 * @author Ricardo José Horta Morais
 */
public interface RequestDispatcher {
    

    /**
     * Dispatches a request passing the request and assuming this is the dispatcher 
     * @param request event that occured
     */
    public void fireRequest(Request request);
            
    /**
     * Registers a request handler to handle a specific type of request
     * @param requestType 
     * @param handler 
     */
    public void registerHandler(RequestType requestType, RequestHandler handler);
    
    
    /**
     * Unregister a request handler
     * @param handler 
     */
    public void unregisterHandler(RequestHandler handler);
    
    
    
}
