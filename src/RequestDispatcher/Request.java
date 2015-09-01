/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;




/**
 * Request interface
 * @author Ricardo José Horta Morais
 * @param <D> type of requestData
 */
public interface Request <D extends RequestData> {
    
    /**
     * Gets the request type
     * @return request type
     */
    public RequestType getRequestType();
    /**
     * Gets the data
     * @return request data
     */
    public RequestData getData();
    
}
