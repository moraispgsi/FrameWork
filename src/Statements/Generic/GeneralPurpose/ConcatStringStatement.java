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
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class ConcatStringStatement implements Statement {

    private BaseStatement base;

    public ConcatStringStatement() {
        base = new BaseStatement("Juntar Strings", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<String> constantValue1 = new BaseOutput<String>("Juntar", String.class) {

            @Override
            public ExecutingOutput<String> getExecutionInstance() {

                return () -> {

                    String a = (String) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    String b = (String) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    return a + b;

                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);

        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("A", String.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        Input input2 = new BaseInput("B", String.class) {

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
