package Behavioral.Memento;

import java.util.List;

public class OrderDraftMemento {
    private List<String> items;
    private String discountCode;

    // Pratikte, memento nesnesi genellikle sadece Originator tarafından oluşturulur
    // ve dışarıdan erişilemez.
    // Bu nedenle, constructor'ı package-private (default) yapabiliriz.
    OrderDraftMemento(List<String> items, String discountCode) {
        this.items = items;
        this.discountCode = discountCode;
    }

    List<String> getItems() {
        return items;
    }

    String getDiscountCode() {
        return discountCode;
    }

}
