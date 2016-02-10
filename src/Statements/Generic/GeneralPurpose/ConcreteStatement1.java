package Statements.Generic.GeneralPurpose;

import Statements.Output;
import Statements.ExecutingOutput;
import Statements.ExecutingInput;
import Statements.Input;
import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingStatement;
import Statements.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class ConcreteStatement1 implements Statement {

    private BaseStatement base;

    public ConcreteStatement1() {
        base = new BaseStatement("ConcreteStatement1", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Collection> constantValue1 = new BaseOutput<Collection>("Const1", Collection.class) {

            @Override
            public ExecutingOutput<Collection> getExecutionInstance() {
                return ()-> {
                    Collection a = (Collection) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Collection b = (Collection) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    
                    Collection c = new LinkedList();
                    c.addAll(a);
                    c.addAll(b);
                    
                    return c;
                };
            }

        };
        
       
        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("A", Collection.class){

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
       
        Input input2 =  new BaseInput("B", Collection.class){

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
       
        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);
        mapInputs.put(input2.getName(), input2);

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
