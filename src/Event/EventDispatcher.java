/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

/**
 * Represents an event dispatcher that can propagate events by registering handlers
 * and firing events
 * @author Ricardo José Horta Morais
 */
public interface EventDispatcher {
    

    /**
     * Dispatches a event passing the event and assuming this is the dispatcher 
     * @param event event that occured
     */
    public void fireEvent(Event event);
            
    /**
     * Registers an event handler to handle a specific type of event
     * @param eventType type of the event
     * @param handler handler to register associated to the event type
     */
    public void registerHandler(EventType eventType, EventHandler handler);
    
    /**
     * Unregister an event handler
     * @param handler handler to remove
     */
    public void unregisterHandler(EventHandler handler);
    
    
    
}
