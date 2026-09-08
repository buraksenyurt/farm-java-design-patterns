package Bonus.ActorModelStockControl.Messages;

public record OrderFailed(String orderId, String reason) implements Message {

}
