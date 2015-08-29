/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifunctional;

import Command.Command;
import Command.WrappedCommandCenter;
import Event.Event;
import Event.EventHandler;
import Event.EventType;
import Event.WrappedEventDispatcher;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author moraisPGSI
 */
public class WrappedMultifunctional implements Multifunctional {
    
    private final Set<Functionality> functionalities = new HashSet<>();
    private final WrappedCommandCenter commandCenter = new WrappedCommandCenter();
    private final WrappedEventDispatcher eventDispatcher = new WrappedEventDispatcher();
    
    @Override
    public void addFunctionality(Functionality functionality) {
        
        for(Functionality element:functionalities){
            if( element.getClass().equals(functionality.getClass()) )
                throw new RuntimeException("Functionality already attained");
        }
        
        if(functionality.isConnected() && functionality.getMultifunctional() != this)
        {
            
            functionality.disconnect();
            functionality.connect(this);
            
        }else if(!functionality.isConnected()){
            
            functionality.connect(this);
            
        }

        functionalities.add(functionality);
    }

    
    @Override
    public void removeFunctionality(Class<? extends Functionality> functionality) {

        boolean foundFunctionality = false;
        for(Functionality element:functionalities){
            
            if(element.getClass().equals(functionality)){
                foundFunctionality = true;
                element.disconnect();
                functionalities.remove(element);
            }
        }
        
        if(!foundFunctionality)
            throw new RuntimeException("No such functionality.");
            
 
    }

    @Override
    public boolean hasFunctionality(Class<? extends Functionality> functionality) {
        
        for(Functionality element:functionalities){
            if( element.getClass().equals(functionality) )
                return true;
        }
        return false;
    }

    @Override
    public <T extends Functionality> T as(Class<T> functionality) {
        
        for(Functionality functionalityIndexed:functionalities){
            if( functionalityIndexed.getClass().equals(functionality) )
                return (T)functionalityIndexed;
        }
        
        throw new RuntimeException("No such functionality " + functionality.getSimpleName());
    }


    @Override
    public void fireEvent(Event event) {
        eventDispatcher.fireEvent(eventDispatcher, event);
    }

    @Override
    public void registerHandler(EventType eventType, EventHandler handler) {
        eventDispatcher.registerHandler(eventType, handler);
    }

    @Override
    public void unregisterHandler(EventHandler handler) {
        eventDispatcher.unregisterHandler(handler);
    }

    @Override
    public Set<String> getCommands() {
        return commandCenter.getCommands();
    }

    @Override
    public Command getCommand(String name) {
        return commandCenter.getCommand(name);
    }

    @Override
    public void registerCommand(String name, Command command) {
        commandCenter.registerCommand(name, command);
    }

    @Override
    public void unregisterCommand(String name) {
        commandCenter.unregisterCommand(name);
    }
    
    
}
