/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

/**
 * Represents an event dispatcher wrapped class that avoids inheritance
 * by wrapping the object with another class through composition, implementing
 * the same interface and delegating all the methods.
 * 
 * @author Ricardo José Horta Morais
 */
public class WrappedEventDispatcher implements EventDispatcher {

    private final Map<EventType, Set<EventHandler>> handlers = new HashMap<>();
    
    /**
     * Fires an events that can be catch by a registered handle
     * @param dispatcher Wrapper dispatcher
     * @param event event
     */
    public void fireEvent(EventDispatcher dispatcher,Event event) {
        
        if (dispatcher == null || event == null) {
            throw new RuntimeException("Parameters cannot be null.");
        }
        
        EventType type = event.getEventType();
        
        /*
         * TODO: Implement the root Event handler to be a type o handle that can receive any type of event
         * 
         */
        
        if(handlers.containsKey(type))
            handlers.get(type).stream().forEach((EventHandler h) -> h.handle(dispatcher, event));
        
    }
    
    @Override
    public void fireEvent(Event event){
        fireEvent(this,event);
    }

    @Override
    public void registerHandler(EventType eventType, EventHandler handler) {

        if (eventType == null || handler == null) {
            throw new RuntimeException("Parameters cannot be null.");
        }

        unregisterHandler(handler);

        if (!handlers.containsKey(eventType)) {
            handlers.put(eventType, new HashSet<>());
        }

        handlers.get(eventType).add(handler);

    }

    @Override
    public void unregisterHandler(EventHandler handler) {
        handlers.values()
                .stream()
                .forEach(s -> s.remove(handler));
    }

}
