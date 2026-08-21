package Behavioral.Command;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        // Receiver nesnesi oluşturuluyor
        DataTransferService receiver = new DataTransferService();

        // Concrete Command nesneleri oluşturuluyor
        Command addQueryCommand = new AddQueryCommand("Select * from Products Where OrderDate > '2026-01-01'", "Inventory", receiver);
        Command addFtpCommand = new AddSourceCommand("sftp://azon.moon.com", "Inventory", receiver);
        Command addDatabaseCommand = new AddSourceCommand("StoneageDb", "Inventory", receiver);
        Command addDestinationCommand = new AddDestinationCommand("Data Warehouse", "Inventory", receiver);

        // Command nesneleri Invoker'a ekleniyor
        DataTransferRunner.getInstance().addCommand(addQueryCommand);
        DataTransferRunner.getInstance().addCommand(addFtpCommand);
        DataTransferRunner.getInstance().addCommand(addDatabaseCommand);
        DataTransferRunner.getInstance().addCommand(addDestinationCommand);

        Thread.sleep(2000); // Olayı simüle etmek için hayali bir bekletme
        DataTransferRunner.getInstance().stop();
    }
}
