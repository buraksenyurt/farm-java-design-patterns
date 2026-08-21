package Behavioral.Memento;

/*
    Şöyle bir senaryo düşünelim. Bir müşterinin alışveriş sepetine attığı ürünler ve indirim kodu var.
    Müşteri sepete ürün ekleme ve indirim kodu uygulama gibi işlemler yapabiliyor ve ayrıca her şeyi onaylamadan önce
    geri alma (undo) işlemi de yapmak istiyor.

    Bunun gibi bir senaryoya konu olan bir nesnenin anlık durumlarını hatırlamak (Snapshot almak diyelim)
    ve gerektiğinde geri almak için Memento Design Pattern kullanılabilir.

    Bu işlemde nesne kendi anlık durumunu kaydeder, bu anısı için kimsenin okuyamayacağı bir token verir
    ve daha sonra o token üzerinden kendini geri yükler.

    Bu örnekte Memento Design Pattern söz konusu senaryo özelinde ele alınıyor.
*/

public class Main {
    public static void main(String[] args) {
        OrderDraft orderDraft = new OrderDraft();
        OrderHistory orderHistory = new OrderHistory(orderDraft);

        /*
         * Her mutasyon grubundan önce anlık durumu kaydediyoruz (checkpoint).
         * Bu sayede undo çağrıldığında geri dönülecek gerçekten farklı bir durum
         * oluyor. Artık checkpoint alma ve geri yükleme OrderHistory içinde,
         * Originator (OrderDraft) üzerinden yürütülüyor; Client save()/restore()
         * ile hiç uğraşmıyor.
         */
        orderHistory.save();
        orderDraft.addItem("PRD-10001");
        orderDraft.addItem("PRD-10002");
        orderDraft.applyDiscountCode("VIP Discount");

        orderHistory.save();
        orderDraft.addItem("PRD-10003");
        orderDraft.applyDiscountCode("Student Discount");

        orderHistory.save();
        orderDraft.addItem("PRD-10004");
        orderDraft.applyDiscountCode("Black Friday Discount");

        System.out.println("Şu anki durum: " + orderDraft);

        // Geri alma işlemi (undo)
        System.out.println("Geri alma işlemi (undo) yapılıyor...");
        orderHistory.undo();
        System.out.println("Güncel durum: " + orderDraft);

        // Tekrar geri alma işlemi (undo)
        System.out.println("Tekrar geri alma işlemi (undo) yapılıyor...");
        orderHistory.undo();
        System.out.println("Güncel durum: " + orderDraft);

        // Redo işlemi (tekrar ileri alma)
        System.out.println("Redo(tekrar ileri alma) yapılıyor...");
        orderHistory.redo();
        System.out.println("Redo ile geri yüklenen durum: " + orderDraft);

        // Tekrar geri alma işlemi (undo)
        System.out.println("Tekrar geri alma işlemi (undo) yapılıyor...");
        orderHistory.undo();
        System.out.println("Güncel durum: " + orderDraft);
    }
}
