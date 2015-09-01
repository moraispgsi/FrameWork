/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifunctional;

/**
 * Represents a Wrapped Functionality. It supplies all the basic connection code
 * and its used to avoid composition by implementing the Functionality interface
 * and delegating all the methods towards the wrapped object.
 * 
 * @author Ricado José Horta Morais
 */
public class WrappedFunctionality implements Functionality {
    
    private final Functionality wrapper;
    private Multifunctional multifunctional;

    /**
     * Constructor
     * @param wrapper Functionality that will wrap this object 
     */
    public WrappedFunctionality(Functionality wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public Multifunctional getMultifunctional() {
        return multifunctional;
    }

    @Override
    public boolean isConnected() {
        return multifunctional!=null;
    }

    @Override
    public void connect(Multifunctional multifunctional) {
        
        this.multifunctional = multifunctional;
        if(!multifunctional.hasFunctionality(wrapper.getClass())){
            this.multifunctional.addFunctionality(wrapper);
        }

    }
    
    @Override
    public void disconnect() {
        
        if(multifunctional.hasFunctionality(wrapper.getClass()))
            multifunctional.removeFunctionality(wrapper.getClass());
        multifunctional = null;

    }

}
