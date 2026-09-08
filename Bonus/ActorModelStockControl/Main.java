package Bonus.ActorModelStockControl;

import java.util.List;

import Bonus.ActorModelStockControl.Actors.OrderCoordinatorActor;
import Bonus.ActorModelStockControl.Actors.ReplyCollectorActor;
import Bonus.ActorModelStockControl.Actors.WarehouseDispatcherActor;
import Bonus.ActorModelStockControl.Messages.GetStockSnapshot;
import Bonus.ActorModelStockControl.Messages.Message;
import Bonus.ActorModelStockControl.Messages.OrderCompleted;
import Bonus.ActorModelStockControl.Messages.OrderFailed;
import Bonus.ActorModelStockControl.Messages.PlaceOrder;
import Bonus.ActorModelStockControl.Messages.ReservationConfirmed;
import Bonus.ActorModelStockControl.Messages.ReservationRejected;
import Bonus.ActorModelStockControl.Messages.ReserveStock;
import Bonus.ActorModelStockControl.Messages.StockIn;
import Bonus.ActorModelStockControl.Messages.StockSnapshot;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("DMS Stock/Order Management (Actor Model Detailed Sample)\n");

        WarehouseDispatcherActor dispatcher = new WarehouseDispatcherActor();
        OrderCoordinatorActor coordinator = new OrderCoordinatorActor(dispatcher);
        ReplyCollectorActor collector = new ReplyCollectorActor();

        Thread.ofVirtual().name("dispatcher").start(dispatcher);
        Thread.ofVirtual().name("order-coordinator").start(coordinator);
        Thread.ofVirtual().name("reply-collector").start(collector);

        dispatcher.send(new StockIn("PRD-001", 5));
        dispatcher.send(new StockIn("PRD-002", 20));

        System.out.println("Case 1: Successful order scenario (Multiple line items)");
        coordinator.send(new PlaceOrder(
                "ORDER-1001",
                List.of(new LineItem("PRD-001", 2), new LineItem("PRD-002", 4)),
                collector));
        awaitOrderResult(collector);

        System.out.println("\nCase 2: Partial failure and compensating action scenario (Compensating Action)");
        coordinator.send(new PlaceOrder(
                "ORDER-1002",
                List.of(new LineItem("PRD-002", 3), new LineItem("PRD-001", 100)),
                collector));
        awaitOrderResult(collector);

        System.out.println("\nCase 3: Concurrent dealer orders (Race Condition test)");
        runConcurrentDealerRaceDemo(dispatcher, collector);
    }

    private static void awaitOrderResult(ReplyCollectorActor collector) throws InterruptedException {
        Message result = collector.takeResult();
        switch (result) {
            case OrderCompleted c ->
                System.out.println("[Result] Order " + c.orderId() + " completed successfully.");
            case OrderFailed f ->
                System.out.println("[Result] Order " + f.orderId() + " failed: " + f.reason());
            default ->
                System.out.println("[Result] Unexpected message: " + result);
        }
    }

    private static void runConcurrentDealerRaceDemo(WarehouseDispatcherActor dispatcher, ReplyCollectorActor collector)
            throws InterruptedException {
        String sku = "PRD-003";
        int initialStock = 5;
        int dealerCount = 15; // 15 bayi, her biri 1 adet istiyor ama stokta sadece 5 adet var

        dispatcher.send(new StockIn(sku, initialStock));

        for (int i = 1; i <= dealerCount; i++) {
            final String orderId = "DEALER-ORDER-" + i;
            Thread.ofVirtual().start(() -> dispatcher.send(new ReserveStock(sku, orderId, 1, collector)));
        }

        int confirmed = 0;
        int rejected = 0;
        for (int i = 0; i < dealerCount; i++) {
            Message reply = collector.takeResult();
            if (reply instanceof ReservationConfirmed) {
                confirmed++;
            } else if (reply instanceof ReservationRejected) {
                rejected++;
            }
        }

        System.out.printf("%d dealers are competing for %d units of stock. Unbelievable! -> Confirmed: %d, Rejected: %d%n",
                dealerCount, initialStock, confirmed, rejected);

        dispatcher.send(new GetStockSnapshot(sku, collector));
        StockSnapshot snapshot = (StockSnapshot) collector.takeResult();
        System.out.println("Final state -> available: " + snapshot.available() + ", reserved: " + snapshot.reserved());
    }
}
