package Statements.Generic;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Morai
 */
public class ConcatStringStatement implements Statement {

    private BaseStatement base;

    public ConcatStringStatement() {
        base = new BaseStatement("Juntar Strings", generateOutputs(), generateInputs());
    }

    private Map<String, Output> generateOutputs() {

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

        Map<String, Output> mapOutputs = new HashMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);

        return mapOutputs;
    }

    private Map<String, Input> generateInputs() {

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

        Map<String, Input> mapInputs = new HashMap();
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
