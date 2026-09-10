package com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.actors;

import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.LineItem;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ConfirmShipment;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.Message;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.OrderCompleted;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.OrderFailed;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.PlaceOrder;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReleaseReservation;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReservationConfirmed;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReservationRejected;
import com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.messages.ReserveStock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderCoordinatorActor extends Actor {

    private static final class OrderState {

        private final List<LineItem> items;
        private final Actor notifyOnCompletion;
        private final Set<String> confirmedSkus = new HashSet<>();
        private boolean failed;

        private OrderState(List<LineItem> items, Actor notifyOnCompletion) {
            this.items = items;
            this.notifyOnCompletion = notifyOnCompletion;
        }
    }

    private final WarehouseDispatcherActor dispatcher;
    private final Map<String, OrderState> ordersInProgress = new HashMap<>();

    public OrderCoordinatorActor(WarehouseDispatcherActor dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void onReceive(Message message) {
        switch (message) {
            case PlaceOrder order ->
                startOrder(order);
            case ReservationConfirmed confirmed ->
                onReservationConfirmed(confirmed);
            case ReservationRejected rejected ->
                onReservationRejected(rejected);
            default -> {
            }
        }
    }

    private void startOrder(PlaceOrder order) {
        System.out.println("[Order " + order.orderId() + "] are being processed, " + order.items().size() + " line items.");
        ordersInProgress.put(order.orderId(), new OrderState(order.items(), order.notifyOnCompletion()));
        for (LineItem item : order.items()) {
            dispatcher.send(new ReserveStock(item.sku(), order.orderId(), item.quantity(), this));
        }
    }

    private void onReservationConfirmed(ReservationConfirmed confirmed) {
        OrderState state = ordersInProgress.get(confirmed.orderId());
        if (state == null || state.failed) {
            dispatcher.send(new ReleaseReservation(confirmed.sku(), confirmed.orderId(), confirmed.quantity()));
            return;
        }

        state.confirmedSkus.add(confirmed.sku());
        System.out.println("[Order " + confirmed.orderId() + "] Reservation confirmed for '" + confirmed.sku() + "'.");

        if (state.confirmedSkus.size() == state.items.size()) {
            System.out.println("[Order " + confirmed.orderId() + "] ALL line items reserved, confirming shipment.");
            for (LineItem item : state.items) {
                dispatcher.send(new ConfirmShipment(item.sku(), confirmed.orderId(), item.quantity()));
            }
            ordersInProgress.remove(confirmed.orderId());
            state.notifyOnCompletion.send(new OrderCompleted(confirmed.orderId()));
        }
    }

    private void onReservationRejected(ReservationRejected rejected) {
        OrderState state = ordersInProgress.get(rejected.orderId());
        if (state == null) {
            return;
        }

        System.out.println("[Order " + rejected.orderId() + "] REJECTED ('" + rejected.sku() + "': "
                + rejected.reason() + "). Reverting previous reservations (compensating action).");

        state.failed = true;
        for (String confirmedSku : state.confirmedSkus) {
            LineItem item = state.items.stream()
                    .filter(candidate -> candidate.sku().equals(confirmedSku))
                    .findFirst()
                    .orElseThrow();
            dispatcher.send(new ReleaseReservation(confirmedSku, rejected.orderId(), item.quantity()));
        }
        ordersInProgress.remove(rejected.orderId());
        state.notifyOnCompletion.send(new OrderFailed(rejected.orderId(), rejected.reason()));
    }
}
