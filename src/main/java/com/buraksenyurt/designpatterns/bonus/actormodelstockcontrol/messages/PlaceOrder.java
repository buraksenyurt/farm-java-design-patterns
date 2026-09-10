package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors.Actor;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.LineItem;
import java.util.List;

public record PlaceOrder(String orderId, List<LineItem> items, Actor notifyOnCompletion) implements Message {
}
