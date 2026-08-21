package Behavioral.CommandSimple;

// Receiver
public class Light {
    private final String room;

    public Light(String room) {
        this.room = room;
    }

    public void turnOn() {
        System.out.println(room + " ışığı açıldı.");
    }

    public void turnOff() {
        System.out.println(room + " ışığı kapatıldı.");
    }
}
