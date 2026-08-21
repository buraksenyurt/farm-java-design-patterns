package Behavioral.CommandSimple;

// Concrete Command: Işığı kapatma komutu
public class TurnOffLightCommand implements SmartHomeCommand {
    private final Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}
