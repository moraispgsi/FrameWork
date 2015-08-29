/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;




/**
 *
 * @author Ricardo José Horta Morais
 * @param <D>
 */
public abstract class BaseEvent <D extends EventData> implements Event<D> {

    public static final EventType root = new EventType("Root");
    
    private final EventType eventType;
    private final EventData data;
    
    public BaseEvent(EventType eventType,D data) {
        this.eventType = eventType;
        this.data = data;
    }
    
    
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
