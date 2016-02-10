/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Morai
 * @param <T>
 */
public abstract class BaseInput<T> implements Input<T> {

    private Output output;
    private final String name;
    private final Class<T> type;

    public BaseInput(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class getType() {
        return type;
    }

    @Override
    public Output getOutput() {
        return output;
    }

    @Override
    public void setOutput(Output output) {
        this.output = output;
    }

}
