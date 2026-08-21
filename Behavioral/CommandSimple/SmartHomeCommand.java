package Behavioral.CommandSimple;

// Command arayüzü
public interface SmartHomeCommand {
    void execute();
    void undo();
}
