package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record ReservationRejected(String sku, String orderId, String reason) implements Message {

}
