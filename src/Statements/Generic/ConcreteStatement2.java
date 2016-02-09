package Statements.Generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class ConcreteStatement2 implements Statement {

    private BaseStatement base;

    public ConcreteStatement2() {
        base = new BaseStatement("ConcreteStatement2", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Collection> constantValue1 = new BaseOutput<Collection>("Const1", Collection.class) {

            @Override
            public ExecutingOutput<Collection> getExecutionInstance() {
                return ()-> {
                    return Arrays.asList("B");
                };
            }

        };
        
        Output<Collection> constantValue2 = new BaseOutput<Collection>("Const2", Collection.class) {

            @Override
            public ExecutingOutput<Collection> getExecutionInstance() {
                return ()-> {
                    return Arrays.asList("A","A","A","A");
                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        mapOutputs.put(constantValue2.getName(), constantValue2);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

     
       
        SortedMap<String, Input> mapInputs = new TreeMap();
       

        return mapInputs;
    }

    @Override

    public Set<Output> getOutputs() {
        return base.getOutputs();
    }

    @Override
    public Set<Input> getInputs() {
        return base.getInputs();
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public ExecutingStatement getExecutingInstance() {
        return () -> {
            
        };
    }

}
