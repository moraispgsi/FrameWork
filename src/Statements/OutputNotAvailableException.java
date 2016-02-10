
package Statements;

/**
 * Happens when a Output value is not available
 * @author Morai
 */
public class OutputNotAvailableException extends Exception{
    
    public OutputNotAvailableException(){
        super("Output value is not available at this point.");
    }
    
}
