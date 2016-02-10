/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 *
 * @author Morais
 */
public class BaseStatement {

    private final String name;
    private final SortedMap<String, Output> mapOutputs;
    private final SortedMap<String, Input> mapInputs;

    public BaseStatement(String name, SortedMap<String, Output> mapOutputs, SortedMap<String, Input> mapInputs) {
        this.name = name;
        this.mapOutputs = mapOutputs;
        this.mapInputs = mapInputs;
    }

    public Set<Output> getOutputs() {
        return new HashSet(mapOutputs.values());
    }

    public Set<Input> getInputs() {
        return new HashSet(mapInputs.values());
    }

    public Map<String, Output> mapOutputs() {
        return mapOutputs;
    }

    public Map<String, Input> mapInputs() {
        return mapInputs;
    }

    public String getName() {
        return name;
    }

}
