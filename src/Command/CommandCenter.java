/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;


import java.util.Set;

/**
 *
 * @author Ricardo José Horta Morais
 */
public interface CommandCenter {
    
    public Set<String> getCommands();
    
    public Command getCommand(String name);
    
    public void registerCommand(String name, Command command);
    
    public void unregisterCommand(String name);
    
}
