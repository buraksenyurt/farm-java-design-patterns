package Behavioral.CommandSimple;

// Concrete Command: Işığı açma komutu
public class TurnOnLightCommand implements SmartHomeCommand {
    private final Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
