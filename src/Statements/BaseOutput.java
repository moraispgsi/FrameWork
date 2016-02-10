/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Morai
 */
public abstract class BaseOutput<T> implements Output<T> {

    private final String name;
    private final Class<T> type;

    public BaseOutput(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }
    
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<T> getType() {
        return type;
    }
    
}
