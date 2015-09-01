/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;



/**
 * Represents the request type
 * 
 * @author Ricardo José Horta Morais
 */
public final class RequestType {

    private final String type;
    
    /**
     * Constructor
     * @param type request type
     */
    public RequestType(String type) {
        this.type = type;
    }
    
    /**
     * Gets the request type
     * @return type of the request
     */
    public String getType(){
        return type;
    }
    
}
