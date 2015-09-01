/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

/**
 * Represents the type of an event
 * @author Ricardo José Horta Morais
 */
public final class EventType {

    private final String type;
    
    /**
     * Contructor
     * @param type type of the event 
     */
    public EventType(String type) {
        this.type = type;
    }

    /**
     * Get event type
     * @return event type
     */
    public String getType(){
        return type;
    }
    
}
