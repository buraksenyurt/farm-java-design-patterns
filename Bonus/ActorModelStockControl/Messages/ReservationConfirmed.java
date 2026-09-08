package Bonus.ActorModelStockControl.Messages;

public record ReservationConfirmed(String sku, String orderId, int quantity) implements Message {

}
