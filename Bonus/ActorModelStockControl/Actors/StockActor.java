package Bonus.ActorModelStockControl.Actors;

import Bonus.ActorModelStockControl.Messages.ConfirmShipment;
import Bonus.ActorModelStockControl.Messages.GetStockSnapshot;
import Bonus.ActorModelStockControl.Messages.Message;
import Bonus.ActorModelStockControl.Messages.ReleaseReservation;
import Bonus.ActorModelStockControl.Messages.ReservationConfirmed;
import Bonus.ActorModelStockControl.Messages.ReservationRejected;
import Bonus.ActorModelStockControl.Messages.ReserveStock;
import Bonus.ActorModelStockControl.Messages.StockIn;
import Bonus.ActorModelStockControl.Messages.StockSnapshot;

public class StockActor extends Actor {

    private final String sku;
    private int available;
    private int reserved;

    public StockActor(String sku) {
        this.sku = sku;
    }

    @Override
    protected void onReceive(Message message) {
        switch (message) {
            case StockIn stockIn -> available += stockIn.quantity();
            case ReserveStock reserveStock -> handleReserve(reserveStock);
            case ReleaseReservation release -> {
                reserved -= release.quantity();
                available += release.quantity();
            }
            case ConfirmShipment shipment -> reserved -= shipment.quantity();
            case GetStockSnapshot snapshot ->
                snapshot.replyTo().send(new StockSnapshot(sku, available, reserved));
            default -> {
            }
        }
    }

    private void handleReserve(ReserveStock reserve) {
        if (available >= reserve.quantity()) {
            available -= reserve.quantity();
            reserved += reserve.quantity();
            reserve.replyTo().send(new ReservationConfirmed(sku, reserve.orderId(), reserve.quantity()));
        } else {
            reserve.replyTo().send(new ReservationRejected(
                    sku,
                    reserve.orderId(),
                    "Insufficient stock: requested " + reserve.quantity() + ", available " + available));
        }
    }
}
