/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

/**
 *
 * @author sturner
 */
public class Rule {

    private int[] condition;
    private int output;

    public Rule(int[] condition, int output) {
        this.condition = condition;
        this.output = output;
    }

    Rule(int[] ruleEnconding) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] getCondition() {
        return condition;
    }

    public int getOutput() {
        return output;
    }

}
