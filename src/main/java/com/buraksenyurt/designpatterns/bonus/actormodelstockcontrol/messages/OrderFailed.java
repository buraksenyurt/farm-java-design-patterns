package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record OrderFailed(String orderId, String reason) implements Message {

}
