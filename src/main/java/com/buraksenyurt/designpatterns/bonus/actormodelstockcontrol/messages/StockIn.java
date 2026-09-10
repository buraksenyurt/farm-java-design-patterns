package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record StockIn(String sku, int quantity) implements Message {

}
