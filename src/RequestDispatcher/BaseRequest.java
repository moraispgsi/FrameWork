/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;




/**
 * Represents a basic request
 * @author Ricardo José Horta Morais
 * @param <D> type of request data
 */
public abstract class BaseRequest <D extends RequestData> implements Request<D> {

    private final RequestType requestType;
    private final RequestData data;
    
    /**
     * Contructor
     * @param requestType type of request
     * @param data data to be sent
     */
    public BaseRequest(RequestType requestType,D data) {
        this.requestType = requestType;
        this.data = data;
    }
    /**
     * Constructor
     */
    public BaseRequest() {
        this.requestType = null;
        this.data = null;
    } 
    
    
    @Override
    public RequestType getRequestType() {
        return requestType;
    }


    @Override
    public D getData(){
        return (D) data;
    }
    
    
}
