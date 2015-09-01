/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestDispatcher;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;

/**
 * Represents a request dispatcher wrapped class that avoids inheritance
 * by wrapping the object with another class through composition, implementing
 * the same interface and delegating all the methods.
 * 
 * @author Ricardo José Horta Morais
 */
public class WrappedRequestDispatcher implements RequestDispatcher {

    private final Map<String, Set<RequestHandler>> handlers = new HashMap<>();
    
    /**
     * Fires a request
     * @param dispatcher Wrapper dispatcher
     * @param request request to fire
     */

    public void fireRequest(RequestDispatcher dispatcher,Request request) {
        
        if (dispatcher == null || request == null) {
            throw new RuntimeException("Parameters cannot be null.");
        }
        
        String type = request.getRequestType().getType();
        
        /*
         * TODO: Implement the root request handler to be a type of handle that can receive any type of event
         * 
         */
        
        if(handlers.containsKey(type))
            handlers.get(type).stream().forEach((RequestHandler h) -> h.handle(dispatcher, request));
        
    }
    
    @Override
    public void fireRequest(Request request){
        fireRequest(this,request);
    }

    @Override
    public void registerHandler(RequestType requestType, RequestHandler handler) {

        if (requestType == null || handler == null) {
            throw new RuntimeException("Parameters cannot be null.");
        }

        unregisterHandler(handler);

        if (!handlers.containsKey(requestType.getType())) {
            handlers.put(requestType.getType(), new HashSet<>());
        }

        handlers.get(requestType.getType()).add(handler);

    }

    @Override
    public void unregisterHandler(RequestHandler handler) {
        handlers.values()
                .stream()
                .forEach(s -> s.remove(handler));
    }

}
