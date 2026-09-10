package com.buraksenyurt.designpatterns.bonus.actormodelbasic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * Temel aktör sınıfıdır. Tüm aktörler bu sınıftan türetilmelidir.
 * Aktörler mesajları almak ve işlemek için bir posta kutusuna(Mailbox) sahiptir.
 */
abstract class Actor implements Runnable {

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
