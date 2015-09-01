/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * Wrapped command center allow for the basic command center to be wrapped inside
 * another class with the same interface in order to avoid inheritance
 * 
 * @author Ricardo José Horta Morais
 */
public class WrappedCommandCenter implements CommandCenter {
    
    private final Map<String,Command> commands = new HashMap<>();
    
    @Override
    public Set<String> getCommands() {
        return commands.keySet();
    }

    @Override
    public Command getCommand(String name) {
        
        if(name == null)
            throw new RuntimeException("Parameters cannot be null");
        
        if(!commands.containsKey(name))
            throw new RuntimeException("Command does not exist.");
        
        return commands.get(name);
    }

    @Override
    public void registerCommand(String name, Command command) {
        if(name == null || command == null)
            throw new RuntimeException("Parameters cannot be null");
        
        
        if(commands.containsKey(name))
            throw new RuntimeException("Command already exists.");
        
        commands.put(name,command);
    }

    @Override
    public void unregisterCommand(String name) {
        if(name == null)
            throw new RuntimeException("Parameters cannot be null");

        commands.remove(name);
    }
    
}
