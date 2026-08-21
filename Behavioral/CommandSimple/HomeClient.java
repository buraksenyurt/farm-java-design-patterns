package Behavioral.CommandSimple;

public class HomeClient {
    public static void main(String[] args) {

        // Receiver
        Light saloonLight = new Light("Salon");
        Light bedroomLight = new Light("Yatak Odası");

        // Concrete Command nesneleri
        SmartHomeCommand openSaloonLight = new TurnOnLightCommand(saloonLight);
        SmartHomeCommand closeSaloonLight = new TurnOffLightCommand(saloonLight);
        SmartHomeCommand openBedroomLight = new TurnOnLightCommand(bedroomLight);

        // Invoker
        RemoteControl remote = new RemoteControl();

        remote.press(openSaloonLight);
        remote.press(openBedroomLight);
        remote.press(closeSaloonLight);

        remote.undoLast();
        remote.undoLast();
    }
}
