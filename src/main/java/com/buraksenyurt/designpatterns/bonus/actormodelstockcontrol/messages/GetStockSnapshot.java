package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors.Actor;

public record GetStockSnapshot(String sku, Actor replyTo) implements Message {

}
