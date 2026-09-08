package Bonus.ActorModelStockControl.Messages;

public record ReservationRejected(String sku, String orderId, String reason) implements Message {

}
