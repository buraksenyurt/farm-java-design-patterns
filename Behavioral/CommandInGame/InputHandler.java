package Behavioral.CommandInGame;

// Invoker: Tuşları dinleyen ve ilgili butona atanmış komutları döndüren sınıf.
// Doğrudan eylemi gerçelleştirmez, sadece hangi komutun çalıştırılacağını belirler.
// Çalıştırma işini oyun döngüsüne bırakır
public class InputHandler {
    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;
    private Command attackCommand;

    public InputHandler() {
        moveUpCommand = new MoveCommand(new Vector(0, 1));
        moveDownCommand = new MoveCommand(new Vector(0, -1));
        moveLeftCommand = new MoveCommand(new Vector(-1, 0));
        moveRightCommand = new MoveCommand(new Vector(1, 0));
        attackCommand = new AttackCommand();
    }

    public Command handleInput(String input) {
        if (input == null) {
            return null;
        }

        return switch (input) {
            case "W" -> moveUpCommand;
            case "S" -> moveDownCommand;
            case "A" -> moveLeftCommand;
            case "D" -> moveRightCommand;
            case "SPACE" -> attackCommand;
            default -> null;
        };
    }

    // Opsiyonel: Komutları dinamik olarak değiştirebilmek için setter metodları ekleyebiliriz
    public void changeAttackCommand(Command newAttackCommand) {
        this.attackCommand = newAttackCommand;
    }
}
