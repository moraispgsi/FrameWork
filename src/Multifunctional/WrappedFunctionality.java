/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifunctional;

/**
 *
 * @author moraisPGSI
 */
public class WrappedFunctionality implements Functionality {
    
    private final Functionality wrapper;
    private Multifunctional multifunctional;

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
