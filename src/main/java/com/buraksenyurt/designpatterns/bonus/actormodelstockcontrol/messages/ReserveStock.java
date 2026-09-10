package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors.Actor;

public record ReserveStock(String sku, String orderId, int quantity, Actor replyTo) implements Message {
}
