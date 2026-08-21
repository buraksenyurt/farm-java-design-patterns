package Behavioral.Command;

public class AddDestinationCommand implements Command {
    private String destination;
    private String domain;
    private DataTransferService receiver;

    public AddDestinationCommand(String destination, String domain, DataTransferService receiver) {
        this.destination = destination;
        this.domain = domain;
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("AddDestinationCommand çalıştırılıyor..." + destination + " sisteme ekleniyor.");
        receiver.addDestination(destination, domain);
    }
    
}
