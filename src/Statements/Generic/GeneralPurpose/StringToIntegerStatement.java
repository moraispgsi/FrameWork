/*
 * Copyright 2016 Morai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Statements.Generic.GeneralPurpose;

import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingInput;
import Statements.ExecutingOutput;
import Statements.ExecutingStatement;
import Statements.Input;
import Statements.Output;
import Statements.Statement;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */


public class StringToIntegerStatement implements Statement {

    private BaseStatement base;

    public StringToIntegerStatement() {
        base = new BaseStatement("String para Integer", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Integer> constantValue1 = new BaseOutput<Integer>("integer", Integer.class) {

            @Override
            public ExecutingOutput<Integer> getExecutionInstance() {

                return () -> {

                    String a = (String) base.mapInputs().get("string").getExecutingInput().getOutput().getValue();
                    Integer b = Integer.parseInt(a);
                    
                    return b;

                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);

        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("string", String.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        

        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);

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
