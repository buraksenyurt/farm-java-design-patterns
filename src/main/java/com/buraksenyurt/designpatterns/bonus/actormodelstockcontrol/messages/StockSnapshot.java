package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages;

public record StockSnapshot(String sku, int available, int reserved) implements Message {

}
