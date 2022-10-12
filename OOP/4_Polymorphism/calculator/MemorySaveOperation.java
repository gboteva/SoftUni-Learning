package calculator;


import java.util.LinkedList;
import java.util.List;

public class MemorySaveOperation implements Operation{
    private List<Integer> operands;

    public MemorySaveOperation() {
        this.operands = new LinkedList<>();
    }

    @Override
    public void addOperand(int operand) {
        this.operands.add(operand);
    }

    @Override
    public int getResult() {
        return operands.get(operands.size()-1);
    }

    @Override
    public boolean isCompleted() {
        return !operands.isEmpty();
    }

    public Integer releasedFromMemory(){
        return operands.remove(operands.size()-1);
    }
}
