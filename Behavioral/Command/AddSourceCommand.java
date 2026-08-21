package Behavioral.Command;

// Concrete Command sınıfı
public class AddSourceCommand implements Command {
    private String source;
    private String domain;
    private DataTransferService receiver;

    public AddSourceCommand(String source, String domain, DataTransferService receiver) {
        this.source = source;
        this.domain = domain;
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        System.out.println("AddSourceCommand çalıştırılıyor..." + source + " sisteme ekleniyor.");
        receiver.addSource(source, domain);
    }
}
