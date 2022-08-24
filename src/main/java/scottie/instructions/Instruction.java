package scottie.instructions;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

import java.util.Map;
import java.util.Objects;

public abstract class Instruction {
    private final String mainArgument;
    private final Map<String, String> flagArgumentsMap;

    Instruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        this.mainArgument = mainArgument;
        this.flagArgumentsMap = flagArgumentsMap;
    }

    public static Instruction of(String commandName, String mainArgument, Map<String, String> flagArgumentsMap)
            throws InvalidCommandException {
        Command command = Command.fromName(commandName);
        if (command == null) {
            throw new InvalidCommandException(commandName);
        }
        switch (command) {
        case LIST:
            return new ListInstruction(mainArgument, flagArgumentsMap);
        case MARK:
            return new MarkInstruction(mainArgument, flagArgumentsMap);
        case UNMARK:
            return new UnmarkInstruction(mainArgument, flagArgumentsMap);
        case TODO:
            return new TodoInstruction(mainArgument, flagArgumentsMap);
        case DEADLINE:
            return new DeadlineInstruction(mainArgument, flagArgumentsMap);
        case EVENT:
            return new EventInstruction(mainArgument, flagArgumentsMap);
        case DELETE:
            return new DeleteInstruction(mainArgument, flagArgumentsMap);
        case BYE:
            return new ByeInstruction(mainArgument, flagArgumentsMap);
        default:
            // Only way execution can reach here is if a new Command was added
            // but the switch statement was not updated.
            throw new RuntimeException("Missing Instruction subclass for Command " + command.name());
        }
    }

    boolean hasMainArgument() {
        return this.mainArgument != null;
    }

    String getMainArgument() {
        return this.mainArgument;
    }

    String getFlagArgument(String flag) {
        return this.flagArgumentsMap.get(flag);
    }

    public abstract void execute(TaskList taskList, Ui ui);

    public boolean endsProgram() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instruction that = (Instruction) o;
        return Objects.equals(this.mainArgument, that.mainArgument)
                && Objects.equals(this.flagArgumentsMap, that.flagArgumentsMap);
    }
}
