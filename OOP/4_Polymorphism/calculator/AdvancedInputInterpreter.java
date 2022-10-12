package calculator;

public class AdvancedInputInterpreter extends InputInterpreter{

    private MemorySaveOperation memorySaveOperation;


    public AdvancedInputInterpreter(CalculationEngine engine) {
        super(engine);
    }

    @Override
    public Operation getOperation(String operation) {
       if (operation.equals("/")){
           return new DivisionOperation();
       }else if (operation.equals("ms")) {
           if (memorySaveOperation == null){
              memorySaveOperation = new MemorySaveOperation();
           }
            return memorySaveOperation;
       }else if (operation.equals("mr")){
           return new MemoryRecallOperation(memorySaveOperation);
       }

        return super.getOperation(operation);
    }
}
