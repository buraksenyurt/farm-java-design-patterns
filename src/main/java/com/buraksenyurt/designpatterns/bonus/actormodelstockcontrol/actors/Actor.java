package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.Message;

public abstract class Actor implements Runnable {

    protected final BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public void send(Message message) {
        mailbox.offer(message);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = mailbox.take();
                onReceive(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    protected abstract void onReceive(Message message);
}
