package Behavioral.CommandInGame;

public class MoveCommand implements Command {
    private final Vector direction;

    public MoveCommand(Vector direction) {
        System.out.println("MoveCommand oluşturuldu...Direction = " + direction);
        this.direction = direction;
    }

    @Override
    public void execute(Player actor) {
        actor.move(direction);
    }
}
