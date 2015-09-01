
package Entity;

import Command.Command;
import Event.Event;
import Event.EventHandler;
import Event.EventType;
import Multifunctional.Functionality;
import Multifunctional.Multifunctional;
import Multifunctional.WrappedMultifunctional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * An entity stands for a  multifunctional interface. It allow it to be composed of many
 * Functionalities at runtime in order to mean anything. 
 * 
 * @author Ricado José Horta Morais
 */
public class Entity implements Multifunctional  {
    
    private static final AtomicLong idGenerator = new AtomicLong();

    private final long id;

    private final WrappedMultifunctional multifunctional = new WrappedMultifunctional();
    
    /**
     * Contructor
    */
    public Entity() {

        id = idGenerator.incrementAndGet();

    }
    
    /**
     * Gets an unique Id assigned to this Entity
     * @return id
     */
    public long getId() {
        return id;
    }
    
    @Override
    public void addFunctionality(Functionality functionality) {
        multifunctional.addFunctionality(functionality);
    }

    @Override
    public void removeFunctionality(Class<? extends Functionality>  functionality) {
        multifunctional.removeFunctionality(functionality);
    }

    @Override
    public boolean hasFunctionality(Class<? extends Functionality>  functionality) {
        return multifunctional.hasFunctionality(functionality);
    }

    @Override
    public <T extends Functionality> T as(Class<T> functionality) {
        return multifunctional.as(functionality);
    }

    
   @Override
    public void fireEvent(Event event) {
        multifunctional.fireEvent(event);
    }

    @Override
    public void registerHandler(EventType eventType, EventHandler handler) {
        multifunctional.registerHandler(eventType, handler);
    }

    @Override
    public void unregisterHandler(EventHandler handler) {
        multifunctional.unregisterHandler(handler);
    }

    @Override
    public Set<String> getCommands() {
        return multifunctional.getCommands();
    }

    @Override
    public Command getCommand(String name) {
        return multifunctional.getCommand(name);
    }

    @Override
    public void registerCommand(String name, Command command) {
        multifunctional.registerCommand(name, command);
    }

    @Override
    public void unregisterCommand(String name) {
        multifunctional.unregisterCommand(name);
    }

    
    
}
