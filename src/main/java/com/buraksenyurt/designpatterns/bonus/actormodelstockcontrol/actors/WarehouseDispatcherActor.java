package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ConfirmShipment;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.GetStockSnapshot;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.Message;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReleaseReservation;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReserveStock;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.StockIn;
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
