package Multifunctional;

/**
 * Represents a functionality to be added to a multifunctionality
 * @author Ricado José Horta Morais
 */
public interface Functionality {
    
    /**
     * Get the multifunctional associated to this functionality
     * @return multifunctional that is associated to the functionality
     */
    public Multifunctional getMultifunctional();
    /**
     * If the functional is associated to a multifunctional
     * @return true if its associated with a multifunctional
     */
    public boolean isConnected();
    /**
     * Connects this functionality to a multifunctionality
     * @param multifunctional multifunctional to connect to
     */
    public void connect(Multifunctional multifunctional);
    /**
     * Disconects this functionality from its associated multifunctionality
     */
    public void disconnect();
    
}
