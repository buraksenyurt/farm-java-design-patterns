package Behavioral.Command;

public class RemoveSourceCommand implements Command {
    private String source;
    private String domain;
    private DataTransferService receiver;

    public RemoveSourceCommand(String source, String domain, DataTransferService receiver) {
        this.source = source;
        this.domain = domain;
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        System.out.println("RemoveSourceCommand çalıştırılıyor..." + source + " sistemden kaldırılıyor.");
        receiver.removeSource(source, domain);
    }
    
}
