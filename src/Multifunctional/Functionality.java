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
public interface Functionality {
    
    public Multifunctional getMultifunctional();
    public boolean isConnected();
    public void connect(Multifunctional multifunctional);
    public void disconnect();
    
}
