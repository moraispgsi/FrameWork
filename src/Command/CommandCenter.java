/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;


import java.util.Set;

/**
 *
 * Represents a command center that allow to register commands 
 * 
 * @author Ricardo José Horta Morais
 */
public interface CommandCenter {
    
    /**
     * Gets a set of all commands 
     * @return set of all commands
     */
    public Set<String> getCommands();
    /**
     * Get command by string name
     * @param name the name of the command  
     * @return the command with the name
     */
    public Command getCommand(String name);
    /**
     * Registers a commands by its name
     * @param name name of the command to associate it
     * @param command command
     */
    public void registerCommand(String name, Command command);
    /**
     * Unregisters a command by its name
     * @param name name of the command
     */
    public void unregisterCommand(String name);
    
}
