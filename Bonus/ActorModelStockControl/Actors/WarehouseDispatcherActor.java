package Bonus.ActorModelStockControl.Actors;

import Bonus.ActorModelStockControl.Messages.ConfirmShipment;
import Bonus.ActorModelStockControl.Messages.GetStockSnapshot;
import Bonus.ActorModelStockControl.Messages.Message;
import Bonus.ActorModelStockControl.Messages.ReleaseReservation;
import Bonus.ActorModelStockControl.Messages.ReserveStock;
import Bonus.ActorModelStockControl.Messages.StockIn;
import java.util.HashMap;
import java.util.Map;

public class WarehouseDispatcherActor extends Actor {

    private final Map<String, StockActor> stockActors = new HashMap<>();

    @Override
    protected void onReceive(Message message) {
        switch (message) {
            case StockIn stockIn -> routeToStockActor(stockIn.sku()).send(stockIn);
            case ReserveStock reserveStock -> routeToStockActor(reserveStock.sku()).send(reserveStock);
            case ReleaseReservation release -> routeToStockActor(release.sku()).send(release);
            case ConfirmShipment shipment -> routeToStockActor(shipment.sku()).send(shipment);
            case GetStockSnapshot snapshot -> routeToStockActor(snapshot.sku()).send(snapshot);
            default -> {
            }
        }
    }

    private StockActor routeToStockActor(String sku) {
        return stockActors.computeIfAbsent(sku, this::createStockActor);
    }

    private StockActor createStockActor(String sku) {
        StockActor stockActor = new StockActor(sku);
        Thread.ofVirtual().name("stock-" + sku).start(stockActor);
        System.out.println("[Dispatcher] New StockActor spawned for '" + sku + "'.");
        return stockActor;
    }
}
