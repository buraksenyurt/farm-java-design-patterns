package Bonus.ActorModelStockControl.Messages;

import Bonus.ActorModelStockControl.Actors.Actor;

public record ReserveStock(String sku, String orderId, int quantity, Actor replyTo) implements Message {
}
