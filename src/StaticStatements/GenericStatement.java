
package StaticStatements;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Morai
 */
public abstract class GenericStatement implements Statement{
    
    protected class InvokeHeaderFooter{
        
        private String header = "";
        private GenericStatement statement = null;
        private String footer = "";

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public GenericStatement getStatement() {
            return statement;
        }

        public void setStatement(GenericStatement statement) {
            this.statement = statement;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

    }
    

    protected Map<String,InvokeHeaderFooter> invokes = new HashMap();
    protected Map<String,StaticValue> valuesInput = new HashMap();
    protected Map<String,StaticValue> valuesOutput = new HashMap();
    

    protected GenericStatement(List<String> invokeIds,List<String> inputIds,List<String> outputIds) {
        
        invokeIds.stream().forEach((id) -> invokes.put(id,new InvokeHeaderFooter()));
        inputIds.stream().forEach((id) -> valuesInput.put(id,null));
        outputIds.stream().forEach((id) -> valuesOutput.put(id,null));

    }
    
    
    
    
}
