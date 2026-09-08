package Bonus.ActorModelStockControl.Messages;

public record StockSnapshot(String sku, int available, int reserved) implements Message {

}
