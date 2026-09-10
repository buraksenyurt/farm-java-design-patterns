package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record ReservationConfirmed(String sku, String orderId, int quantity) implements Message {

}
