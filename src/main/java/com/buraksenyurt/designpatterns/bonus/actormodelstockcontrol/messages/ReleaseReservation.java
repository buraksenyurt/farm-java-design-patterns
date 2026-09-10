package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record ReleaseReservation(String sku, String orderId, int quantity) implements Message {
}