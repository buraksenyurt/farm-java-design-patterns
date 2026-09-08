package Bonus.ActorModelStockControl.Messages;

public record ConfirmShipment(String sku, String orderId, int quantity) implements Message {

}
