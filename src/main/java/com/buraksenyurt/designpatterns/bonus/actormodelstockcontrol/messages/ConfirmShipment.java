package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record ConfirmShipment(String sku, String orderId, int quantity) implements Message {

}
