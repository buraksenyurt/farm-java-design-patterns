package Bonus.ActorModelStockControl.Messages;

public record StockIn(String sku, int quantity) implements Message {

}
