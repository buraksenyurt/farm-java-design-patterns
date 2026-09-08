package Bonus.ActorModelStockControl.Messages;

import Bonus.ActorModelStockControl.Actors.Actor;

public record GetStockSnapshot(String sku, Actor replyTo) implements Message {

}
