package Bonus.ActorModelStockControl.Messages;

import Bonus.ActorModelStockControl.Actors.Actor;
import Bonus.ActorModelStockControl.LineItem;
import java.util.List;

public record PlaceOrder(String orderId, List<LineItem> items, Actor notifyOnCompletion) implements Message {
}
