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
public interface RequestHandler {
    
    /**
     * Handles a request
     * @param origin Dispatcher that originated the event
     * @param event event that occured
     */
    public void handle(RequestDispatcher origin, Request event);
    
}
