package Behavioral.Memento;

import java.util.ArrayList;
import java.util.List;

/*
    Desende Originator rolünü üstlenen sınıf.
    Ürünle ve indirim kodunu değiştirilebilir(mutable) halini tutar.
    Dikkat edileceği üzere addItem ve applyDiscountCode metotları ile ürün ve indirim kodunu değiştirebiliriz.
    Bu değişiklikler, OrderDraftMemento nesnesi ile kaydedilebilir ve geri yüklenebilir. Bunun içinde save ve restore metotları kullanılır.
*/
public class OrderDraft {
    private List<String> items;
    private String discountCode;

    public OrderDraft() {
        this.items = new ArrayList<>();
        this.discountCode = "";
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void applyDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public OrderDraftMemento save() {
        return new OrderDraftMemento(new ArrayList<>(items), discountCode);
    }

    public void restore(OrderDraftMemento memento) {
        this.items = new ArrayList<>(memento.getItems());
        this.discountCode = memento.getDiscountCode();
    }

    @Override
    public String toString() {
        return "OrderDraft{items=" + items + ", discountCode='" + discountCode + "'}";
    }
}
