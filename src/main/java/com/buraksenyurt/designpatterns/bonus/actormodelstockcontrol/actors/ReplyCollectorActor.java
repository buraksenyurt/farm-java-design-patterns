package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ReplyCollectorActor extends Actor {

    private final BlockingQueue<Message> results = new LinkedBlockingQueue<>();

    public Message takeResult() throws InterruptedException {
        return results.take();
    }

    @Override
    protected void onReceive(Message message) {
        results.offer(message);
    }
}