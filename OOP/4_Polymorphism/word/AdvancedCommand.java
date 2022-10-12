package word;

import java.util.List;

public class AdvancedCommand extends CommandInterfaceImpl {

    public AdvancedCommand(StringBuilder text) {
        super(text);
    }

    @Override
    protected List<Command> initCommands() {
        CutTransform cutTransform = new CutTransform();
        Command cut = new Command("cut", cutTransform);
        List<Command> commands = super.initCommands();
        commands.add(cut);

        Command paste = new Command("paste", new PasteTransform(cutTransform));
        commands.add(paste);

        return commands;
    }


}
