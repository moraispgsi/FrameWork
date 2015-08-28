/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author moraisPGSI
 */
public class Entity implements Multifunctional  {
    
    private static final AtomicLong idGenerator = new AtomicLong();

    private final long id;
    
    public WrappedMultifunctional multifunctional = new WrappedMultifunctional();

    
    public Entity() {

        id = idGenerator.incrementAndGet();

    }

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
