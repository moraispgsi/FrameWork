/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;




/**
 * Represents the basic behaviour of an event
 * 
 * @author Ricardo José Horta Morais
 * @param <D> type of a event data
 */
public abstract class BaseEvent <D extends EventData> implements Event<D> {

    /**
     * Root type
     */
    public static final EventType root = new EventType("Root");
    
    private final EventType eventType;
    private final EventData data;
    
    /**
     * Constructor
     * @param eventType event type
     * @param data data associated with the event type
     */
    public BaseEvent(EventType eventType,D data) {
        this.eventType = eventType;
        this.data = data;
    }
    
    /**
     * Constructor
     * 
    */
    public BaseEvent() {
        this.eventType = null;
        this.data = null;
    } 
    
    
    @Override
    public EventType getEventType() {
        return eventType;
    }


    @Override
    public D getData(){
        return (D) data;
    }
    
    
}
