package Bonus.ActorModelStockControl.Messages;

public record ReleaseReservation(String sku, String orderId, int quantity) implements Message {
}