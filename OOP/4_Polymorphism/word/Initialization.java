package word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text){
        CommandInterface command = new AdvancedCommand(text);
        command.init();
        return command;
    }
}
