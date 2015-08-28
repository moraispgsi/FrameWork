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
public abstract class BaseRequest <D extends RequestData> implements Request<D> {

    private final RequestType requestType;
    private final RequestData data;
    
    public BaseRequest(RequestType requestType,D data) {
        this.requestType = requestType;
        this.data = data;
    }

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
