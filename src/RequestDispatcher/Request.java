/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;




/**
 *
 * @author Ricardo José Horta Morais
 * @param <D>
 */
public interface Request <D extends RequestData> {
    
    public RequestType getRequestType();
    public RequestData getData();
    
}
