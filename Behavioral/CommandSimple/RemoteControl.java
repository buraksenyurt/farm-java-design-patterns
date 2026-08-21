package Behavioral.CommandSimple;

import java.util.ArrayDeque;
import java.util.Deque;

// Invoker
public class RemoteControl {
    private final Deque<SmartHomeCommand> history = new ArrayDeque<>();

    public void press(SmartHomeCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("Geri alınacak komut yok.");
            return;
        }
        history.pop().undo();
    }
}
