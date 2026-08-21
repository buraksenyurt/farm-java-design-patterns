package Behavioral.Command;

import java.util.LinkedList;
import java.util.List;

// Invoker rolünü üstlenen sınıf. 
// Burada Runnable türevli bir sınıf kullanılıyor.
public class DataTransferRunner implements Runnable {
    private Thread thread;
    private List<Command> commandQueue;
    private volatile boolean stop;
    private static final DataTransferRunner RUNNER = new DataTransferRunner();

    public static final DataTransferRunner getInstance() {
        return RUNNER;
    }

    private DataTransferRunner() {
        commandQueue = new LinkedList<>();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("DataTransferRunner çalışmaya başladı...");
        while (true) {
            Command command = null;
            synchronized (commandQueue) {
                if (commandQueue.isEmpty()) {
                    try {
                        commandQueue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("DataTransferRunner durduruluyor...");
                        if (stop)
                            return;
                    }
                } else {
                    command = commandQueue.remove(0);
                }
            }
            if (command != null) {
                command.execute();
            }
        }
    }

    public void addCommand(Command command) {
        synchronized (commandQueue) {
            System.out.println("DataTransferRunner'a yeni bir komut eklendi: " + command.getClass().getSimpleName());
            commandQueue.add(command);
            commandQueue.notifyAll();
        }
    }

    public void stop() {
        stop = true;
        thread.interrupt();
    }
}
