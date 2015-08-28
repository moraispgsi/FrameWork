/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

/**
 *
 * @author Ricardo José Horta Morais
 */
public interface EventHandler {
    
    /**
     * Handles an event
     * @param origin Dispatcher that originated the event
     * @param event event that occured
     */
    public void handle(EventDispatcher origin, Event event);
    
}
